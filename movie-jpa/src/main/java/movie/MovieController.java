package movie;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    public List<MovieDTO> getMovies() {
        return service.getMovies();
    }

    @PostMapping
    public MovieDTO createMovie(@RequestBody CreateMovieCommand command) {
        return service.createMovie(command);
    }

    @PostMapping
    public MovieDTO addNewRating(@PathVariable("id") Long id, @RequestBody CreateRatingCommand command) {
        return service.addNewRating(id,command);
    }
}
