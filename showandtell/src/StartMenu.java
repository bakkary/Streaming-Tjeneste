import java.util.ArrayList;
import java.util.Arrays;

public class StartMenu {

    static FileIO fileIO = new FileIO();
    static TextUI textUI = new TextUI();

    public StartMenu() {
    }


    public void RunMenu() {
        User u = null;

        String result = textUI.getUserInput("Welcome! \n Press 1 to sign up \n Press 2 to log in");
        if (Integer.parseInt(result) == 1) {
            u = signUp();
        } else if (Integer.parseInt(result) == 2) {
            u = login();
        } else {
            System.out.println("please try again");
            RunMenu();
        }


        Nav nav = new Nav(u);
        nav.mainMenu();
    }

    public User login() {
        String username = textUI.getUserInput("Please type your username: ");
        String password = textUI.getUserInput("Please type your password: ");
        String result = fileIO.readUserData(username, password);
        System.out.println(result);
        if (result.equalsIgnoreCase("")) {
            System.out.println("Wrong username or password");
            login();
        } else {
            String[] arr = result.split(";");
            String[] watchedmovies = arr[4].split(",");
            ArrayList<String> watchedMovies = new ArrayList<>(Arrays.asList(watchedmovies));
            String[] savedmovies = arr[5].split(",");
            ArrayList<String> savedMovies = new ArrayList<>(Arrays.asList(savedmovies));
            String[] watchedseries = arr[6].split(",");
            ArrayList<String> watchedSeries = new ArrayList<>(Arrays.asList(watchedseries));
            String[] savedseries = arr[7].split(",");
            ArrayList<String> savedSeries = new ArrayList<>(Arrays.asList(savedseries));

            User user = new User(arr[1], arr[2], Integer.parseInt(arr[3]), Integer.parseInt(arr[0]), watchedMovies, savedMovies, watchedSeries, savedSeries);
            return user;
        }
        return null;
    }

    public void logout() {
        User u = null;
        Nav nav = new Nav(u);
        String result = textUI.getUserInput("Are you sure you want to logout? \n Press 1 to logout \n Press 2 to go back");
        User user = null;
        if (Integer.parseInt(result) == 1) {
            RunMenu();
        } else if (Integer.parseInt(result) == 2) {
            nav.mainMenu();
        } else {
            System.out.println("please try again");
            login();
        }

    }

    private User signUp() {
        String username = textUI.getUserInput("Please type your username: ");
        String password = textUI.getUserInput("Please type your password: ");
        int age = age();
        int ID = fileIO.getRow("userRow");
        User user = new User(username, password, age, ID, new ArrayList<>(), new ArrayList<>(), new ArrayList(), new ArrayList());
        fileIO.writeUserData(user);
        return user;
    }

    private int age() {
        int age = 0;
        try {
            age = Integer.parseInt(textUI.getUserInput("Please type your age: "));
        } catch (NumberFormatException n) {
            System.out.println("This is not a number");
            age();
        }
        return age;

    }
}
