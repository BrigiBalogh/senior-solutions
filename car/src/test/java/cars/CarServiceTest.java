package cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    CarService carService;

    @BeforeEach
    void  setUp() {
        carService = new CarService();
    }

    @Test
    void getAllCars() {
        List<Car> cars =carService.getAllCars();

        assertThat(cars)
                .hasSize(2)
                .extracting(Car::getBrand)
                .contains("jeep","Cadillac ");
    }

    @Test
    void getBrands() {
        Set<String> brands =carService.getBrands();

        assertThat(brands)
                .hasSize(2)
                .contains("jeep","Cadillac ");

    }
}