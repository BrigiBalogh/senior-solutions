package bikes;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeService {

    private List<Bike> bikes = new ArrayList<>();

    private void readFromFile() {
        try (BufferedReader reader = Files.newBufferedReader(Path.of("bikes.csv"))){
            String line;
            while ((line = reader.readLine()) != null ){
                Bike actual = processLine(line);
                bikes.add(actual);
            }

        }catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file", ioe);
        }
    }

    private Bike processLine(String line) {
        String[] parts = line.split(";");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime actualTime = LocalDateTime.parse(parts[2], formatter);
        return new Bike(parts[0], parts[1], actualTime, Double.parseDouble(parts[3]));
    }


    public List<Bike> getBikes() {
        if (bikes.isEmpty()) {
            readFromFile();
        }
        return bikes;
    }

    public List<String> getUserId() {
        return bikes.stream()
                .map(Bike::getLastUserId)
                .collect(Collectors.toList());
    }


}
