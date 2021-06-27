package training.hourofworks;

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
}
