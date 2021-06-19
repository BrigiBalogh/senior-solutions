package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DistanceServiceTest {

    @Mock
    LocationRepository repository;

    @InjectMocks
    DistanceService service;

  /*  @Test
    void testCalculateDistance() {
        when(repository.findByName("Quito"))
                .thenReturn(Optional.of(new Location("Quito",0,-78.5)));
        when(repository.findByName("Budapest"))
                .thenReturn(Optional.of(new Location("Budapest",47.497912,-19.040235)));

        assertEquals(10611.89,service.calculateDistance("Quito","Budapest").get(),0,01);
        verify(repository).findByName(argThat(l -> l.equals("Budapest")));
    }*/

    @Test
    void testCalculateDistanceWithOptionalEmpty() {
        when(repository.findByName("Quito"))
                .thenReturn(Optional.of(new Location("Quito",0,-78.5)));
        when(repository.findByName("Budapest"))
                .thenReturn(Optional.empty());

        assertEquals(Optional.empty(),service.calculateDistance("Quito","Budapest"));
        verify(repository).findByName(argThat(l -> l.equals("Budapest")));
    }


}
