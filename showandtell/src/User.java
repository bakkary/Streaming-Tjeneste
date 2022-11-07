import java.util.ArrayList;

public class User {
    private int age;
    private String username;
    private String password;
    private static int ID;

    private ArrayList<Movie> watchedMovies = new ArrayList();
    private ArrayList<Movie> savedMovies = new ArrayList();

    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public void setWatchedMovies(Movie watched) {
        watchedMovies.add(watched);
    }

    public void setSavedMovies(Movie saved) {
        savedMovies.add(saved);
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public ArrayList<Movie> getSavedMovies() {
        return savedMovies;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static int getID() {
        return ID;
    }

}
