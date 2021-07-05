package bikes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BikeControllerIT {

    @Autowired
    BikeController controller;

    @Test
    public void getHistoryTest() {
        List<Bike> result =controller.bikesList();

        assertThat(result).hasSize(5).extracting(Bike::getId).contains("FH631");
    }


}
