package bonus.doggo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoggoRepository  extends JpaRepository<Dog, Long> {


}
