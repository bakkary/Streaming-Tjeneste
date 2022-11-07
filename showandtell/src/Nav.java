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
        user = new User(Integer.parseInt((arr[0])), arr[1], arr[2], Integer.parseInt(arr[3]), arr[4], arr[5], arr[6], arr[7]);
    }

    public void logout() {
        String result = textUI.getUserInput("Are you sure you want to logout? \n Press 1 to logout \n Press 2 to go back");
                if(Integer.parseInt(result) == 1){
                    user = null;

                    startMenu();
                    //  } else{ mainMenu();


        }


    }

    private void signUp() {
        String username = textUI.getUserInput("Please type your username: ");
        String password = textUI.getUserInput("Please type your password: ");
        int age = Integer.parseInt(textUI.getUserInput("Please type your age: "));

        user = new User(username, password, age);
        fileIO.writeUserData(user);
    }


}
