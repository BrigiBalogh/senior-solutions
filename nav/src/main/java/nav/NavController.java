package nav;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class NavController {

    @Autowired
    private final NavService service;


    @GetMapping("/types")
    public List<Type> getTypes() {
        return service.getTypes();
    }

}
