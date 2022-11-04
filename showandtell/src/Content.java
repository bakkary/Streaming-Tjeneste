import java.util.ArrayList;

public abstract class Content {
    private static int ID;
    private String title;
    private ArrayList<String> categories;
    private float rating;
    Content(String title, ArrayList categories, float rating, int ID) {
        this.title = title;
        this.categories = categories;
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
        return categories;
    }

    public float getRating() {
        return rating;
    }
}
