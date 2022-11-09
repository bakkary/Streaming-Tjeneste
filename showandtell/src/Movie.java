import java.util.ArrayList;

public class Movie extends Content {
    private int year;

    Movie(String title, ArrayList categories, float rating, int year, int ID, boolean age) {
        super(title, categories, rating, ID, age);
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void play() {

    }
}
