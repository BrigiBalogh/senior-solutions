package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LocationControllerIT {


    @Autowired
    LocationsController controller;

    @Test
    void testGetLocations() {

        List<LocationDto> result = controller.getLocations();

        assertThat(result)
                .hasSize(3)
                .extracting(LocationDto::getName)
                .contains("Budapest", "London", "Quito");
    }

    @Test
    void testFindLocationByName() {

        LocationDto result = controller.findLocationByName("Budapest");
        assertEquals("Budapest", result.getName());
    }

    @Test
    void testGetLocationById() {

        LocationDto result = controller.getLocationById(1L);
        assertEquals("Budapest", result.getName());
    }
}




