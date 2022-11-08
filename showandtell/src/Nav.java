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
        while (!input.equalsIgnoreCase("5")) {


            ArrayList<String> options = new ArrayList<String>(Arrays.asList("Search by title", "Search by category", "View saved content", "View watched content" , "Logout"));
            input = textUI.getUserInput("Please select one of the following", options);
            switch (Integer.parseInt(input)) {
                case 1:
                    searchByTitle();
                    break;
                case 2:
                    //searchByCategory();
                    break;
                case 3:
                    viewSaved();
                    break;
                case 4:
                    viewWatched();
                    break;
                case 5:
                    start.logout();
                default:
                    System.out.println("try again");
            }
        }
    }

    private void searchByTitle() {
        String input = textUI.getUserInput("write the title of the movie you wish to watch");
        Movie n = fileIO.readMovieData("title", input);
        movieAction(n);
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

    private void viewSaved(){
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


    private void viewWatched(){
       ArrayList<Movie> movies = new ArrayList<>();
       for(int i = 0; i < u.getWatchedMovies().size(); i++){
           Movie movie = fileIO.readMovieData("ID", String.valueOf(u.getWatchedMovies().get(i)));
           movies.add(movie);
       }

       for(int i = 0 ; i < movies.size(); i++){
           System.out.println(movies.get(i));
       }
    }






}
