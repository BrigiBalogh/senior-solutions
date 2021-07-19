package activitytrackerspringbootsolution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "track_points")
@NamedQuery(name = "findTrackPointCoordinatesByDate",
        query = "select new activitytrackerspringbootsolution.Coordinate(t.lat, t.lon) from TrackPoint t where t.time > :time")
public class TrackPoint {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate time;
    private double lat;
    private double lon;

    @ManyToOne
    @JoinColumn(name = "ac_id")
    private Activity activity;


}
