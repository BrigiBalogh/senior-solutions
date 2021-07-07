package org.training360.musicstore;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instruments")
public class MusicStoreController {


    private MusicStoreService musicStoreService;

    public MusicStoreController(MusicStoreService musicStoreService) {
        this.musicStoreService = musicStoreService;
    }


    @GetMapping
    public List<InstrumentDTO> getInstruments(
            @RequestParam Optional<String> brand,@RequestParam Optional<Integer>price )
    {
        return musicStoreService.getInstruments(brand, price);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public InstrumentDTO getInstrumentById(@PathVariable("id") long id) {
        return musicStoreService.getInstrumentById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstrumentDTO createInstrument ( @Valid @RequestBody CreateInstrumentCommand command) {
        return musicStoreService.createInstrument(command);
    }

    @DeleteMapping
    public void deleteAll(){
        musicStoreService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteInstrumentById(@PathVariable long id) {

        musicStoreService.deleteInstrumentById(id);
    }


    @PutMapping("/{id}")
    public InstrumentDTO updateInstrumentPrice(
            @PathVariable("id") long id, @Valid @RequestBody UpdatePriceCommand command) {
        return musicStoreService.updateInstrumentPrice(id, command);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<org.zalando.problem.Problem> handleNotFound(IllegalArgumentException iae) {
        org.zalando.problem.Problem problem =
                Problem.builder()
                        .withType(URI.create("instruments/not-found"))
                        .withTitle("Not found")
                        .withStatus(Status.NOT_FOUND)
                        .withDetail(iae.getMessage())
                        .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
/*
    @GetMapping("/{id}")
    public ResponseEntity findMovieById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(service.findMovieById(id));

        } catch (IllegalArgumentException iae) {
            return ResponseEntity.notFound().build();
        }
    }

 */