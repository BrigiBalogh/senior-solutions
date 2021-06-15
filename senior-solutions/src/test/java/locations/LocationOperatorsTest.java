package locations;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;


public class LocationOperatorsTest {

    @LocationOperationsFeatureTest
    public void testHaversine() {
        double kmDistance = Location.distanceFrom(47, 0, 17, 170);
        Location location1 = new Location("Bélbaltavár", 47, 17);
        Location location2 = new Location("Papua mellett", 0, 170);
        double kmDistance2 = location1.distanceFrom(location2);
        assertEquals(14168.5, kmDistance, 0.5);
    }

    @Test
    public void testLocationOneStep() {
        String name = "Budapest";
        double lat = 47;
        double lon = 17;
        Location location1 = new Location(name, lat, lon);
        assertEquals("Location{" +
            "name='" + name + '\'' +
                    ", lat=" + lat +
                    ", lon=" + lon +
                    '}', location1.toString());
    }

    @Test
    public void testOnNorth() {
        Location location1 = new Location("Bélbaltavár", 47, 17);
        Location location2 = new Location("Papua mellett", 0, 170);
        List<Location> locList = List.of(location1, location2);
        List<Location> onNorth = LocationOperators.filterOnNorth(locList);
        assertNotNull(onNorth);
        assertEquals(1, onNorth.size());
        assertEquals("Bélbaltavár", onNorth.get(0).getName());
    }
}
