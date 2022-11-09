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
        while (!input.equalsIgnoreCase("8")) {


            ArrayList<String> options = new ArrayList<String>(Arrays.asList("Search by movie title", "Search by series title", "Search by category", "View saved movie", "View saved series ", "View watched movies", "View watched series ", "Logout"));

            input = textUI.getUserInput("Please select one of the following", options);
            switch (Integer.parseInt(input)) {
                case 1:
                    searchByTitle();
                    break;
                case 2:
                    //searchByCategory();
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
                    System.out.println("try again");
            }
        }
    }




    private void searchByTitle(boolean isMovie) {


        if(isMovie) {
            String input = textUI.getUserInput("write the title of the content you wish to watch");
            Content n = fileIO.readMovieData("title", input);
            movieAction(n);
        } else {
            String input = textUI.getUserInput("write the title of the series you wish to watch");
            Content s = fileIO.readSeriesData("title", input);
            movieAction(s);
        }
    }

    public void searchByCategory(){


        String userInput = textUI.getUserInput("please type 1 for movie and 2 for series");

        ArrayList <String> cat = fileIO.searchCategories(Integer.parseInt(userInput));

        String input = textUI.getUserInput("Please select a Categorie", cat);

        ArrayList <Content> result = fileIO.movieCat(cat.get(Integer.parseInt(input)-1),Integer.parseInt(userInput));

        input =textUI.getUserInput("please select one of the movies", result);

       movieAction(result.get(Integer.parseInt(input)-1));
        {

        }
    }


    private void movieAction(Content mov) {
        ArrayList<String> options = new ArrayList(Arrays.asList("Play movie", "Add movie to list", "Remove movie from list"));
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
                if(mov instanceof Movie) {
                    u.setSavedMovies(mov.getID());
                }else{
                    u.setSavedSeries(mov.getID());
                }
                break;
            case 3:
                if(mov instanceof Movie) {
                    u.removeSavedMovie(mov.getID());
                }else{
                    u.setSavedSeries(mov.getID());
                }
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
        movieAction(movies.get(Integer.parseInt(input) - 1));
    }


        }
        ArrayList<String> options = new ArrayList();
        for (int i = 0; i < series.size(); i++) {
            options.add(series.get(i).getTitle());
        }
        String input = textUI.getUserInput("Please select your series", options);
        movieAction(series.get(Integer.parseInt(input) - 1));
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
