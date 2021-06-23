package locations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LocationService {

    public void writeLocations(Path file, List<Location> locations) {

        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(file))) {
            for(Location location : locations) {
                writer.format("%s,%d,%d\n",location.getName(), location.getLat(), location.getLon() );
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot write the file", ioe);
        }
    }

    public List<Location> readLocations(Path file) {
        List<Location> result = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {

            String line = null;
            while ((line = reader.readLine()) != null) {

                //Location loc = new Location()
                //result.add(line); // TODO
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read from file.", ioe);
        }
        return result;
    }



}
