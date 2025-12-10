package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/dbm_finalproject";
    private static final String USER = "ervonrey";
    private static final String PASSWORD = "mellisaAdmin1023!";
    
    public static Connection getConnection(){
        
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Connected Successfully");
        } catch (SQLException e) {
            
            e.printStackTrace();
            System.out.println("Error at connecting to Database");
            
        }
        
        return connection;
        
    }
    
    public static void main(String[] args) {
        getConnection();
    }
    
}
