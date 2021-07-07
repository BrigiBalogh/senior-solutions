package movie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @Mock
    MovieService service;

    @InjectMocks
    MovieController controller;

    @Test
    void createMovie() {
        when(service.createMovie(any())).thenReturn(new MovieDto("Titanic", 100, 4.6));

        MovieDto result = controller.createMovie(new CreateMovieCommand("Titanic", 100));

        assertEquals("Titanic", result.getTitle());
    }

}