import java.util.ArrayList;

public abstract class Content {

    public String title;
    public ArrayList<String> category;
    public float rating;
    Content(String title, ArrayList category, float rating) {
        this.title = title;
        this.category = category;
        this.rating = rating;
    }

    public abstract String getTitle();

    public abstract ArrayList getCategories();

    public abstract float getRating();
}
