package Core;

//import here:

import DatabaseConnection.DBConnection;
import User.ManageUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
    This class is for checking the validation of their inputs and also conditions to limit bugs
    or any unexpected outcomes. This is also encapsualtion since this wont be seen on any other
    class and hides the long codes.
*/

public class Confirmation {
    
    /*
        This is for checking the customer if it exists or not
        1. get the id from the parameter then check the database
        2. gets the result through resultset and see if it matches
            if it does then that means the customer exists.
            thus, returning true
    */
    public boolean doesCustomerExist(int customerID){
        try (Connection connection = DBConnection.getConnection()){
            String sql = "SELECT customer_id FROM customers WHERE customer_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error on method doesCustomerExist: " + e);
        }
        return false;
    }
    
    /*
        -- check for duplication of product id
        This method is similar for the customer but this time it'll check the product id if its is
        duplicated or not. This is to avoid the same Primary Key with any of the product that is about
        to get created.
    */
    public boolean isProductIDDuplicated(int productID){
        try (Connection connection = DBConnection.getConnection()){
            String duplicateSQL = "SELECT product_id FROM products WHERE product_id = ?";
            PreparedStatement duplicate = connection.prepareStatement(duplicateSQL);
            duplicate.setInt(1, productID);
            ResultSet result = duplicate.executeQuery();
            if (result.next()){
                String errorMessage = "The product ID already exist.";
                JOptionPane.showMessageDialog(null, errorMessage, "Product Creation", JOptionPane.ERROR_MESSAGE);
                return true;
                }
        } catch (SQLException e) {
            System.out.println("Error on method isProductIDDuplicated: " + e);
        }
        return false;
    }
    
    /*
        -- check for the product if it exists
        Same logic for the isCustomerExisting, but for the product to check
        if the product exists.
    */
    public boolean doesProductExist(int productID){
        try (Connection connection = DBConnection.getConnection()){
            String existsSQL = "SELECT product_id FROM products WHERE product_id = ?";
            PreparedStatement exists = connection.prepareStatement(existsSQL);
            exists.setInt(1, productID);
            ResultSet result = exists.executeQuery();
            if (result.next()){
                return true;
            } else {
                String errorMessage = "The product " + productID + " does not exist in the system.";
                JOptionPane.showMessageDialog(null, errorMessage, "Product Search", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("Error on method doesProductExist: " + e);
            return false;
        }
        return false;
    }
    
    public boolean isUsernameDuplicated(String username){
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
    
    public boolean checkLogin(String username, String password){
        
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


}
