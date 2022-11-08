import java.util.ArrayList;
import java.util.Arrays;

public class Nav {

    StartMenu start = new StartMenu();
    private User u;
    public FileIO fileIO = new FileIO();
    private TextUI textUI = new TextUI();


    public Nav(User u) {
        this.u = u;
    }

    public void mainMenu() {

        String input = "";
        while (!input.equalsIgnoreCase("6")) {


            ArrayList<String> options = new ArrayList<String>(Arrays.asList("Search by movie title", "Search by series title", "Search by category", "View saved content", "View watched content", "Logout"));

            input = textUI.getUserInput("Please select one of the following", options);
            switch (Integer.parseInt(input)) {
                case 1:
                    searchByTitle(true);
                    break;
                case 2:
                    searchByTitle(false);
                    break;
                case 3:
                    //searchByCategory();
                    break;
                case 4:
                    viewSavedMovie();
                    break;
                case 5:
                    viewSavedSeries();
                    break;
                case 6:
                    start.logout();

                    viewWatchedMovie();
                    break;
                case 7:
                   viewWatchedSeries();

                default:
                    System.out.println("Please try again");
            }
        }
    }

    private void searchByTitle(boolean isMovie) {


        if(isMovie) {
            String input = textUI.getUserInput("write the title of the movie you wish to watch");
            Movie n = fileIO.readMovieData("title", input);
            movieAction(n);
        }else{
            String input = textUI.getUserInput("write the title of the series you wish to watch");
            Series s = fileIO.readSeriesData("title", input);
            seriesAction(s);
        }
    }

    private void movieAction(Movie mov) {
        ArrayList<String> options = new ArrayList(Arrays.asList("Play movie", "Add movie til list", "Remove movie from list"));
        String input = textUI.getUserInput("" + mov.getTitle() + " Please select one of the following", options);
        switch (Integer.parseInt(input)) {
            case 1:
                System.out.println(mov);
                u.setWatchedMovies(mov.getID());
                break;
            case 2:
                u.setSavedMovies(mov.getID());
                break;
            case 3:
                u.removeSavedMovie(mov.getID());
                break;
            default:
                System.out.println("Please try again");
        }
        fileIO.updateUserData(u);
    }

    private void seriesAction(Series ser) {
        ArrayList<String> options = new ArrayList(Arrays.asList("Play series", "Add series til list", "Remove series from list"));
        String input = textUI.getUserInput("" + ser.getTitle() + " Please select one of the following", options);
        switch (Integer.parseInt(input)) {
            case 1:
                System.out.println(ser);
                u.setWatchedSeries(ser.getID());
                break;
            case 2:
                u.setSavedSeries(ser.getID());
                break;
            case 3:
                u.removeSavedSeries(ser.getID());
                break;
            default:
                System.out.println("Please try again");
        }
        fileIO.updateUserData(u);
    }

    private void viewSavedMovie(){
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < u.getSavedMovies().size(); i++) {
            Movie movie = fileIO.readMovieData("ID", String.valueOf(u.getSavedMovies().get(i)));
            movies.add(movie);

        }
        ArrayList<String> options = new ArrayList();
        for (int i = 0; i < movies.size(); i++) {
            options.add(movies.get(i).getTitle());
        }
        String input = textUI.getUserInput("Please select your movie", options);
        movieAction(movies.get(Integer.parseInt(input)));
    }

    private void viewSavedSeries(){
        ArrayList<Series> series = new ArrayList<>();
        for (int i = 0; i < u.getSavedSeries().size(); i++) {
            Series serie = fileIO.readSeriesData("ID", String.valueOf(u.getSavedSeries().get(i)));
            series.add(serie);

        }
        ArrayList<String> options = new ArrayList();
        for (int i = 0; i < series.size(); i++) {
            options.add(series.get(i).getTitle());
        }
        String input = textUI.getUserInput("Please select your series", options);
        seriesAction(series.get(Integer.parseInt(input)));
    }

    private void viewWatchedMovie(){
       ArrayList<Movie> movies = new ArrayList<>();
       for(int i = 0; i < u.getWatchedMovies().size(); i++){
           Movie movie = fileIO.readMovieData("ID", String.valueOf(u.getWatchedMovies().get(i)));
           movies.add(movie);
       }

       for(int i = 0 ; i < movies.size(); i++){
           System.out.println(movies.get(i));
       }
    }

    private void viewWatchedSeries(){
        ArrayList<Series> series = new ArrayList<>();
        for(int i = 0; i < u.getWatchedSeries().size(); i++){
            Series serie = fileIO.readSeriesData("ID", String.valueOf(u.getWatchedSeries().get(i)));
            series.add(serie);
        }

        for(int i = 0 ; i < series.size(); i++){
            System.out.println(series.get(i));
        }
    }
}
