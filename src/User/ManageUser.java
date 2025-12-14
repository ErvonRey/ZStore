package User;

//import here:

import DatabaseConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ManageUser {

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
    
    public static boolean checkLogin(String username, String password){
        
        
        try (Connection connection = DBConnection.getConnection();){
            String SQLCheckingLogin = "SELECT * FROM users WHERE user_username = ? AND BINARY user_password = ?";
            PreparedStatement loggingIn = connection.prepareStatement(SQLCheckingLogin);
            loggingIn.setString(1, username);
            loggingIn.setString(2, password);
            ResultSet result = loggingIn.executeQuery();
            if (result.next()) {
//                System.out.println("Account matched on bank_users...");
                int userID = result.getInt("user_id");
                ManageUser.setCurrentUserID(userID);
                return true;
            } else {
                //Account does not match
                String errorMessage =
                        "Account either does not exist or you typed"
                        + " wrong credentials.\n kindly "
                        + "recheck account information and then try again...";
                JOptionPane.showMessageDialog(null, errorMessage, "Login Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println("Error on database method checkLogin: " + e);
        }
        return false;
    }
    
    public static boolean isUsernameDuplicated(String username){
        try (Connection connection = DBConnection.getConnection()) {
            String SQLChecking = "SELECT user_username FROM users WHERE user_username = ?";
            PreparedStatement checking = connection.prepareStatement(SQLChecking);
            checking.setString(1, username);
            ResultSet result = checking.executeQuery();
            if (result.next()) {
                //duplicated
                JOptionPane.showMessageDialog(null, "Username already exists.", "Account Creation", JOptionPane.ERROR_MESSAGE);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error on database method(isUsernameDuplicated): " + e);
        }
        return true;
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
