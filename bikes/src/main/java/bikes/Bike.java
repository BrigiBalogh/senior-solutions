package bikes;

import java.time.LocalDateTime;
import java.util.Objects;

public class Bike {

    private String id;
    private String lastUserId;
    private LocalDateTime lastReturnDateTime;
    private double lastDistance;

    public Bike(String id, String lastUserId, LocalDateTime lastReturnDateTime, double lastDistance) {
        this.id = id;
        this.lastUserId = lastUserId;
        this.lastReturnDateTime = lastReturnDateTime;
        this.lastDistance = lastDistance;
    }


    public String getId() {
        return id;
    }

    public String getLastUserId() {
        return lastUserId;
    }

    public LocalDateTime getLastReturnDateTime() {
        return lastReturnDateTime;
    }

    public double getLastDistance() {
        return lastDistance;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "id='" + id + '\'' +
                ", lastUserId='" + lastUserId + '\'' +
                ", lastReturnDateTime=" + lastReturnDateTime +
                ", lastDistance=" + lastDistance +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return Double.compare(bike.lastDistance, lastDistance) == 0 && Objects.equals(id, bike.id) && Objects.equals(lastUserId, bike.lastUserId) && Objects.equals(lastReturnDateTime, bike.lastReturnDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastUserId, lastReturnDateTime, lastDistance);
    }
}
