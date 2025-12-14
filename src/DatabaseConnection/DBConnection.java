package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/dbm_finalproject";
                                //URL = "jdbc:mysql://(hostname):(port)/(schema_name)";
    private static final String USER = "ervonrey";
                                //USER = "(the username of your mysql, common as root)"
    private static final String PASSWORD = "mellisaAdmin1023!";
                                //PASSWORD = "(the password of your user, also common as root)"
    
    public static Connection getConnection(){
        
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Connected Successfully"); //debugging line
        } catch (SQLException e) {
            
            e.printStackTrace();
            System.out.println("Error at connecting to Database");
            
        }
        
        return connection;
        
    }
 
}
