import java.sql.*;
import java.util.ArrayList;



    public class SQLIO {

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

        private String url = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
        private String username = "root";
        private String password = "7gaZXHlXt8y6^#0u";
        private ArrayList<String> users = new ArrayList<>();

        public void run()
        {
            // connection
            establishConnection();

            // statement
            String query = "SELECT * FROM city ORDER BY population DESC LIMIT 10";

            try
            {
                Statement statement = this.connection.createStatement();
                statement.execute(query);

                ResultSet resultSet = statement.getResultSet();

                while(resultSet.next())
                {
                    String name = resultSet.getString("username");
                    String password = resultSet.getString("password");


                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }


          readUserData("chris");
        }


        public String readUserData(String username) {


            String query = ("SELECT * FROM users WHERE username = '" +username+"'LIMIT 1");
            try
            {
                Statement statement = this.connection.createStatement();
                statement.execute(query);

                ResultSet resultSet = statement.getResultSet();

                while(resultSet.next())
                {
                    String name = resultSet.getString("username");
                    String password = resultSet.getString("Password");

                    users.add(username);

                    System.out.println(users);


                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return "";

        }

/*
        //private void writeUserData(int ID, String username, String password) {
            String query = ("INSERT INTO users (ID,username,password) VALUES("+ID+",'"+username+"','"+password+"'");
            Resultset result = new


        }
*/




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
    }

