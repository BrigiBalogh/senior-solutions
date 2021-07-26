package bonus.doggo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "dogs")
public class Dog {

    @Id
    @Column(name = "dog_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dog_name")
    private String name;
    @Column(name = "dog_race")
    private String race;
    @Column(name = "dog_age")
    private int age;
    @Column(name = "favorite_game")
    private String favoriteGame;


    public Dog(String name, String race, int age, String favoriteGame) {
        this.name = name;
        this.race = race;
        this.age = age;
        this.favoriteGame = favoriteGame;
    }
}
