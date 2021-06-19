package locations;

import java.util.Optional;

public class DistanceService {

    private LocationRepository locationRepository;

    public DistanceService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<Double> calculateDistance(String name1, String name2) {
        Optional<Location>location = locationRepository.findByName(name1);
        Optional<Location>locationAnother = locationRepository.findByName(name2);

        if (location.isPresent() && locationAnother.isPresent()) {
            return Optional.of(location.get().distanceFrom(locationAnother.get()));
        }
        return Optional.empty();
    }
}
