import java.util.ArrayList;

public interface Expresso {




    String[] readSeriesData(String field, String query);

    String[] readMovieData(String field, String query);

    String[] readUserData(String username, String password);

    ArrayList searchCategories(int cat);

    ArrayList <String> movieCat(String field, int userInput);

    void writeUserData(User u);

    void updateUserData(User u);
}
