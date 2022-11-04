import java.util.ArrayList;

public class Nav {

    public FileIO fileIO = new FileIO();
    private TextUI textUI = new TextUI();

    public ArrayList<Series> series = new ArrayList();

    public ArrayList<Movie> movies = new ArrayList();

    public User user;

    public void startMenu(){
        String result = textUI.getUserInput("Welcome! \n Press 1 to sign up \n Press 2 to log in");
        if(Integer.parseInt(result) == 1){
            signUp();
        }else{
            login();
        }
        System.out.println(user.getUsername());
    }
    public void login() {
        String username = textUI.getUserInput("Please type your username: ");
        String password = textUI.getUserInput("Please type your password: ");
        String result = fileIO.readUserData(username, password);
        String[] arr = result.split(";");
        user = new User(arr[0],arr[1], Integer.parseInt(arr[2]));
    }

    public void logout() {


    }

    private void signUp() {
        String username = textUI.getUserInput("Please type your username: ");
        String password = textUI.getUserInput("Please type your password: ");
        int age = Integer.parseInt(textUI.getUserInput("Please type your age: "));

        user = new User(username, password, age);
        fileIO.writeUserData(user);
    }


}
