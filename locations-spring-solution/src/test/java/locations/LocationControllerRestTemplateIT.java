package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    LocationsService locationsService;

    @BeforeEach
    void init() {
        locationsService.deleteAll();
    }

    @BeforeEach
    void initAnother() {
        LocationDto locationDto =
                template.postForObject("/locations",
                        new CreateLocationCommand("Budapest",  47.497912, 19.040235),
                        LocationDto.class);

        LocationDto locationDto1 =
                template.postForObject("/locations",
                        new CreateLocationCommand( "London",51.5,0),
                        LocationDto.class);


        LocationDto locationDto2 =
                template.postForObject("/locations",
                        new CreateLocationCommand(  "Quito",0, -78.5),
                        LocationDto.class);

    }





    @Test
    void testGetLocations() {

        List<LocationDto> locationDtos = template.exchange("/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDto>>() {})
                .getBody();

        assertThat(locationDtos)
                .extracting(LocationDto::getName)
                .containsExactly("Budapest", "London", "Quito");
    }

    @Test
    void testGetLocationById() {

        LocationDto result = template.exchange("/locations/id/1",
                HttpMethod.GET,
                null,
                LocationDto.class)
                .getBody();
        assertEquals("Budapest", result.getName());

    }


    @Test
    void testCreateLocation() {

        assertEquals("Budapest", locationDto.getName());
    }

    @Test
    void testUpdateLocation() {

        template.put("/locations/1",
                new UpdateLocationCommand("Funchal", 11, 25));

        LocationDto result = template.exchange("/locations/id/1",
                HttpMethod.GET,
                null,
                LocationDto.class
        )
                .getBody();

        assertEquals("Funchal", result.getName());

    }

    @Test
    void testDeleteLocationById() {
        template.delete("/locations/1");

        List<LocationDto> locations = template.exchange("/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDto>>() {
                })
                .getBody();

        assertEquals(2, locations.size());
    }
}
