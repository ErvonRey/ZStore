package Core;

//import here:

import DatabaseConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class Confirmation {
    
    public boolean isCustomerExisting(int customerID){
        try (Connection connection = DBConnection.getConnection()){
            String sql = "SELECT customer_id FROM customers WHERE customer_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error on method isProductIDDuplicated: " + e);
        }
        return false;
    }

    //-- check for duplication of product id
    public boolean isProductIDDuplicated(int productID){
        try (Connection connection = DBConnection.getConnection()){
            String duplicateSQL = "SELECT product_id FROM products WHERE product_id = ?";
            PreparedStatement duplicate = connection.prepareStatement(duplicateSQL);
            duplicate.setInt(1, productID);
            ResultSet result = duplicate.executeQuery();
            if (result.next()){
                String errorMessage = "The product ID already exists.";
                JOptionPane.showMessageDialog(null, errorMessage, "Product Creation", JOptionPane.ERROR_MESSAGE);
                return true;
                }
        } catch (SQLException e) {
            System.out.println("Error on method isProductIDDuplicated: " + e);
        }
        return false;
    }
    
    //-- check for the product if it exists
    public boolean isProductIDExisting(int productID){
        try (Connection connection = DBConnection.getConnection()){
            String existsSQL = "SELECT product_id FROM products WHERE product_id = ?";
            PreparedStatement exists = connection.prepareStatement(existsSQL);
            exists.setInt(1, productID);
            ResultSet result = exists.executeQuery();
            if (result.next()){
                return true;
            } else {
                String errorMessage = "The product " + productID + " does not exists.";
                JOptionPane.showMessageDialog(null, errorMessage, "Order Request", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("Error on method isProductIDDuplicated: " + e);
            return false;
        }
        return false;
    }


}
