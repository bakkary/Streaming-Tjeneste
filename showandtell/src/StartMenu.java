import java.util.ArrayList;
import java.util.Arrays;

public class StartMenu {
    static FileIO fileIO = new FileIO();
    static TextUI textUI = new TextUI();

    private Connector connector = new Connector();

    public StartMenu() {


    }



    public void RunMenu() {
        User u = null;
        connector.connection();
        String result = textUI.getUserInput("Welcome! \n Press 1 to sign up \n Press 2 to log in");
        try {
            if (Integer.parseInt(result) == 1) {
                u = signUp();
            } else if (Integer.parseInt(result) == 2) {
                u = login();
            } else {
                System.out.println("Please try again");
                RunMenu();
            }
        }catch (NumberFormatException e){
            System.out.println(result + " is not recognized as a expected input");
            RunMenu();
        }


        Nav nav = new Nav(u,connector);
        nav.mainMenu();
    }

    public User login() {
        String username = textUI.getUserInput("Please type your username: ");
        String password = textUI.getUserInput("Please type your password: ");
        User result = connector.readUserData(username, password);
        System.out.println("Hello " + username + "Welcome to Show and Tell");
        if (result == null) {
            System.out.println("Wrong username or password");
            login();
        } else {
            return result;
        }
        return null;
    }


    public void logout() {
        User u = null;
        String result = textUI.getUserInput("Are you sure you want to logout? \n Press 1 to logout \n Press 2 to go back");
        if (Integer.parseInt(result) == 1) {
            RunMenu();
        } else if (Integer.parseInt(result) == 2) {
            Nav nav = new Nav(u, connector);
            nav.mainMenu();
        } else {
            System.out.println("Please try again");
            login();
        }

    }

    private User signUp() {
        String username = textUI.getUserInput("Please type your username: ");
        String password = textUI.getUserInput("Please type your password: ");
        int age = age();
        int ID = fileIO.getRow("userRow");
        User user = new User(username, password, age, ID, new ArrayList<>(), new ArrayList<>(), new ArrayList(), new ArrayList());
        connector.writeUserData(user);
        System.out.println("test");
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
