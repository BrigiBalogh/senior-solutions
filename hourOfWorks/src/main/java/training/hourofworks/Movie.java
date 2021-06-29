package training.hourofworks;

import java.util.Objects;

public class Movie {

    private String title;
    private int length;
    private double rating;

    public Movie(String title, int length, double rating) {
        this.title = title;
        this.length = length;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", length=" + length +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return length == movie.length && Double.compare(movie.rating, rating) == 0 && Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, length, rating);
    }
}
