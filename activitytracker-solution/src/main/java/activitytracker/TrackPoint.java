package activitytracker;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "track_points")
@NamedQuery(name = "findTrackPointCoordinatesByDate",
        query = "select new activitytracker.Coordinate(t.lat, t.lon) from TrackPoint t where t.time > :time")
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


    public TrackPoint() {
    }


    public TrackPoint(LocalDate time, double lat, double lon) {
        this.time = time;
        this.lat = lat;
        this.lon = lon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "TrackPoint{" +
                "id=" + id +
                ", time=" + time +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
