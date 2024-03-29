package locations;

public class Location {

    private String name;
    private double lat;
    private double lon;

    public Location(String name, double lat, double lon) {
        if((lat < -90 || lat > 90) || lon < -180 ||  lon >180) {
            throw new IllegalArgumentException("Invalid location!");
        }
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public boolean isOnEquator() {
        if (lat == 0) {
            return true;
        }
        return false;
    }

    public  boolean isOnPrimeMeridian() {
        if(lon == 0) {
            return true;
        }
        return false;
    }

    public double distanceFrom(Location other) {
        return distanceFrom(this.lat, other.lat, this.lon, other.lon);
    }

    public static double distanceFrom(double lat1, double lat2, double lon1,
                                  double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c ;

        return distance;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
