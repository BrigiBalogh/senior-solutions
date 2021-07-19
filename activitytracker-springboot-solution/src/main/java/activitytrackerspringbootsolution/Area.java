package activitytrackerspringbootsolution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "areas")
public class Area {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long id;

    @Column(name = "area_name")
    private String name;


    @ManyToMany
    private List<Activity> activities = new ArrayList<>();

    @OneToMany (mappedBy = "area", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @MapKey(name = "name")
    private Map<String,City> cities = new HashMap<>();




    public Area(String name) {
        this.name = name;
    }




    public void addActivity(Activity activity) {
        activities.add(activity);
        activity.getAreas().add(this);
    }
}
