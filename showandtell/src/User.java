import java.util.ArrayList;

public class User {
    private int ID;
    private int age;
    private String username;
    private String password;
    private ArrayList<Integer> watchedMovies = new ArrayList();
    private ArrayList<Integer> savedMovies = new ArrayList();

    public User(int ID, String username, String password, int age, ArrayList watchedMovies, ArrayList savedMovies) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.ID = ID;
        this.watchedMovies = watchedMovies;
        this.savedMovies = savedMovies;
    }

    public void setWatchedMovies(Integer watched) {
        watchedMovies.add(watched);
    }

    public void setSavedMovies(Integer saved) {
        savedMovies.add(saved);
    }

    public ArrayList<Integer> getWatchedMovies() {
        return watchedMovies;
    }

    public ArrayList<Integer> getSavedMovies() {
        return savedMovies;
    }

    public void removeSavedMovie(int ID){

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
