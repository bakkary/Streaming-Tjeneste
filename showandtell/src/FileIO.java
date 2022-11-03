import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {


    public ArrayList<String> readMovieData() {
        File file = new File("Data/movies.txt");
        ArrayList<String> movie = new ArrayList<>();
        try{ Scanner input = new Scanner(file);
            input.nextLine();

            while (input.hasNextLine()){
                movie.add(input.nextLine());
            }
        }catch (FileNotFoundException e) {
             movie = null;
        }
        return movie;
    }


    public ArrayList<String> readSeriesData() {
        File file = new File("Data/series.txt");
        ArrayList<String> series = new ArrayList<>();
        try{ Scanner input = new Scanner(file);
            input.nextLine();

            while (input.hasNextLine()){
                series.add(input.nextLine());
            }
        }catch (FileNotFoundException e) {
            series = null;
        }
        return series;
    }

    public ArrayList<String> readUserData() {
        File file = new File("Data/users.txt");
        ArrayList<String> users = new ArrayList<>();
        try{ Scanner input = new Scanner(file);
            input.nextLine();

            while (input.hasNextLine()){
                users.add(input.nextLine());
            }
        }catch (FileNotFoundException e) {
            users = null;
        }
        return users;
    }
}

