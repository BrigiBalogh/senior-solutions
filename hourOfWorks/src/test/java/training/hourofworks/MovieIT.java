package training.hourofworks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MovieIT {

    @Autowired
    MovieController movieController;


    @Test
    void testListMovies(){
        List<Movie>movies = movieController.listMovies();
        assertThat(movies).isEqualTo(List.of(
                new Movie("Titanic", 120, 4.7),
                new Movie("Thor", 121, 4.5)
        ));
    }
}
