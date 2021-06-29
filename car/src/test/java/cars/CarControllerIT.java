package cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CarControllerIT {

    @Autowired
    CarController carController;


    @Test
    void getAllCars() {
        List<Car> cars = carController.getAllCars();

        assertThat(cars)
                .hasSize(2)
                .extracting(Car::getBrand)
                .contains("jeep","Cadillac ");
    }

    @Test
    void testGetBrands() {
        Set<String> brands = carController.getBrands();

        assertThat(brands)
                .hasSize(2)
                .contains("jeep","Cadillac ");

    }
}
