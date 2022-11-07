public class Main {

    static FileIO fileIO;
    static TextUI textUI;
    static User user;




    public static void main(String[] args) {
        startMenu();
    }

    public  static  void startMenu(){
        String result = textUI.getUserInput("Welcome! \n Press 1 to sign up \n Press 2 to log in");
        if(Integer.parseInt(result) == 1){
            signUp();
        }else{
            login();
        }
        System.out.println(user.getUsername());
    }
    public static void login() {
        String username = textUI.getUserInput("Please type your username: ");
        String password = textUI.getUserInput("Please type your password: ");
        String result = fileIO.readUserData(username, password);
        String[] arr = result.split(";");
        user = new User(arr[0],arr[1], Integer.parseInt(arr[2]));
    }

    public static void logout() {
        String result = textUI.getUserInput("Are you sure you want to logout? \n Press 1 to logout \n Press 2 to go back");
        if(Integer.parseInt(result) == 1){
            user = null;

            startMenu();
            //  } else{ mainMenu();


        }


    }

    private static void signUp() {
        String username = textUI.getUserInput("Please type your username: ");
        String password = textUI.getUserInput("Please type your password: ");
        int age = Integer.parseInt(textUI.getUserInput("Please type your age: "));

        user = new User(username, password, age);
        fileIO.writeUserData(user);
    }
}