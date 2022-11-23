import java.sql.*;
import java.util.ArrayList;




    public class SQLIO implements Expresso {

        public SQLIO() {
            try
            {
                connection = DriverManager.getConnection(url, username, password);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }


        private Connection connection;

        private String url = "jdbc:mysql://localhost/streaming?" + "autoReconnect=true&useSSL=false";
        private String username = "root";
        private String password = "password";

        @Override
        public String[] readSeriesData(String field, String column) {
            String query = ("select * from series where "+field+ " = '"+column+"' ");
            ArrayList<String> series = new ArrayList<>();

            try {
                Statement statement = this.connection.createStatement();
                statement.execute(query);

                ResultSet resultSet = statement.getResultSet();

                while(resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    String title = resultSet.getString("title");
                    String range = resultSet.getString("range");
                    String rating = resultSet.getString("rating");
                    String seasons = resultSet.getString("seasons");
                    boolean bAge = resultSet.getBoolean("age");
                    String age = "false";
                    if (bAge) {
                        age = "true";
                    }

                    series.add(ID);
                    series.add(title);
                    series.add(range);
                    series.add(rating);
                    series.add(seasons);
                    series.add(age);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            String[] seriesArr = new String[series.size()];
            seriesArr = series.toArray(seriesArr);

            for (int i = 0; i < seriesArr.length; i++) {
                System.out.println(seriesArr[i]);
            }

            return seriesArr;
        }

        @Override
        public String[] readMovieData(String field, String column) {
            String query = ("select * from movies where "+field+ " = '"+column+"' ");
            ArrayList<String> movie = new ArrayList<>();

            try {
                Statement statement = this.connection.createStatement();
                statement.execute(query);

                ResultSet resultSet = statement.getResultSet();

                while(resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    String title = resultSet.getString("title");
                    String year = resultSet.getString("year");
                    String rating = resultSet.getString("rating");
                    boolean bAge = resultSet.getBoolean("age");
                    String age = "false";
                    if (bAge) {
                        age = "true";
                    }

                    movie.add(ID);
                    movie.add(title);
                    movie.add(year);
                    movie.add(rating);
                    movie.add(age);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            String[] movieArr = new String[movie.size()];
            movieArr = movie.toArray(movieArr);

            for (int i = 0; i < movieArr.length; i++) {
                System.out.println(movieArr[i]);
            }

            return movieArr;
        }

        @Override
        public String[] readUserData(String u, String p) {
            String query = ("SELECT * FROM users WHERE username = '"+u+"' AND password = '"+p+"'");
            ArrayList<String> user = new ArrayList<>();

            try {
                Statement statement = this.connection.createStatement();
                statement.execute(query);

                ResultSet resultSet = statement.getResultSet();

                while(resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String age = resultSet.getString("age");
                    user.add(ID);
                    user.add(username);
                    user.add(password);
                    user.add(age);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            String savedMovies = getList(Integer.parseInt(user.get(0)), "saved_movies", "user_id", "movie_id");
            String watchedMovies = getList(Integer.parseInt(user.get(0)), "watched_movies", "user_id", "movie_id");
            String savedSeries = getList(Integer.parseInt(user.get(0)), "saved_series", "user_id", "series_id");
            String watchedSeries = getList(Integer.parseInt(user.get(0)), "watched_movies", "user_id", "series_id");
            user.add(4, watchedMovies);
            user.add(5,savedMovies);
            user.add(6, watchedSeries);
            user.add(7, savedSeries);
            String[] userArr = new String[user.size()];
            userArr = user.toArray(userArr);

            return userArr;
        }

        public String getList(int i, String table, String column, String label) {
            String query = ("select * from "+ table +" where "+column+" ="+i+"");
            String list = "";
            try {
                Statement statement = this.connection.createStatement();
                statement.execute(query);

                ResultSet resultSet = statement.getResultSet();

                while(resultSet.next()) {
                    int ID = resultSet.getInt(label);
                    list += ID + ",";
                }


            } catch(SQLException e) {
                e.printStackTrace();
            }

            return list;
        }



        @Override
        public ArrayList searchCategories(int cat) {
            return null;
        }

        @Override
        public ArrayList<String> movieCat(String field, int userInput) {
            return null;
        }

        @Override
        public void writeUserData(User u) {
            //String query = ("INSERT INTO users (ID,username,password) VALUES("+ID+",'"+username+"','"+password+"'");
            //Resultset result = new


        }

        @Override
        public void updateUserData(User u) {

        }



    }

