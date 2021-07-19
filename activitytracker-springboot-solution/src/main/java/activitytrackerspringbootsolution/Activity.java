package activitytrackerspringbootsolution;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
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


}