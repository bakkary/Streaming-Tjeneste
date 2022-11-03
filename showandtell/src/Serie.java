import java.util.ArrayList;

public class Serie extends Content{
    public static int ID;
    private int startDate;
    private int endDate;
    ArrayList[][] season = new ArrayList[0][0];

    public Serie(String title, ArrayList category, float rating, int startDate, int endDate, ArrayList[][] season) {
        super(title, category, rating);
        this.startDate = startDate;
        this.endDate = endDate;
        this.season = season;
    }

    public String getYear() {
        return startDate + "-" + endDate;
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

    public ArrayList play(int s, int e) {
        return season[s][e];
    }
}
