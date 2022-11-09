import java.util.ArrayList;

public abstract class Content {
    private int ID;
    private String title;
    private ArrayList<String> categories;
    private float rating;
    private boolean age;
    Content(String title, ArrayList categories, float rating, int ID, boolean age) {
        this.title = title;
        this.categories = categories;
        this.rating = rating;
        this.ID = ID;
        this.age = age;
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

    @Override
    public String toString() {
        return getTitle();
    }

    public boolean getAge() {
        return age;
    }
}
