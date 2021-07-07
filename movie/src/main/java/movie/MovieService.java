package movie;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {


    private List<Movie> movies = new ArrayList<>();

    private AtomicLong id = new AtomicLong();

    private ModelMapper modelMapper;

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDto> getMovies() {
        Type targetListType = new TypeToken<List<MovieDto>>(){}.getType();
        return modelMapper.map(movies, targetListType);
    }

    public List<MovieDto> getMoviess(Optional<String> prefix) {

            return movies.stream()
                    .filter(movie -> prefix.isEmpty() || movie.getTitle().toLowerCase().startsWith(prefix.get().toLowerCase()))
                    .map(movie -> modelMapper.map(movie,MovieDto.class))
                    .collect(Collectors.toList());
    }


    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(id.incrementAndGet(), command.getTitle(), command.getLength() );
        movies.add(movie);

        return modelMapper.map(movie, MovieDto.class);
    }



    public MovieDto addRating(long id, NewMovieRatingCommand command) {

        Movie movie = findById(id);
        movie.addRating(command.getRating());

        return modelMapper.map(movie, MovieDto.class);
    }

    private Movie findById(long id) {
        return movies.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find:" + id));
    }



    public MovieDto getMovieById(long id) {
        return modelMapper.map(findById(id),MovieDto.class);
    }

    public void deleteMovieById(long id) {
        Movie movie = findById(id);
        movies.remove(movie);

    }

    public MovieDto getRateByMovie(long id) {
        Movie movie = findById(id);
        return modelMapper.map(movie, MovieDto.class);
    }
}