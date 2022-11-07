import java.util.ArrayList;
import java.util.Arrays;

public class Nav {

    private User u;
    public FileIO fileIO = new FileIO();
    private TextUI textUI = new TextUI();


    public Nav(User u){
        this.u = u;
    }

    public void mainMenu(){
        String input = "";
        while(!input.equalsIgnoreCase("Q")) {


            ArrayList<String> options = new ArrayList<String>(Arrays.asList("Search by title", "Search by category", "View saved content", "View watched content"));
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
                default:
                    System.out.println("try again");
            }
        }
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
