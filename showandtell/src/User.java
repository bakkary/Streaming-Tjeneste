import java.util.ArrayList;

public class User {

    private int age;
    private String username;
    private String password;
    private static int ID;

    private ArrayList<Movie> watchedMovies = new ArrayList<>;
    private ArrayList<Movie> savedMovies = new ArrayList<>;

    public User(String username, String password, int age, int ID) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.ID = ID;
    }

    public void watchedMovies() {

    }

    public void savedMovies() {

    }

    public void setwatchedMovies(ArrayList watchedMovies) {
    //add arrayList
    }

    public void setsavedMovies(ArrayList SavedMovies) {
        //add arrayList
    }

    public User(int age, String username, String password) {

    }
}
