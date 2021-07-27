package movie;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {

    private ModelMapper mapper;

    private MovieRepository repository;

    public List<MovieDTO> getMovies() {
        return repository.findAll().stream()
                .map(m-> mapper.map(m, MovieDTO.class))
                .toList();
    }


    public MovieDTO createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(command.getTitle());
        repository.save(movie);
        return mapper.map(movie, MovieDTO.class);
    }

    @Transactional
    public MovieDTO addNewRating(Long id, CreateRatingCommand command) {
        Movie movie = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Can not find movie !"));
        movie.addRating(command.getRating());

        return mapper.map(movie, MovieDTO.class);
    }
}
