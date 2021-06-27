package training.hourofworks;

import java.util.List;

@RestController
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/")
    public List<Movie> listMovie(){
        return movieService.listMovies();
    }

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }
}
