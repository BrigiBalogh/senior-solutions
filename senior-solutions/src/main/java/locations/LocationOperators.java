package locations;

import java.util.List;
import java.util.stream.Collectors;

public class LocationOperators {

    public static List<Location> filterOnNorth(List<Location>locations) {
        return locations.stream().filter(e -> e.getLat() > 0).collect(Collectors.toList());
    }
}
