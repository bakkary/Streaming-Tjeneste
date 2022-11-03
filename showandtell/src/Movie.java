import java.util.ArrayList;

public class Movie extends Content {
    public static int ID;
    private int year;

    Movie(String title, ArrayList category, float rating, int year) {
        super(title, category, rating);
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public ArrayList getCategories() {
        return category;
    }

    @Override
    public float getRating() {
        return rating;
    }

    public void play() {

    }
}
