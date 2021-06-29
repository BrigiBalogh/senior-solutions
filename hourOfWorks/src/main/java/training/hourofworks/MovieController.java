package training.hourofworks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/")
    public List<Movie> listMovies(){
        return movieService.listMovies();
    }

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }
}
