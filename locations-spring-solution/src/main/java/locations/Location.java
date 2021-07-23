package locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locations")
public class Location {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    @Column(name = "location_name")
   private String name;

    @Column(name = "location_lat")
   private double lat;
    @Column(name = "location_lon")
   private double lon;


    public Location(String name, double lat, double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }
}

/* docker run -d -e MYSQL_DATABASE=locations -e MYSQL_USER=locations -e MYSQL_PASSWORD=locations -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -p 3306:3306 --name locations-mariadb mariadb  */
