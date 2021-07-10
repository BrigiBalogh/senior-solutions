package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    private AtomicLong id = new AtomicLong();

    private ModelMapper modelMapper;

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.locations =  new ArrayList<>();
        locations.add(new Location(1l, "Budapest",47.497912,-19.040235));
        locations.add(new Location(2l, "London",51.5,0));
        locations.add(new Location(3l, "Quito",0, -78.5));
        locations.add(new Location(4l, "Manila",0, -123.6));
    }

    private List<Location>locations;

    public List<Location> getLocations() {

        return new ArrayList<>(locations);
    }

    public  List<LocationDto> getLocationsOtherVariable(Optional<String>name ) {

       Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();

        List<Location> filtered =locations.stream()
                .filter(l -> name.isEmpty() || l.getName().equalsIgnoreCase(name.get()))
               .collect(Collectors.toList());
        return modelMapper.map(filtered,targetListType );
    }

    public LocationDto findLocationByName(String name) {
        return modelMapper.map(
                locations.stream()
                        .filter(location -> location.getName().equalsIgnoreCase(name)).findFirst()
                        .orElseThrow(() -> new LocationNotFoundException("Location not found by: " + name)),
                LocationDto.class);
    }

    public LocationDto getLocationById(long id) {
        return modelMapper.map(findById(id), LocationDto.class);

    }

    private Location findById(Long id) {
        return locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find:" + id));
    }


    public LocationDto createLocation(CreateLocationCommand command) {
        Location location =
                new Location(id.incrementAndGet(), command.getName(),
                        command.getLat(), command.getLon());
        locations.add(location);

        return modelMapper.map(location, LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {

        Location result = findById(id);
        if (result.getName() != command.getName()) {
            result.setName(command.getName());
        }
        if(result.getLat() != command.getLat()){
            result.setLat(command.getLat());
        }
        if( (result.getLon() != command.getLon())){

            result.setLon(command.getLon());
        }
        return modelMapper.map(result, LocationDto.class);
    }

    public void deleteLocationById(long id) {

        Location location = findById(id);
        locations.remove(location);

    }

    public void deleteAll() {
        locations.clear();

        id = new AtomicLong();
    }

    public LocationDto findLocationById(long id) {

        return modelMapper.map(locations.stream()
                        .filter(e -> e.getId() == id).findAny()
                        .orElseThrow(() -> new IllegalArgumentException("Location not found: " + id)),
                LocationDto.class);
    }
}
