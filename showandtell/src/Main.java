public class Main {

    static FileIO fileIO;
    static TextUI textUI;





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
        User user = new User(arr[0],arr[1], Integer.parseInt(arr[2]));
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
        public static User signUp() {
        String username = textUI.getUserInput("Please type your username: ");
        String password = textUI.getUserInput("Please type your password: ");
        int age = Integer.parseInt(textUI.getUserInput("Please type your age: "));

        User user = new User(username, password, age);
        fileIO.writeUserData(user);
        return user;
    }
}