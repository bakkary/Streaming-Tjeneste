import java.util.ArrayList;
import java.util.Scanner;

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
        String input = textUI.getUserInput("write the title of the movie you wish to watch");
        Movie n = fileIO.readMovieData("Title", input);
        movieAction(n);

    }
    private void movieAction(){

    }

    private void viewSaved(){

    }

    private void viewWatched(){

    }






}
