import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
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

    public String readUserData(String username, String password) {
        File file = new File("Data/users.txt");
        ArrayList<String> users = new ArrayList<>();
        try{ Scanner input = new Scanner(file);
            //input.nextLine();
            while (input.hasNextLine()){
                users.add(input.nextLine());
            }
            for(String u : users){
                String[] values = u.split(";");
                if(Objects.equals(values[0], username) && Objects.equals(values[1], password)){
                    return u;
                }
            }
        }catch (FileNotFoundException e) {
            users = null;
        }
        return "";
    }

    public void writeUserData(User u){
        try{
            FileWriter writer = new FileWriter("Data/users.txt");
            writer.write(u.getUsername() + ";" + u.getPassword() + ";" + u.getAge());
            writer.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }
}

