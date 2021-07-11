package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService service;

    @InjectMocks
    LocationsController controller;


    LocationDto budapest;
    LocationDto london;
    LocationDto quito;

    @BeforeEach
    void init() {
        budapest = new LocationDto("Budapest", 47.497912, 19.040235);
        london = new LocationDto("London", 51.5, 0);
        quito = new LocationDto("Quito", 0, -78.5);
    }


    @Test
    void testGetLocations() {
        List<LocationDto> locations = new ArrayList<>(List.of(
                budapest, london,quito));

        when(service.getLocations()).thenReturn(locations);

        List<LocationDto> result = controller.getLocations();

        assertThat(result)
                .hasSize(3)
                .extracting(LocationDto::getName)
                .contains("Budapest", "London", "Quito");
    }

    @Test
    void testGetLocationById() {
        when(service.getLocationById(anyLong())).thenReturn(budapest);

        LocationDto result =controller.getLocationById(1L);

        assertEquals("Budapest", result.getName());
    }

    @Test
    void testFindLocationByName() {

        when(service.findLocationByName(anyString())).thenReturn(budapest);

        LocationDto result = controller.findLocationByName("Budapest");

        assertEquals("Budapest", result.getName());
    }


    @Test
    void testGetLocationById() {
        when(service.getLocationById(anyLong())).thenReturn(budapest);

        LocationDto result =controller.getLocationById(1L);

        assertEquals("Budapest", result.getName());
    }

    @Test
    void TestCreateLocation() {
        when(service.createLocation(any())).thenReturn(budapest);

        LocationDto result = controller.createLocation(new CreateLocationCommand());

        assertEquals("Budapest", result.getName());
    }

    @Test
    void testUpdateLocation() {
        when(service.updateLocation(anyLong(), any())).thenReturn(budapest);

        LocationDto result = controller.updateLocation(1L, new UpdateLocationCommand());

        assertEquals("Budapest", result.getName());
    }

    @Test
    void TestDeleteLocationById() {
        controller.deleteLocationById(1L);
        verify(service,times(1)).deleteLocationById(anyLong());
    }

}