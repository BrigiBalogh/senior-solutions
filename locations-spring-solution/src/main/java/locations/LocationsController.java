package locations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/locations")
@Tag(name = "Operations on locations")
public class LocationsController {

    private LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

  /*  @GetMapping
    public  String getLocations() {
        List<Location> locations = List.of(new Location(1l, "Budapest",47.497912,19.040235),
                new Location(2l, "London",51.5,0),
                new Location(3l, "Quito",0, -78.5));

        return locations.stream()
                .map(l -> l.toString())
                .collect(Collectors.joining(", "));

        return locationsService.getLocations().toString();
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public LocationsDto getLocationsOtherVariable(@RequestParam Optional<String> name) {

        return new LocationsDto( locationsService.getLocationsOtherVariable(name));
    }*/

    @GetMapping
    public List<LocationDto> getLocationsOtherVariable(@RequestParam Optional<String> name) {

        return locationsService.getLocationsOtherVariable(name);
    }


    /*@GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public LocationDto getLocationById(@PathVariable("id") long id) {
        return  locationsService.getLocationById(id);
    }*/

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity findLocationById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(locationsService.findLocationById(id));
        } catch (IllegalArgumentException ex) {
            throw new LocationNotFoundException("Cannot found location", ex);
            //return ResponseEntity.notFound().build();
        }
    }

    public LocationDto findLocationByName(@RequestParam String name) {
        return locationsService.findLocationByName(name);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a location", description = "Creates a location.")
    @ApiResponse(responseCode = "404", description = "Location is not found")
    public LocationDto createLocation(@Valid @RequestBody CreateLocationCommand command) {
        return locationsService.createLocation(command);
    }

    @DeleteMapping
    public void deleteAll() {
        locationsService.deleteAll();
    }

    @DeleteMapping("/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocationById(@PathVariable long id) {
        try {
            locationsService.deleteLocationById(id);
        } catch (IllegalArgumentException ex) {
            throw new LocationNotFoundException(ex);
        }
    }


    @PutMapping("/{id}")
    public LocationDto updateLocation(
            @PathVariable("id") long id, @Valid @RequestBody UpdateLocationCommand command) {
        return locationsService.updateLocation(id, command);
    }


    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(LocationNotFoundException iae) {
        Problem problem =
                Problem.builder()
                        .withType(URI.create("locations/not-found"))
                        .withTitle("Not found")
                        .withStatus(Status.NOT_FOUND)
                        .withDetail(iae.getMessage())
                        .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handleValidException(MethodArgumentNotValidException exception) {
        List<Violation> violations =
                exception.getBindingResult().getFieldErrors().stream()
                        .map(fe -> new Violation(fe.getField(), fe.getDefaultMessage()))
                        .collect(Collectors.toList());

        Problem problem =
                Problem.builder()
                        .withType(URI.create("locations/not-valid"))
                        .withTitle("Validation error")
                        .withStatus(Status.BAD_REQUEST)
                        .withDetail(exception.getMessage())
                        .with("violations", violations)
                        .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
