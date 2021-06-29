package training.hourofworks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

   // @Autowired
  //  MovieController movieController;
    @Mock
    MovieService movieService;

    @InjectMocks
    MovieController movieController;


    @Test
    void listMovie() {
        when(movieService.listMovies()).thenReturn(List.of(
                new Movie("Titanic", 120, 4.7),
                new Movie("Thor", 121, 4.5)));
        List<Movie>movies = movieController.listMovies();
        assertThat(movies).isEqualTo(List.of(
                new Movie("Titanic", 120, 4.7),
                new Movie("Thor", 121, 4.5)
        ));

    }

}