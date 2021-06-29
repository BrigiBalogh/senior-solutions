package cars;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarService {


    private List<Car> cars = new ArrayList<>(List.of(
            new Car("jeep"," Grand Cherokee",3,Condition.NORMAL, new ArrayList<KmState>(
                    List.of(new KmState(LocalDate.of(2018, Month.APRIL,18), 50000),
                            new KmState(LocalDate.of(2020,Month.AUGUST,1),10000)
            ))), new Car("Cadillac ","  Escalade",1,Condition.PERFECT, new ArrayList<>(
                                    List.of(new KmState(LocalDate.of(2020, Month.APRIL,18), 50000),
                                            new KmState(LocalDate.of(2020,Month.AUGUST,1),60000))))));

    public List<Car> getAllCars() {
        return cars;
    }

    public Set<String> getBrands() {
        return cars.stream()
                .map(Car::getBrand)
                .collect(Collectors.toSet());
    }
}
