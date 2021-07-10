package movie;


import org.modelmapper.internal.bytebuddy.dynamic.DynamicType;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/movies")
public class MovieController {



    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping
    public List<MovieDto> getMoviess(@RequestParam Optional<String> prefix) {
        return movieService.getMoviess(prefix);
    }



    @GetMapping("/{id}")
    public MovieDto getMovieById(@PathVariable("id") long id) {
      return movieService.getMovieById(id);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto createMovie(@RequestBody CreateMovieCommand command)
    {
        return movieService.createMovie(command);
    }


    @PostMapping("/{id}/rating")
    public MovieDto updataMovieRating(@PathVariable("id") long id, NewMovieRatingCommand command) {
        return movieService.addRating(id,command);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieById(@PathVariable long id) {
        movieService.deleteMovieById(id);
    }


    @GetMapping("/{id}/rating")
    public MovieDto getRateByMovieId(@PathVariable("id") long id) {
        return movieService.getRateByMovie(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Problem> handleExceptionWhenNotFound(IllegalArgumentException iae) {
        Problem problem =
                Problem.builder()
                .withType(URI.create("movie/not-found"))
                .withTitle("No movie")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);

    }



}
