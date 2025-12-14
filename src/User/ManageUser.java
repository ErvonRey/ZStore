package User;

//import here:

import DatabaseConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageUser {
    
    /*
        This class also demonstrates encapsulation as it hides codes and variables, it will also check
        the database to see if the user thats logging in matches its credentials.
        Also this class is responsible for grabbing the customer's ID and the clerk's ID
    */

    private static int currentUserID;
    private static int currentCustomerID;
    private static int currentClerkID;
    
    public static void setCurrentUserID(int userID){
        currentUserID = userID;
    }
    public static void setCurrentCustomerID(int customerID){
        currentCustomerID = customerID;
    }
    public static void setCurrentClerkID(int clerkID){
        currentClerkID = clerkID;
    }
    public static int getCurrentUserID(){
        return currentUserID;
    }
    public static int getCurrentCustomerID(){
        return currentCustomerID;
    }
    public static int getCurrentClerkID(){
        return currentClerkID;
    }
    
    public static void clearSession(){
        currentUserID = -1;
        String noData = "";
        Data.setUsername(noData);
        Data.setPassword(noData);
        Data.setRole(-1);
        Data.setName(noData);
        Data.setEmail(noData);
        Data.setAddress(noData);
    }
    
    public static void gettingCurrentCustomerID(){
        try (Connection connection = DBConnection.getConnection()) {
            String SQLChecking = "SELECT customer_id FROM customers WHERE user_id = ?";
            PreparedStatement checking = connection.prepareStatement(SQLChecking);
            checking.setInt(1, ManageUser.getCurrentUserID());
            ResultSet result = checking.executeQuery();
            if (result.next()) {
                setCurrentCustomerID(result.getInt("customer_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error on database method(gettingCurrentCustomerID): " + e);
        }
    }
    
    public static void gettingCurrentClerkID(){
        try (Connection connection = DBConnection.getConnection()) {
            String SQLChecking = "SELECT clerk_id FROM clerks WHERE user_id = ?";
            PreparedStatement checking = connection.prepareStatement(SQLChecking);
            checking.setInt(1, ManageUser.getCurrentUserID());
            ResultSet result = checking.executeQuery();
            if (result.next()) {
                setCurrentClerkID(result.getInt("clerk_id"));
//                System.out.println("successful at getting clerk id"); //- it was a logic error lol
//              This one is funny when debugging took me some time to realize i was setting
//              up the clerk id into a customersID cuz i copied and pasted this, i was so confused LMAO
            }
        } catch (SQLException e) {
            System.out.println("Error on database method(gettingCurrentClerkID): " + e);
        }
    }

}
