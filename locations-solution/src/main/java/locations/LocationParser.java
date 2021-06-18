package locations;

public class LocationParser {

    public Location parse(String text) {

        String[] parts = text.split(",");
        String name =parts[0];
        double lat = Double.parseDouble(parts[1]);
        double lon = Double.parseDouble(parts[2]);

        return new Location(name, lat,lon);
    }
}
