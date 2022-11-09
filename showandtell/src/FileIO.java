import java.io.*;
import java.util.*;

public class FileIO {

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

    public Series readSeriesData(String field, String query) {
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
                    return series(values);
                }
            }


        }catch (FileNotFoundException e) {
        }
        return null;
    }

    public Movie readMovieData(String field, String query) {
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
                    return movie(values);
                }
            }


        }catch (FileNotFoundException e) {
        }
        return null;
    }

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

    public ArrayList <Content> movieCat(String field, int userInput){
        String path ="";
        if (userInput == 1) {
            path = "showandtell/Data/movies.txt";
        }else{
            path = "showandtell/DATA/movies.txt";
        }

        File file = new File(path);

        ArrayList<Content> catMovie = null;
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
                    catMovie.add(movie(temp));
                }
            }


        } catch (FileNotFoundException e) {


        }
        return catMovie;



    }

    private Map content(String[] c) {
        int ID = Integer.parseInt(c[0]);
        String title = c[1];
        ArrayList<String> categories = new ArrayList<>();
        String[] categoriesArr = c[3].split(",");
        categories.addAll(Arrays.asList(categoriesArr));
        float rating = Float.parseFloat(c[4].replace(",", "."));
        Map map = new HashMap<>();
        map.put("title", title);
        map.put("categories", categories);
        map.put("rating", rating);
        map.put("ID", ID);
        return map;
    }

    private Movie movie(String[] m) {
        Map map = content(m);
        int year = Integer.parseInt(m[2]);
        boolean age = Boolean.parseBoolean(m[5]);
        return new Movie((String) map.get("title"), (ArrayList) map.get("categories"), (Float) map.get("rating"), year, (Integer) map.get("ID"), age);

    }

    private Series series(String[] s) {
        Map map = content(s);
        String[] years = s[2].trim().split("-");
        int startDate = Integer.parseInt(years[0]);
        boolean age = Boolean.parseBoolean(s[6]);
        int endDate = 0;
        if(years.length > 1) {
            endDate = Integer.parseInt(years[1]);
        }
        ArrayList<String> seasons = new ArrayList<>();
        return new Series((String) map.get("title"), (ArrayList) map.get("categories"), (Float) map.get("rating"), startDate, endDate, seasons, (Integer) map.get("ID"), age);

    }




    public String readUserData(String username, String password) {
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
            FileWriter writer = new FileWriter("showandtell/Data/users.txt", true);
            writer.write(u.getID() + ";" + u.getUsername() + ";" + u.getPassword() + ";" + u.getAge() + ";,;,;,;,;\n");
            writer.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }

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

