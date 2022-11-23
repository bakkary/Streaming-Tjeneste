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
        private String password = "Pihkxl123123";
        private ArrayList<String> users = new ArrayList<>();




        public void establishConnection()
        {
            // connection
            try
            {
                connection = DriverManager.getConnection(url, username, password);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public String[] readSeriesData(String field, String query) {

            return new String[0];
        }

        @Override
        public String[] readMovieData(String field, String query) {
            return new String[0];
        }

        @Override
        public String[] readUserData(String u, String p) {
            String query = ("SELECT * FROM users WHERE username = '" +u+"''" +p+"'LIMIT 1");
            try
            {
                Statement statement = this.connection.createStatement();
                statement.execute(query);

                ResultSet resultSet = statement.getResultSet();
                ArrayList<String> users = new ArrayList<>();
                Object[] usersarray;

                    String ID = resultSet.getString("ID");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    System.out.println(1);

                    users.add(ID);
                    users.add(username);
                    users.add(password);

                    usersarray = users.toArray();




            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            return new String[0];
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

