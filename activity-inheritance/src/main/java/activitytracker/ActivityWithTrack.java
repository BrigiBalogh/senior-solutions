package activitytracker;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class ActivityWithTrack  extends Activity {

    private int distance;
    private int duration;

    public ActivityWithTrack() {
    }

    public ActivityWithTrack(LocalDateTime startTime, String description, int distance, int duration) {
        super(startTime, description);
        this.distance = distance;
        this.duration = duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
