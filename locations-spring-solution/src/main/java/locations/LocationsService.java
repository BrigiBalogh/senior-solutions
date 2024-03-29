package locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationsService {


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LocationsRepository repository;
    private List<Location> locations = new ArrayList<>();

   /* public LocationsService(ModelMapper modelMapper, LocationsRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }*/

    public List<LocationDto> getLocations() {
        return repository.findAll().stream()
                .map(l -> modelMapper.map(l, LocationDto.class))
                .collect(Collectors.toList());
    }


    public LocationDto findLocationByName(String name) {
        return modelMapper.map(repository.findByName(name),LocationDto.class);
    }

    public LocationDto findLocationById(long id) {
        Optional<Location> optLoc = repository.findById(id);
        if (optLoc.isEmpty())
            return null;
        else
            return modelMapper.map(optLoc.get(), LocationDto.class);

    }


    public LocationDto createLocation(CreateLocationCommand command) {
        Location location =
                new Location(command.getName(), command.getLat(), command.getLon());
        repository.save(location);
        //  log.info("Location has been created");
        //   log.debug("Location has been created with name {}", command.getName())
        return modelMapper.map(location, LocationDto.class);
    }


    @Transactional
    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location result = repository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Location not found"));
        result.setName(command.getName());
        result.setLat(command.getLat());
        result.setLon(command.getLon());
        return modelMapper.map(result, LocationDto.class);
    }


    public void deleteLocationById(long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
        locations.clear();
    }
}
