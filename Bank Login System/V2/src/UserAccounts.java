import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserAccounts {

    private HashMap <String,String> loginInfo = new HashMap<String,String>();
    private HashMap <String,Double> money = new HashMap<String,Double>();
    private HashMap <String,String> gender = new HashMap<String,String>();
    private HashMap <String,String> firstName = new HashMap<String,String>();
    private HashMap <String,String> lastName = new HashMap<String,String>();
    String dbUrl = "jdbc:mysql://localhost:3306/BankLoginSystem";
    String username = "root";
    String password = "Kalampoki-2003";

    public UserAccounts(){
        // Load the MySQL JDBC driver
        try {    
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            System.err.println("Error loading MySQL JDBC driver.");
            e.printStackTrace();
            return;
        }

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            String sqlQuery = "SELECT * FROM INFO";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                // Retrieve data from the result set
                String uname = resultSet.getString("USERNAME");
                String fname = resultSet.getString("FIRSTNAME");
                String lname = resultSet.getString("LASTNAME");
                String pword = resultSet.getString("PASSWORD");
                float balance = resultSet.getFloat("BALANCE");
                String gder = resultSet.getString("GENDER");

                loginInfo.put(uname,pword);
                money.put(uname,Double.valueOf(balance));
                gender.put(uname,gder);
                firstName.put(uname,fname);
                lastName.put(uname,lname);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database.");
            e.printStackTrace();
        }
    }

    public void insertDB(String uname,String passwd,String fname,String lname,String gder){
        try{
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            String sqlInsert = 
            "INSERT INTO INFO VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pStatement = connection.prepareStatement(sqlInsert);

            // Set the values for the placeholders (?)
            pStatement.setString(1, uname);
            pStatement.setString(2, fname);
            pStatement.setString(3, lname);
            pStatement.setString(4, passwd);
            pStatement.setFloat(5, Float.valueOf(0));
            pStatement.setString(6, gder);

            pStatement.executeUpdate();

            pStatement.close();
            connection.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updateDB(String uname,float balance){
        try {
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            String sqlUpdate = 
            "UPDATE INFO SET BALANCE = ? WHERE USERNAME = ?";

            PreparedStatement pStatement = connection.prepareStatement(sqlUpdate);

            // Set the values for the placeholders (?)
            pStatement.setFloat(1, balance);
            pStatement.setString(2, uname);

            pStatement.executeUpdate();

            pStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String,String> getLoginInfo(){
        return loginInfo;
    }

    public HashMap<String,Double> getMoney(){
        return money;
    }

    public HashMap<String,String> getGender(){
        return gender;
    }

    public HashMap<String,String> getFirstName(){
        return firstName;
    }

    public HashMap<String,String> getLastName(){
        return lastName;
    }
}