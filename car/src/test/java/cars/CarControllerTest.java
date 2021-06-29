package cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

   @Mock
   CarService carService;

   @InjectMocks
   CarController  carController;

    @Test
    void getAllCars() {
        List<Car> cars = new ArrayList<>(List.of(
                new Car("jeep"," Grand Cherokee",3,Condition.NORMAL, new ArrayList<KmState>(
                        List.of(new KmState(LocalDate.of(2018, Month.APRIL,18), 50000),
                                new KmState(LocalDate.of(2020,Month.AUGUST,1),10000)
                                ))),
                new Car("Cadillac ","  Escalade",1,Condition.PERFECT, new ArrayList<>(
                        List.of(new KmState(LocalDate.of(2020, Month.APRIL,18), 50000),
                                new KmState(LocalDate.of(2020,Month.AUGUST,1),60000))))));

        when(carService.getAllCars()).thenReturn(cars);

        List<Car> cars2 =carService.getAllCars();

        assertThat(cars2)
                .hasSize(2)
                .extracting(Car::getBrand)
                .contains("jeep","Cadillac ");
        verify(carService, times(1)).getAllCars();
    }

    @Test
    void getBrands() {
        when(carService.getBrands()).thenReturn(Set.of("jeep","Cadillac "));
        Set<String> brands =carService.getBrands();

        assertThat(brands)
                .hasSize(2)
                .contains("jeep","Cadillac ");
        verify(carService).getBrands();

    }
}