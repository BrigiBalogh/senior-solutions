package training.hourofworks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {



    @Test
    void listMovies() {
        MovieService movieService = new MovieService();
        List<Movie> movies = List.of(
                new Movie("Titanic", 120, 4.7),
                new Movie("Thor", 121, 4.5));

        List<Movie> movies1 = movieService.listMovies();
        assertEquals(movies1, movies);
    }
}