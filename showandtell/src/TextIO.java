import java.util.ArrayList;
import java.util.Scanner;

public class TextIO {
    static Scanner scan = new Scanner (System.in);




    public String getUserInput(String msg){
        String[] movies = { " The Godfather" };
        Scanner scanner = new Scanner(System.in);
        String input = null;

        System.out.println("Welcome ");
        System.out.println("what you want to watch?");
        do {
            System.out.print("> ");
            input = scanner.next();
            for (int i = 0; i < movies.length; i++) {
                if (movies[i].equalsIgnoreCase(input)) {
                    System.out.println("Found keyword!");
                    // TODO: You can optimize this
                }
            }
        } while (!input.equalsIgnoreCase("bye"));
        System.out.println("Alright then, goodbye!");
        return null;
    }
}