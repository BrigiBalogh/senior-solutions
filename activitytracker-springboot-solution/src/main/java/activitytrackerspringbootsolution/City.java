package activitytrackerspringbootsolution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class City {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;


    private String name;
    private int population;

    @ManyToOne
    private Area area;




    public City(String name, int population, Area area) {
        this.name = name;
        this.population = population;
        this.area = area;
    }
}





