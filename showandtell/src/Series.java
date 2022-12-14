import java.util.ArrayList;

public class Series extends Content{
    private int startDate;
    private int endDate;
    private ArrayList<String> season;

    public Series(String title, ArrayList categories, float rating, int startDate, int endDate, ArrayList<String> season, int ID, boolean age) {
        super(title, categories, rating, ID, age);
        this.startDate = startDate;
        this.endDate = endDate;
        this.season = season;
    }

    public String getYear() {
        return startDate + "-" + endDate;
    }

}
