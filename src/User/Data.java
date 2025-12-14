package User;

import DatabaseConnection.DBConnection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Data {
    
    /*
        This class is for storing the data of a user. It is all private however it is static, though
        it does still apply encapsulation as it hides the data or codes.
    */
    
    private static String username;
    private static String password;
    private static int role;
    private static String name;
    private static String email;
    private static String address;
    
    //--customer
    private static int customerID;
    
    //getters
    public static String getUsername(){ return username; }
    public static String getPassword(){ return password; }
    public static int getRole(){ return role; }
    public static String getName(){ return name; }
    public static String getEmail(){ return email; }
    public static String getAddress(){ return address; }
    
    //--customer - getter
    public static int getCustomerID(){ return customerID; }
    
    //setters
    public static void setUsername(String tempUsername){ username = tempUsername; }
    public static void setPassword(String tempPassword){ password = tempPassword; }
    public static void setRole(int tempRole){ role = tempRole; }
    public static void setName(String tempName){ name = tempName; }
    public static void setEmail(String tempEmail){ email = tempEmail; }
    public static void setAddress(String tempAddress){ address = tempAddress; }
    
    
    //--customer -setter
    public static void setCustomerID(int tempCustomerID){ customerID = tempCustomerID; }
    
    //getter for the whole information
    public static void fetchInformation(int userID){
        
        try (Connection connection = DBConnection.getConnection()){
            String fetchSQL = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(fetchSQL);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                setUsername(rs.getString("user_username"));
                setPassword(rs.getString("user_password"));
                setRole(rs.getInt("role_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error on method fetchInformation: " + e);
        }
        
        switch (getRole()) {
            case 0 -> {
                try (Connection connection = DBConnection.getConnection()){
                    String fetchCustomerSQL = "SELECT * FROM customers WHERE user_id = ?";
                    PreparedStatement ps = connection.prepareStatement(fetchCustomerSQL);
                    ps.setInt(1, userID);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()){
                        setName(rs.getString("cus_name"));
                        setEmail(rs.getString("cus_email"));
                        setAddress(rs.getString("cus_address"));
                    }
                } catch (SQLException e) { System.out.println("Error on method fetchCustomer: " + e); }
            
            }
            case 1 -> {
                try (Connection connection = DBConnection.getConnection()){
                    String fetchCustomerSQL = "SELECT * FROM customers WHERE user_id = ?";
                    PreparedStatement ps = connection.prepareStatement(fetchCustomerSQL);
                    ps.setInt(1, userID);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()){
                        setName(rs.getString("cus_name"));
                        setEmail(rs.getString("cus_email"));
                        setAddress(rs.getString("cus_address"));
                    }
                } catch (SQLException e) { System.out.println("Error on method fetchCustomer: " + e); }
            }
            case 2 -> {
                try (Connection connection = DBConnection.getConnection()){
                    String fetchClerkSQL = "SELECT * FROM clerks WHERE user_id = ?";
                    PreparedStatement ps = connection.prepareStatement(fetchClerkSQL);
                    ps.setInt(1, userID);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()){
                        setName(rs.getString("cle_name"));
                        setEmail(rs.getString("cle_email"));
                        setAddress(rs.getString("cle_address"));
                    }
                } catch (SQLException e) { System.out.println("Error on method fetchClerk: " + e); }
            }
            case 3 -> {
                try (Connection connection = DBConnection.getConnection()){
                    String fetchClerkSQL = "SELECT * FROM admins WHERE user_id = ?";
                    PreparedStatement ps = connection.prepareStatement(fetchClerkSQL);
                    ps.setInt(1, userID);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()){
                        setName(rs.getString("adm_name"));
                        setEmail(rs.getString("adm_email"));
                        setAddress(rs.getString("adm_address"));
                    }
                } catch (SQLException e) { System.out.println("Error on method fetchClerk: " + e); }
            }
            default -> {
            //ambot di mani mo perform kung walay naka log in HAHAHA
            }

        }
    }
    
}
