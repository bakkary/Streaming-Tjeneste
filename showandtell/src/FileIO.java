import java.io.*;
import java.util.*;

public class FileIO implements Expresso{

    public ArrayList<String> readSeriesData() {
        File file = new File("showandtell/Data/series.txt");
        ArrayList<java.lang.String> series = new ArrayList<>();
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
@Override
    public String[] readSeriesData(String field, String query) {
        File file = new File("showandtell/Data/series.txt");
        try {
            Scanner input = new Scanner(file);
            String[] header = input.nextLine().split(";");
            int index = 0;
            for (int i = 0; i < header.length; i++) {
                if (header[i].equalsIgnoreCase(field)) {
                    index = i;
                }
            }

            while (input.hasNextLine()) {
                String movie = input.nextLine();
                String[] values = movie.split(";");
                if (values[index].equalsIgnoreCase(query)) {
                    return values;
                }
            }

        }catch (FileNotFoundException e) {
        }
        return null;
    }
@Override
    public String[] readMovieData(String field, String query) {
        File file = new File("showandtell/Data/movies.txt");
        try {
            System.out.println(file);
            Scanner input = new Scanner(file);
            String[] header = input.nextLine().split(";");
            int index = 0;
            for (int i = 0; i < header.length; i++) {
                if (header[i].equalsIgnoreCase(field)) {
                    index = i;
                }
            }

            while (input.hasNextLine()) {
                String movie = input.nextLine();
                String[] values = movie.split(";");
                if (values[index].equalsIgnoreCase(query)) {
                    return values;
                }
            }

        }catch (FileNotFoundException e) {
        }
        return null;
    }
@Override
    public String[] readUserData(String username, String password) {
        File file = new File("showandtell/Data/users.txt");
        ArrayList<String> users = new ArrayList<>();
        try{ Scanner input = new Scanner(file);
            input.nextLine();
            while (input.hasNextLine()){
                users.add(input.nextLine());
            }
            for(String u : users){
                String[] values = u.split(";");
                if(Objects.equals(values[1], username) && Objects.equals(values[2], password)){
                    return values;
                }
            }
        }catch (FileNotFoundException e) {
            users = null;
        }
        return new String[]{""};
    }

    @Override
    public ArrayList searchCategories(int cat) {
        String path ="";
        if(cat == 1) {
            path = "showandtell/Data/movies.txt";
        }else {
            path = "showandtell/Data/series.txt";
        }
        File file = new File(path);

        ArrayList<String> indCategories = null;
        try {

            Scanner scan = new Scanner(file);
            scan.nextLine();
            indCategories = new ArrayList<>();
            while (scan.hasNextLine()) {

                String values = scan.nextLine();
                String[] temp = values.split(";");
                String[] categories = temp[3].split(",");
                indCategories.addAll(Arrays.asList(categories));
            }

            Set<String> tempArray = new LinkedHashSet<String>(indCategories);
            indCategories.clear();
            indCategories.addAll(tempArray);
        } catch (FileNotFoundException e) {

        }
        return indCategories;
    }
@Override
    public ArrayList <String> movieCat(String field, int userInput){
        String path ="";
        if (userInput == 1) {
            path = "showandtell/Data/movies.txt";
        }else{
            path = "showandtell/Data/series.txt";
        }

        File file = new File(path);

        ArrayList<String> catMovie = null;
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();
            catMovie = new ArrayList<>();
            while (scan.hasNextLine()) {

                String values = scan.nextLine();
                String[] temp = values.split(";");
                String[] categories = temp[3].split(",");
                List <String> tempList = new ArrayList<>(Arrays.asList(categories));
                if(tempList.contains(field)){
                    catMovie.add(values);
                }
            }

        } catch (FileNotFoundException e) {

        }
        return catMovie;

    }

    @Override
    public void writeUserData(User u){
        try{
            FileWriter writer = new FileWriter("showandtell/Data/users.txt", true);
            writer.write(u.getID() + ";" + u.getUsername() + ";" + u.getPassword() + ";" + u.getAge() + ";,;,;,;,;\n");
            writer.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }
@Override
    public void updateUserData(User u) {
        File file = new File("showandtell/Data/users.txt");
        ArrayList<String> rows = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()){
                rows.add(input.nextLine());
            }

            FileWriter writer = new FileWriter(file);
            writer.write(rows.get(0) + "\n");
            for (int i = 1; i < rows.size(); i++) {
                String[] values = rows.get(i).split(";");
                if(Integer.parseInt(values[0]) == u.getID()) {
                    String watchedMovieStr = "";
                    for (int j = 0; j < u.getWatchedMovies().size(); j++) {
                        watchedMovieStr += u.getWatchedMovies().get(j) + ",";
                    }
                    if (watchedMovieStr.equalsIgnoreCase("")) {
                        watchedMovieStr = ",";
                    }
                    String savedMovieStr = "";
                    for (int j = 0; j < u.getSavedMovies().size(); j++) {
                        savedMovieStr += u.getSavedMovies().get(j) + ",";
                    }
                    if (savedMovieStr.equalsIgnoreCase("")) {
                        savedMovieStr = ",";
                    }
                    String watchedSeriesStr = "";
                    for (int j = 0; j < u.getWatchedSeries().size(); j++) {
                        watchedSeriesStr += u.getWatchedSeries().get(j) + ",";
                    }
                    if (watchedSeriesStr.equalsIgnoreCase("")) {
                        watchedSeriesStr = ",";
                    }
                    String savedSeriesStr = "";
                    for (int j = 0; j < u.getSavedSeries().size(); j++) {
                        savedSeriesStr += u.getSavedSeries().get(j) + ",";
                    }
                    if (savedSeriesStr.equalsIgnoreCase("")) {
                        savedSeriesStr = ",";
                    }
                    rows.set(i, "" + u.getID() + ";" + u.getUsername() + ";" + u.getPassword() + ";" + u.getAge() +  ";" + watchedMovieStr + ";" + savedMovieStr + ";" + watchedSeriesStr + ";" + savedSeriesStr +";");
                }
                writer.write(rows.get(i) + "\n");
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getRow(String rowType) {
        File file = new File("showandtell/Data/systemInfo.txt");
        ArrayList<String> rows = new ArrayList<>();
        int index = 0;
        try{
            Scanner input = new Scanner(file);
            while (input.hasNextLine()){
                rows.add(input.nextLine());
            }


            FileWriter writer = new FileWriter(file);
            writer.write(rows.get(0) + "\n");
            for(int i = 1; i < rows.size(); i++){
                String[] values = rows.get(i).split(";");
                if(values[0].equalsIgnoreCase(rowType)){
                    index = Integer.parseInt(values[1]);
                rows.set(i, "" + values[0] + ";" + (index + 1) +";");
                }
                writer.write(rows.get(i) + "\n");
            }

            writer.close();
        }catch (IOException e) {
            rows = null;
        }
        return index;
    }


}

