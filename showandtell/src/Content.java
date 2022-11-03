import java.util.ArrayList;

public abstract class Content {
    private static int ID;
    private String title;
    private ArrayList<String> category;
    private float rating;
    Content(String title, ArrayList category, float rating, int ID) {
        this.title = title;
        this.category = category;
        this.rating = rating;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList getCategories() {
        return category;
    }

    public float getRating() {
        return rating;
    }
}
