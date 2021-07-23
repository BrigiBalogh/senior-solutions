package locations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationsRepository extends JpaRepository<Location, Long> {

    Location findByName(String name);

    //List<Location> findAllByPrefix(String prefix);

}
