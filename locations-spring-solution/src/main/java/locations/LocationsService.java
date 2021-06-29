package locations;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsService {

    private List<Location>locations;

    public LocationsService() {
        this.locations =  new ArrayList<>();
        locations.add(new Location(1l, "Budapest",47.497912,-19.040235));
        locations.add(new Location(2l, "London",51.5,0));
        locations.add(new Location(3l, "Quito",0, -78.5));
        locations.add(new Location(4l, "Manila",0, -123.6));
    }

    public List<Location> getLocations() {

        return new ArrayList<>(locations);
    }
}
