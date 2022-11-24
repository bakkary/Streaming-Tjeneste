import javax.swing.plaf.nimbus.State;
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
                    String start = resultSet.getString("year_start");
                    String end = resultSet.getString("year_end");
                    String rating = resultSet.getString("rating");
                    String seasons = resultSet.getString("seasons");
                    boolean bAge = resultSet.getBoolean("age");
                    String age = "false";
                    if (bAge) {
                        age = "true";
                    }
                    String range = start + "-" + end;

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
            series.add(3, categories(Integer.parseInt(series.get(0)), "series"));
            String[] seriesArr = new String[series.size()];
            seriesArr = series.toArray(seriesArr);



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
            movie.add(3, categories(Integer.parseInt(movie.get(0)), "movies"));
            String[] movieArr = new String[movie.size()];
            movieArr = movie.toArray(movieArr);

            return movieArr;
        }

        public String categories(int i, String type) {
            String query = ("select categories from "+ type +" join "+type+"_categories on "+type+"_categories."+type+"_id = "+type+".ID join categories on "+type+"_categories.category_id = categories.ID where "+type+".id = "+ i + "");
            String categories = "";
            try {
                Statement statement = this.connection.createStatement();
                statement.execute(query);

                ResultSet resultSet = statement.getResultSet();

                while(resultSet.next()) {
                    String category = resultSet.getString("categories");
                    categories += category+",";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return categories;
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
            String savedMovies = getList(Integer.parseInt(user.get(0)), "movies_saved", "user_id", "movie_id");
            String watchedMovies = getList(Integer.parseInt(user.get(0)), "movies_watched", "user_id", "movie_id");
            String savedSeries = getList(Integer.parseInt(user.get(0)), "series_saved", "user_id", "series_id");
            String watchedSeries = getList(Integer.parseInt(user.get(0)), "series_watched", "user_id", "series_id");
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
                if (list.length() == 0) {
                    list += ",";
                }


            } catch(SQLException e) {
                e.printStackTrace();
            }
            return list;
        }



        @Override
        public ArrayList searchCategories(int cat) {
            ArrayList<String> cate = new ArrayList<>();

            try {
                String query = ("Select * from categories");
                Statement statement = this.connection.createStatement();
                statement.execute(query);

                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next()) {
                    cate.add(resultSet.getString("categories"));
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }

            return cate;
        }

        @Override
        public ArrayList<String> movieCat(String field, int userInput) {
            int id = 0;
            ArrayList<Integer> ids = new ArrayList<>();

            try {
                String query = ("Select * from categories where categories = '"+ field +"'");
                System.out.println(query);
                Statement statement = this.connection.createStatement();
                statement.execute(query);
                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next()) {
                    id = resultSet.getInt("ID");
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
            try {
                String query = ("Select * from movies_categories where category_id = "+ id + "");
                Statement statement = this.connection.createStatement();
                statement.execute(query);
                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next()) {
                    ids.add(resultSet.getInt("movies_id"));                    
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ArrayList<String> movies = new ArrayList<>();
            for (int i = 0; i < ids.size(); i++) {
                var tmp = readMovieData("ID", String.valueOf(ids.get(i)));
                String str = "";
                for (int j = 0; j < tmp.length; j++) {
                    str += tmp[j] + ",";
                }


                str = str.substring(0, str.length()-1);
                movies.add(str);
            }
            return movies;
        }

        @Override
        public void writeUserData(User u) {
            String query = ("insert into users values ("+u.getID()+",'"+ u.getUsername()+"','"+u.getPassword()+"',"+u.getAge()+")");
            try {
                Statement statement = this.connection.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void updateUserData(User u) {

            for (int i = 0; i < u.getSavedMovies().size(); i++) {
                try {
                    String query = ("delete from movies_saved where user_id = " + u.getID() +" and movie_id = "+ u.getSavedMovies().get(i) +"");
                    Statement statement = this.connection.createStatement();
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try{
                    String query = ("insert into movies_saved values("+ u.getID() + ", "+ u.getSavedMovies().get(i) +")");
                    Statement statement = this.connection.createStatement();
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // update movies_watched
            for (int i = 0; i < u.getWatchedMovies().size(); i++) {
                try {
                    String query = ("delete from movies_watched where user_id = "+ u.getID() +" and movie_id = "+ u.getWatchedMovies().get(i)+ "");
                    Statement statement = this.connection.createStatement();
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    String query = ("insert into movies_watched values("+ u.getWatchedMovies().get(i) +", "+ u.getID() +")");
                    Statement statement = this.connection.createStatement();
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // update series_saved
            for (int i = 0; i < u.getSavedSeries().size(); i++) {
                try{
                    String query = ("delete from series_saved where user_id = "+ u.getID() +" and series_id = "+ u.getSavedSeries().get(i) +"");
                    Statement statement = this.connection.createStatement();
                    statement.executeUpdate(query);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    String query = ("insert into series_saved values("+ u.getID() +", "+ u.getSavedSeries().get(i) +")");
                    Statement statement = this.connection.createStatement();
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            // update series_watched
            for (int i = 0; i < u.getWatchedSeries().size(); i++) {
                try{
                    String query = ("delete from series_watched where user_id = "+ u.getID() +" and series_id = "+ u.getWatchedSeries().get(i) +"");
                    Statement statement = this.connection.createStatement();
                    statement.executeUpdate(query);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    String query = ("insert into series_watched values("+ u.getWatchedSeries().get(i) +", "+  u.getID() +")");
                    Statement statement = this.connection.createStatement();
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }



    }

