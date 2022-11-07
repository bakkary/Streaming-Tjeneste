import java.util.ArrayList;

public class Nav {

    private User u;
    public FileIO fileIO = new FileIO();
    private TextUI textUI = new TextUI();


    public Nav(User u){
        this.u = u;
    }

    public void mainMenu(){

    }

    private void searchByTitle(){

    }
    private void movieAction(){

    }

    private void viewSaved() {
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < u.getSavedMovies().size(); i++) {
            Movie movie = fileIO.readMovieData("ID", String.valueOf(u.getSavedMovies().get(i)));
            movies.add(movie);

        }

        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i));


        }
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
