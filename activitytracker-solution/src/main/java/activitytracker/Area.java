package activitytracker;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    public Area() {
    }

    public Area(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Map<String, City> getCities() {
        return cities;
    }

    public void setCities(Map<String, City> cities) {
        this.cities = cities;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        activity.getAreas().add(this);
    }


}
