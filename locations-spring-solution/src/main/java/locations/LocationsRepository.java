package locations;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LocationsRepository extends JpaRepository<Location, Long> {

    Location findByName(String name);

    //List<Location> findAllByPrefix(String prefix);

}
