package bikes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BikeControllerTest {

    @Mock
    BikeService service;

    @InjectMocks
    BikeController controller;

    @Test
    void getHistoryTest() {
        when(service.getBikes()).thenReturn(List.of(
                new Bike("FH636","US336", LocalDateTime.of(2021, 06, 23, 9,36,12),1.9),
                new Bike("FH631","US346",LocalDateTime.of(2021, 06, 24, 8,53,21),2.9)
        ));

        assertThat(controller.getBikes()).hasSize(2).extracting(Bike::getId)
        .contains("FH636","FH631");

        verify(service).getBikes();
    }

}