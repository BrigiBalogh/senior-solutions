package movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private long id;
    private String title;
    private int length;
    private double averageRating;

    private List<Integer> ratings = new ArrayList<>();


    public Movie(long id, String title, int length) {
        this.id = id;
        this.title = title;
        this.length = length;
    }

    public void addRating(int rating) {
        ratings.add(rating);

        averageRating = ratings.stream()
                .collect(Collectors.summarizingInt(Integer::intValue)).getAverage();

    }
}
/*
    public void addRating(int rating){
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        ratings.add(rating);
        averageRating = ratings.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics()
                .getAverage();
    }  */