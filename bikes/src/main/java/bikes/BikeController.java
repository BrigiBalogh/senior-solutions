package bikes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BikeController {

    private final BikeService bikeService;


    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/history")
    public List<Bike> bikesList() {
       /* return new ArrayList<>(List.of(
                new Bike("FH675","US3434", LocalDateTime.of(2021, 06, 2, 17,12,50),0.8)

        ));*/
        return bikeService.getBikes();
    }

    @GetMapping("/users")
    public List<String> userIdList(){
        return bikeService.getUserId();
    }
}
