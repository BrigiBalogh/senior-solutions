package activitytracker;

import javax.persistence.*;

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


  public City() {
  }

  public City(String name, int population, Area area) {
    this.name = name;
    this.population = population;
    this.area = area;
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

  public int getPopulation() {
    return population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }

  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  @Override
  public String toString() {
    return "City{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", population=" + population +
            ", area=" + area +
            '}';
  }
}
