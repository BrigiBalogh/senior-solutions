package movie;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie")
public class Movie {
    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "movie_title")
    private String title;

    @ElementCollection
    private List<Integer> ratings;

    public Movie(String title) {
        this.title = title;
    }


    public void addRating(int rating) {
        if (ratings== null) {
            ratings = new ArrayList<>();
        }
        ratings.add(rating);
    }
}
