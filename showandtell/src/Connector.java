import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Connector {
    FileIO fileIO = new FileIO();
    TextUI textUI = new TextUI();

    public boolean connection() {
        String input = textUI.getUserInput(" hello press 1  if you would like to acces online files \n press 2 if you would like to acces local files");
        boolean answear = false;
        if(Integer.parseInt(input) == 1 ){
            System.out.println("going online");
            //SQL sql = new SQL();
            answear = true;
        } else {
            FileIO fileIO = new FileIO();
            System.out.println("going offline");
            answear = false;
        }
        return answear;
    }

    private Map content(String[] c) {
        int ID = Integer.parseInt(c[0]);
        String title = c[1];
        ArrayList<String> categories = new ArrayList<>();
        String[] categoriesArr = c[3].split(",");
        categories.addAll(Arrays.asList(categoriesArr));
        float rating = Float.parseFloat(c[4].replace(",", "."));
        Map map = new HashMap<>();
        map.put("title", title);
        map.put("categories", categories);
        map.put("rating", rating);
        map.put("ID", ID);
        return map;
    }

    private Movie movie(String[] m) {
        Map map = content(m);
        int year = Integer.parseInt(m[2]);
        boolean age = Boolean.parseBoolean(m[5]);
        return new Movie((String) map.get("title"), (ArrayList) map.get("categories"), (Float) map.get("rating"), year, (Integer) map.get("ID"), age);
    }

    private Series series(String[] s) {
        Map map = content(s);
        String[] years = s[2].trim().split("-");
        int startDate = Integer.parseInt(years[0]);
        boolean age = Boolean.parseBoolean(s[6]);
        int endDate = 0;
        if (years.length > 1) {
            endDate = Integer.parseInt(years[1]);
        }
        ArrayList<String> seasons = new ArrayList<>();
        return new Series((String) map.get("title"), (ArrayList) map.get("categories"), (Float) map.get("rating"), startDate, endDate, seasons, (Integer) map.get("ID"), age);
    }

    private User users(String[] result) {
        String[] watchedmovies = result[4].split(",");
        ArrayList<String> watchedMovies = new ArrayList<>(Arrays.asList(watchedmovies));
        String[] savedmovies = result[5].split(",");
        ArrayList<String> savedMovies = new ArrayList<>(Arrays.asList(savedmovies));
        String[] watchedseries = result[6].split(",");
        ArrayList<String> watchedSeries = new ArrayList<>(Arrays.asList(watchedseries));
        String[] savedseries = result[7].split(",");
        ArrayList<String> savedSeries = new ArrayList<>(Arrays.asList(savedseries));

        User user = new User(result[1], result[2], Integer.parseInt(result[3]), Integer.parseInt(result[0]), watchedMovies, savedMovies, watchedSeries, savedSeries);
        return user;
    }

    Movie readMovieData(String field, String query) {
        String[] movieData = fileIO.readMovieData(field, query);
        return movie(movieData);
    }

    Series readSeriesData(String field, String query) {
        String[] seriesData = fileIO.readSeriesData(field, query);
        return series(seriesData);
    }

    User readUserData(String username, String password) {
        String[] userData = fileIO.readUserData(username, password);
        if (userData.length == 0) {
            return null;
        } else {
            return users(userData);
        }
    }

    ArrayList<Content> movieCat(String field, int userInput) {
        ArrayList<String> cat = fileIO.movieCat(field, userInput);
        ArrayList<Content> con = new ArrayList<>();
        for (int i = 0; i < cat.size(); i++) {
            String[] temp = cat.get(i).split(";");
            if (userInput == 1) {
                con.add(movie(temp));
            }else{
                con.add(series(temp));
            }
        }
        return con;
    }

    ArrayList<String> searchCategories(int cat){
        return fileIO.searchCategories(cat);
    }

    void writeUserData(User u){
        fileIO.writeUserData(u);
    }

    void updateUserData(User u){
        fileIO.updateUserData(u);
    }
}
