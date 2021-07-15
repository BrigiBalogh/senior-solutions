package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "activities")
public class Activity {

  @Id
 // @GeneratedValue(strategy = GenerationType.AUTO)/@GeneratedValue(strategy = GenerationType.IDENTITY)
  @GeneratedValue(generator = "Act_Gen")
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
  //@PrePersist()
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt  ;

  @ElementCollection
  @CollectionTable(name ="LABELS",joinColumns = @JoinColumn(  name = "AC_ID"))
  @Column(name = "LABELS")
  private List<String> labels;

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

  @Override
  public String toString() {
    return "Activity{" +
            "id=" + id +
            ", startTime=" + startTime +
            ", desc='" + desc + '\'' +
            ", type=" + type +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
  }
}
/* docker run -d -e MYSQL_DATABASE=activitytracker -e MYSQL_USER=activitytracker -e MYSQL_PASSWORD=activitytracker -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -p 3307:3307 --name activitytracker-mysql mysql
 */