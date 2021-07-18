package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "activities")
@SecondaryTable(name="activity_details",
        pkJoinColumns=@PrimaryKeyJoinColumn(name="ac_id"))
public class Activity {

  @Id
 // @GeneratedValue(strategy = GenerationType.AUTO)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
 // @GeneratedValue(generator = "Act_Gen")
 /* @TableGenerator(name = "Act_Gen",
          table = "act_id_gen",
          pkColumnName = "id_gen",
          valueColumnName = "id_val",
          initialValue=10000,
          allocationSize=100)*/
  private Long id;
  @Column(name = "start_time", nullable = false)
  private LocalDateTime startTime;
  @Column(name = "activity_description",length = 200, nullable = false)
  private String desc;

  @Column(name = "activity_type", length = 20, nullable = false)
  @Enumerated(EnumType.STRING)
  private ActivityType  type = ActivityType.RUNNING;

  @Column(name ="created_at" )
 private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt  ;

  @ElementCollection
  @CollectionTable(name ="labels",joinColumns = @JoinColumn(  name = "ac_id"))
  @Column(name = "label")
  private List<String> labels;

@Column(table = "activity_details")
  private int  distance;

    @Column(table = "activity_details")
  private int  duration;


 // @ElementCollection
//  @CollectionTable(name ="trackPoints",joinColumns = @JoinColumn(  name = "tp_id"))
  @OneToMany(mappedBy = "activity",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  @OrderColumn(name = "track_point")
  @OrderBy("time")
  private List<TrackPoint> trackPoints;


  @ManyToMany(mappedBy = "activities")
  @JoinTable(name="ac_area",
          joinColumns=@JoinColumn(name="AC_ID"),
          inverseJoinColumns=@JoinColumn(name="area_ID"))
  private List<Area> areas = new ArrayList<>();

  public Activity() {
  }

  public Activity(LocalDateTime startTime, String desc, ActivityType type) {
    this.startTime = startTime;
    this.desc = desc;
    this.type = type;
  }

  public Activity(LocalDateTime startTime, String desc, ActivityType type, LocalDateTime createdAt,
                  LocalDateTime updatedAt) {
    this.startTime = startTime;
    this.desc = desc;
    this.type = type;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }


    public Activity(LocalDateTime startTime, String desc, ActivityType type, LocalDateTime createdAt, LocalDateTime updatedAt, List<String> labels, int distance, int duration, List<TrackPoint> trackPoints, List<Area> areas) {
        this.startTime = startTime;
        this.desc = desc;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.labels = labels;
        this.distance = distance;
        this.duration = duration;
        this.trackPoints = trackPoints;
        this.areas = areas;
    }

    @PrePersist
  public void prePersist() {
      this.createdAt = LocalDateTime.now();
      this.updatedAt = this.createdAt;
  }

  @PreUpdate
  public void preUpdate() {
      this.updatedAt = LocalDateTime.now();
  }

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public ActivityType getType() {
    return type;
  }

  public void setType(ActivityType type) {
    this.type = type;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public List<String> getLabels() {
    return labels;
  }

  public void setLabels(List<String> labels) {
    this.labels = labels;
  }

  public List<TrackPoint> getTrackPoints() {
    return trackPoints;
  }

  public void setTrackPoint(List<TrackPoint> trackPoints) {
    this.trackPoints = trackPoints;
  }

    public void setTrackPoints(List<TrackPoint> trackPoints) {
        this.trackPoints = trackPoints;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
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

    public void addTrackPoint(TrackPoint trackPoint) {
        if (trackPoints == null) {
            trackPoints = new ArrayList<>();
        }
        trackPoints.add(trackPoint);
        trackPoint.setActivity(this);
    }

    public void addArea(Area area ) {
      if (areas == null) {
          areas = new ArrayList<>();
      }
      areas.add(area);
        area.getActivities().add(this);
    }


  @Override
  public String toString() {
    return "Activity{" +
            "id=" + id +
            ", startTime=" + startTime +
            ", desc='" + desc + '\'' +
            ", type=" + type +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", labels=" + labels +
            ", trackPoints=" + trackPoints +
            '}';
  }
}
/* docker run -d -e MYSQL_DATABASE=activitytracker -e MYSQL_USER=activitytracker -e MYSQL_PASSWORD=activitytracker -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -p 3307:3306 --name activitytracker-mysql mysql
 */