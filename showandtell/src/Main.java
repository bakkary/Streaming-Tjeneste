import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static FileIO fileIO = new FileIO();
    static TextUI textUI = new TextUI();


    public static void main(String[] args) {
        User u = startMenu();
        Nav nav = new Nav(u);
        nav.mainMenu();

    }

    public  static  User startMenu(){
        String result = textUI.getUserInput("Welcome! \n Press 1 to sign up \n Press 2 to log in");
        if(Integer.parseInt(result) == 1){
            return signUp();
        }else{
            return login();
        }

    }
    public static User login() {
        String username = textUI.getUserInput("Please type your username: ");
        String password = textUI.getUserInput("Please type your password: ");
        String result = fileIO.readUserData(username, password);
        String[] arr = result.split(";");
        String[] watched = arr[4].split(",");
        ArrayList<String> watchedMovies = new ArrayList<>(Arrays.asList(watched));
        String[] saved = arr[5].split(",");
        ArrayList<String> savedMovies = new ArrayList<>(Arrays.asList(saved));
        User user = new User(arr[1],arr[2], Integer.parseInt(arr[3]), Integer.parseInt(arr[0]), watchedMovies, savedMovies);
        return user;
    }

    /*public static User logout() {
        String result = textUI.getUserInput("Are you sure you want to logout? \n Press 1 to logout \n Press 2 to go back");
        if(Integer.parseInt(result) == 1){
            User user = null;

            startMenu();
            //  } else{ mainMenu();


        }


    }
*/
    private static User signUp() {
        String username = textUI.getUserInput("Please type your username: ");
        String password = textUI.getUserInput("Please type your password: ");
        int age = Integer.parseInt(textUI.getUserInput("Please type your age: "));
        int ID = fileIO.getRow("userRow");
        User user = new User(username, password, age, ID, new ArrayList<>(), new ArrayList<>());
        fileIO.writeUserData(user);
        return user;
    }

    
}