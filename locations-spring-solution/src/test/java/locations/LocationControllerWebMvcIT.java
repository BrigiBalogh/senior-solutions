package locations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = LocationsController.class)
public class LocationControllerWebMvcIT {

    @MockBean
    LocationsService locationsService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    LocationDto budapest;
    LocationDto london;
    LocationDto quito;
    LocationDto funchal;

    @BeforeEach
    void init() {
        budapest = new LocationDto("Budapest", 47.497912, 19.040235);
        london = new LocationDto("London", 51.5, 0);
        quito = new LocationDto("Quito", 0, -78.5);
        funchal = new LocationDto("Funchal", 32.66568 , -16.92547);
    }



    @Test
    void testGetLocations() throws Exception {
        when(locationsService.getLocations()).thenReturn(new ArrayList<>(List.of(budapest,london,quito
        )));

        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo("Budapest")));
    }


    @Test
    void testGetLocationById() throws Exception {
        when(locationsService.getLocationById(anyLong())).thenReturn(budapest);


        mockMvc.perform(get("/locations/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Budapest")));
    }


    @Test
    void testGetLocationByName() throws Exception {
        when(locationsService.findLocationByName(anyString())).thenReturn(budapest);


        mockMvc.perform(get("/locations/name/budapest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Budapest")));
    }

    @Test
    void testCreate() throws Exception {
        when(locationsService.createLocation(any())).thenReturn(budapest);
        String createJSON = objectMapper.writeValueAsString(
                new CreateLocationCommand(budapest.getName(), budapest.getLat(), budapest.getLon()));
        mockMvc.perform(
                post("/locations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(createJSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name", equalTo("Budapest")));

    }

    @Test
    void testUpdateLocation() throws Exception {
        when(locationsService.updateLocation(any(),any(UpdateLocationCommand.class))).thenReturn(funchal);
        String createJSON = objectMapper.writeValueAsString(
                new UpdateLocationCommand(funchal.getName(), funchal.getLat(), funchal.getLon()));
        mockMvc.perform(
                put("/locations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Funchal")));

    }


    @Test
    void testDeleteLocationById() throws Exception {

        mockMvc.perform(
                delete("/locations/1"))
                .andExpect(status().isNoContent());
        verify(locationsService.deleteLocationById(anyLong()));

    }
}

