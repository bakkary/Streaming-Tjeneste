import java.util.ArrayList;

public class Movie extends Content {
    private int year;

    Movie(String title, ArrayList category, float rating, int year, int ID) {
        super(title, category, rating, ID);
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void play() {

    }
}
