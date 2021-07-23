package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationsServiceTest {


    @Autowired
    LocationsService service;

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private LocationsRepository locationsRepository;

    @BeforeEach
    void setUp() {
        //service = new LocationsService(modelMapper, locationsRepository);
    }


    @Test
    void getLocations() {
        List<LocationDto> result = service.getLocations();

        assertThat(result)
                .hasSize(3)
                .extracting(LocationDto::getName)
                .contains("Budapest", "London");
    }

    @Test
    void getLocationById() {
        LocationDto result = service.findLocationById(1L);

        assertThat(result).isNotNull()
                .extracting(LocationDto::getName).isEqualTo("Budapest");
    }

    @Test
    void findLocationByName() {
        LocationDto result = service.findLocationByName("Budapest");

        assertThat(result.getName())
                .isEqualTo("Budapest");
    }


    @Test
    void findLocationByNameNotFound() {

        Exception ex = assertThrows(LocationNotFoundException.class, () -> service.findLocationByName("Salobrena"));
        assertEquals("Location not found" , ex.getMessage());

    }



    @Test
    void getLocationByIdNotFound() {
        Exception ex = assertThrows(LocationNotFoundException.class, () -> service.findLocationById(4L));
        assertEquals("Location not found ", ex.getMessage());
    }

    @Test
    void createLocation() {
        CreateLocationCommand command = new CreateLocationCommand("Fuchal", 11, 25);
        LocationDto result = service.createLocation(command);

        assertAll(
                () -> assertEquals("Funchal", result.getName()),
                () -> assertEquals(11, result.getLat()),
                () -> assertEquals(25, result.getLon())
        );
    }

    @Test
    void updateLocation() {
        UpdateLocationCommand command = new UpdateLocationCommand();
        command.setName("Funchal");
        command.setLat(11);
        command.setLon(25);
        LocationDto result = service.updateLocation(1L, command);
        assertAll(
                () -> assertEquals("Funchal", result.getName()),
                () -> assertEquals(11, result.getLat()),
                () -> assertEquals(25, result.getLon())
        );
    }

    @Test
    void deleteLocationById() {
        service.deleteLocationById(1L);

        assertEquals(2, service.getLocations().size());

    }


}