import java.util.ArrayList;
import java.util.Arrays;

public class Nav {

    StartMenu start = new StartMenu();
    private User u;
    public FileIO fileIO = new FileIO();
    private TextUI textUI = new TextUI();
    private Connector connector = new Connector();


    public Nav(User u) {
        String input = textUI.getUserInput(" Hello \n Press 1 if you would like to acces online files \n Press 2 if you would like to acces local files");
       try {
           connector.connection(Integer.parseInt(input));
       }catch (NumberFormatException e) {
           System.out.println("Entered value does not exist, please try again");
        }
       this.u = u;
    }

    public void mainMenu() {

        String input = "";
        while (!input.equalsIgnoreCase("8")) {

            ArrayList<String> options = new ArrayList<String>(Arrays.asList("Search by movie title", "Search by series title", "Search by category", "View saved movie", "View saved series ", "View watched movies", "View watched series ", "Logout"));

            input = textUI.getUserInput("Please select one of the following", options);
            switch (Integer.parseInt(input)) {
                case 1:
                    searchByTitle(true);
                    break;
                case 2:
                    searchByTitle(false);
                    break;
                case 3:
                    searchByCategory();
                    break;
                case 4:
                    viewSavedMovie();
                    break;
                case 5:
                    viewSavedSeries();
                    break;
                case 6:
                    viewWatchedMovie();
                    break;
                case 7:
                   viewWatchedSeries();
                   break;
                case 8:
                    start.logout();
                    break;

                default:
                    System.out.println("Please try again");
            }
        }
    }

    private void searchByTitle(boolean isMovie) {
        if (isMovie) {
            String input = textUI.getUserInput("Write the title of the movie you wish to watch");
            Movie n = connector.readMovieData("title", input);
            if (n == null) {
                searchByTitle(true);
            } else if (u.getAge() < 18 && n.getAge()) {
                System.out.println("You are not old enough to see this movie, please try again");
                searchByTitle(true);
            } else {
                movieAction(n);
            }
        } else {
            String input = textUI.getUserInput("Write the title of the series you wish to watch");
            Series s = connector.readSeriesData("title", input);
            if (s == null) {
                searchByTitle(false);
            } else if (u.getAge() < 18 && s.getAge()) {
                System.out.println("You are not old enough to see this series, please try again");
                searchByTitle(false);
            } else {
                seriesAction(s);
            }
        }
    }

    public void searchByCategory(){
        String userInput = textUI.getUserInput("Please type 1 for movie and 2 for series");

        ArrayList<String> cat = connector.searchCategories(Integer.parseInt(userInput));

        String input = textUI.getUserInput("Please select a categorie", cat);

        ArrayList<Content> result = connector.movieCat(cat.get(Integer.parseInt(input)-1),Integer.parseInt(userInput));

        input = textUI.getUserInput("Please select one of the movies", result);

        movieAction(result.get(Integer.parseInt(input)-1));
    }

    private void movieAction(Content mov) {
        ArrayList<String> options = new ArrayList(Arrays.asList("Play movie", "Add movie to list", "Remove movie from list", "Go back to the main menu"));
        String input = textUI.getUserInput("" + mov.getTitle() + " Please select one of the following", options);
        switch (Integer.parseInt(input)) {
            case 1:
                System.out.println(mov);
                if (mov instanceof Movie) {
                    u.setWatchedMovies(mov.getID());
                }else {
                    u.setSavedSeries(mov.getID());
                }
                break;
            case 2:
                if(u.getSavedMovies().contains(mov.getID())) {
                    System.out.println("This movie already exisist, please try again");
                    movieAction(mov);
                } else {
                    u.setSavedMovies(mov.getID());
                }
                break;
            case 3:
                u.removeSavedMovie(mov.getID());
                break;
            case 4:
                mainMenu();
                break;
            default:
                System.out.println("Please try again");
                movieAction(mov);
        }
        connector.updateUserData(u);
    }

    private void seriesAction(Series ser) {
        ArrayList<String> options = new ArrayList(Arrays.asList("Play series", "Add series to list", "Remove series from list", "Go back to the main menu"));
        String input = textUI.getUserInput("" + ser.getTitle() + " Please select one of the following", options);
        switch (Integer.parseInt(input)) {
            case 1:
                System.out.println(ser);
                u.setWatchedSeries(ser.getID());
                break;
            case 2:
                if(u.getSavedSeries().contains(ser.getID())){
                    System.out.println("This movie already exisist, please try again");
                    seriesAction(ser);
                }else {
                    u.setSavedSeries(ser.getID());
                }
                break;
            case 3:
                u.removeSavedSeries(ser.getID());
                break;
            case 4:
                mainMenu();
                break;
            default:
                System.out.println("Please try again");
                seriesAction(ser);
        }
        connector.updateUserData(u);
    }

    private void viewSavedMovie() {
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < u.getSavedMovies().size(); i++) {
            Movie movie = connector.readMovieData("ID", String.valueOf(u.getSavedMovies().get(i)));
            movies.add(movie);

        }

        ArrayList<String> options = new ArrayList();
        options.add("Go back to main menu");
        for (int i = 0; i < movies.size(); i++) {
            options.add(movies.get(i).getTitle());
        }

        String input = textUI.getUserInput("Please select your movie", options);
        if(Integer.parseInt(input) == 1 ){
         mainMenu();
        }
        movieAction(movies.get(Integer.parseInt(input) - 2 ));

    }

    private void viewSavedSeries(){
        ArrayList<Series> series = new ArrayList<>();
        for (int i = 0; i < u.getSavedSeries().size(); i++) {
            Series serie = connector.readSeriesData("ID", String.valueOf(u.getSavedSeries().get(i)));
            series.add(serie);

        }
        ArrayList<String> options = new ArrayList();
        options.add("Go back to main menu");
        for (int i = 0; i < series.size(); i++) {
            options.add(series.get(i).getTitle());
        }
        String input = textUI.getUserInput("Please select your series", options);
        if(Integer.parseInt(input) == 1 ){
            mainMenu();
        }
        seriesAction(series.get(Integer.parseInt(input) - 2));
    }


    private void viewWatchedMovie(){
       ArrayList<Movie> movies = new ArrayList<>();
       for(int i = 0; i < u.getWatchedMovies().size(); i++){
           Movie movie = connector.readMovieData("ID", String.valueOf(u.getWatchedMovies().get(i)));
           movies.add(movie);
       }
        ArrayList<String> options = new ArrayList();
        options.add("Go back to main menu");
       for(int i = 0 ; i < movies.size(); i++){
           options.add(movies.get(i).getTitle());
       }
       String input = textUI.getUserInput("Please select your movie", options);
        if(Integer.parseInt(input) == 1 ){
            mainMenu();
        }
        movieAction(movies.get(Integer.parseInt(input) - 2 ));
    }

    private void viewWatchedSeries(){
        ArrayList<Series> series = new ArrayList<>();
        for(int i = 0; i < u.getWatchedSeries().size(); i++){
            Series serie = connector.readSeriesData("ID", String.valueOf(u.getWatchedSeries().get(i)));
            series.add(serie);
        }
        ArrayList<String> options = new ArrayList();
        options.add("Go back to main menu");
        for (int i = 0; i < series.size(); i++) {
            options.add(series.get(i).getTitle());
        }
        String input = textUI.getUserInput("Please select your series", options);
        if(Integer.parseInt(input) == 1 ){
            mainMenu();

        }
        seriesAction(series.get(Integer.parseInt(input) - 2));
    }
}
