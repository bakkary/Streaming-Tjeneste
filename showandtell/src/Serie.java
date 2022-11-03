import java.util.ArrayList;

public class Serie extends Content{
    private int startDate;
    private int endDate;
    private ArrayList[][] season = new ArrayList[0][0];

    public Serie(String title, ArrayList category, float rating, int startDate, int endDate, ArrayList[][] season, int ID) {
        super(title, category, rating, ID);
        this.startDate = startDate;
        this.endDate = endDate;
        this.season = season;
    }

    public String getYear() {
        return startDate + "-" + endDate;
    }

    public ArrayList play(int s, int e) {
        return season[s][e];
    }
}
