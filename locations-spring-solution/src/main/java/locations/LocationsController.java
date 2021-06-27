package locations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LocationsController {

    private LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping("/locations")
    @ResponseBody
    public String getLocations() {
     /*   List<Location> locations = List.of(new Location(1l, "Budapest",47.497912,-19.040235),
                new Location(2l, "London",51.5,0),
                new Location(3l, "Quito",0, -78.5));

        return locations.stream()
                .map(l -> l.toString())
                .collect(Collectors.joining(", "));
          */
        return locationsService.getLocations().toString();
    }
}
