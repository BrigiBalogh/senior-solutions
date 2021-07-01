package bikes;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeService {

    private List<Bike> bikes = new ArrayList<>();



    public List<Bike> getBikes() {
        return bikes;
    }

    public List<String> getUserId() {
        return bikes.stream()
                .map(Bike::getId)
                .collect(Collectors.toList());
    }


}
