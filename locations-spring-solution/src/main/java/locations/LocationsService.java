package locations;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsService {

    private List<Location>locations;

    public LocationsService(List<Location> locations) {
        this.locations =  locations;
    }

    public List<Location> getLocations() {

        return new ArrayList<>(locations);
    }
}
