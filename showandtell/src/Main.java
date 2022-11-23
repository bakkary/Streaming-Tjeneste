import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //StartMenu start = new StartMenu();
        SQLIO sql = new SQLIO();
        sql.establishConnection();
        sql.readUserData("chris", "haha");
    }
}