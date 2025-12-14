package Core;

//import here:
import DatabaseConnection.DBConnection;
import User.ManageUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Functions {
    
    /*
        This is the main functions for creating, updating, searching, and deleting any row on
        the database which is crucial since this is a system and must need to handle data.
    */
    
    //In order to update or create data it needs confirmation to see if theres any duplicates
    //that is why Confirmation class is called here. Encapsulation too btw
    Confirmation confirm = new Confirmation();
    
    /*
        This method is to generate user's ID so that it wont have duplicates and this was
        taught by sir Ivan back at first year. which is important since we can control the
        increment of the IDs.
    */
    private void generateUserID(){
        
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT user_id FROM users ORDER BY user_id DESC LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int tempID = rs.getInt("user_id");
                int finalID = tempID + 1;
                ManageUser.setCurrentUserID(finalID);
            } else {
                ManageUser.setCurrentUserID(1000);
                //sets up the default lowest id value to 1,000
            }
        } catch (SQLException e) {
            System.out.println("Error on database method(autoIncrement userID): " + e.getMessage());
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Sample">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods for CREATING rows or data for the database">
    public void addUser(String username, String password, int role, String name, String email, String address){
        
        generateUserID();
        
        switch (role) {
                case 1:
                    addCustomer(name, email, address);
                    break;
                case 2:
                    addClerk(name, email, address);
                    break;
                case 3:
                    addAdmin(name, email, address);
                    break;
                default:
                    String errorMessage = "Role does not exist.";
                    JOptionPane.showMessageDialog(null, errorMessage, "Account Creation", JOptionPane.ERROR_MESSAGE);
                    return;
            }
        
        try (Connection connection = DBConnection.getConnection()){
            String usersSQL = "INSERT INTO users(user_id, user_username, user_password, role_id, date_created)"
                    + "VALUES(?, ?, ?, ?, NOW())";
            PreparedStatement addUser = connection.prepareStatement(usersSQL);
            addUser.setInt(1, ManageUser.getCurrentUserID());
            addUser.setString(2, username);
            addUser.setString(3, password);
            addUser.setInt(4, role);
            addUser.executeUpdate();
            String message = "Successfully created a new account!";
            JOptionPane.showMessageDialog(null, message, "Account Creation", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println("Error on method adduser: " + e);
        }
    }
    
    public void addCustomer(String name, String email, String address){
        try (Connection connection = DBConnection.getConnection()){
            String customersSQL = "INSERT INTO customers(user_id, cus_name, cus_email, cus_address)"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement addCustomer = connection.prepareStatement(customersSQL);
            addCustomer.setInt(1, ManageUser.getCurrentUserID());
            addCustomer.setString(2, name);
            addCustomer.setString(3, email);
            addCustomer.setString(4, address);
            addCustomer.executeUpdate();
            } catch (SQLException e) {
            System.out.println("Error on method addCustomer: " + e);
        }
    }
    
    public void addClerk(String name, String email, String address){
        try (Connection connection = DBConnection.getConnection()){
            String customersSQL = "INSERT INTO clerks(user_id, cle_name, cle_email, cle_address)"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement addClerk = connection.prepareStatement(customersSQL);
            addClerk.setInt(1, ManageUser.getCurrentUserID());
            addClerk.setString(2, name);
            addClerk.setString(3, email);
            addClerk.setString(4, address);
            addClerk.executeUpdate();
            } catch (SQLException e) {
            System.out.println("Error on method addClerk: " + e);
        }
    }
    
    public void addAdmin(String name, String email, String address){
        try (Connection connection = DBConnection.getConnection()){
            String customersSQL = "INSERT INTO admins(user_id, adm_name, adm_email, adm_address)"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement addAdmin = connection.prepareStatement(customersSQL);
            addAdmin.setInt(1, ManageUser.getCurrentUserID());
            addAdmin.setString(2, name);
            addAdmin.setString(3, email);
            addAdmin.setString(4, address);
            addAdmin.executeUpdate();
            } catch (SQLException e) {
            System.out.println("Error on method addAdmin: " + e);
        }
    }
    
    public void addProduct(int productID, String productName, double price){
        
        //check if theres already an existing product id.
        if (confirm.isProductIDDuplicated(productID)==true){ return; }
        
        try (Connection connection = DBConnection.getConnection()){
            String productsSQL = "INSERT INTO products(product_id, prod_name, prod_price)"
                    + "VALUES(?,?,?)";
            PreparedStatement addProduct = connection.prepareStatement(productsSQL);
            addProduct.setInt(1, productID);
            addProduct.setString(2, productName);
            addProduct.setDouble(3, price);
            addProduct.executeUpdate();
            String message = "Successfully created a new product!";
            JOptionPane.showMessageDialog(null, message, "Product Creation", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println("Error on method addAdmin: " + e);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods for UPDATING data on the database">
    public void updateUser(int userID, String password){
        
        try (Connection connection = DBConnection.getConnection()){
            
            String updateSQL = "UPDATE users SET user_password = ? WHERE user_id = ?";
            PreparedStatement updateUser = connection.prepareStatement(updateSQL);
            updateUser.setString(1, password);
            updateUser.setInt(2, userID);
            updateUser.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error on method updateUser: " + e);
        }
    }
    
    public void updateCustomer(String name, String email, String address){
        try (Connection connection = DBConnection.getConnection()){
            String customersSQL = "UPDATE customers SET cus_name = ?, cus_email = ?, cus_address = ? WHERE user_id = ?";
            PreparedStatement update = connection.prepareStatement(customersSQL);
            update.setString(1, name);
            update.setString(2, email);
            update.setString(3, address);
            update.setInt(4, ManageUser.getCurrentUserID());
            update.executeUpdate();
            } catch (SQLException e) {
            System.out.println("Error on method updateCustomer: " + e);
        }
    }
    
    public void updateClerk(String name, String email, String address){
        try (Connection connection = DBConnection.getConnection()){
            String customersSQL = "UPDATE clerks SET cle_name = ?, cle_email = ?, cle_address = ? WHERE user_id = ?";
            PreparedStatement addClerk = connection.prepareStatement(customersSQL);
            addClerk.setString(1, name);
            addClerk.setString(2, email);
            addClerk.setString(3, address);
            addClerk.setInt(4, ManageUser.getCurrentUserID());
            addClerk.executeUpdate();
            } catch (SQLException e) {
            System.out.println("Error on method updateClerk: " + e);
        }
    }
    
    public void updateAdmin(String name, String email, String address){
        try (Connection connection = DBConnection.getConnection()){
            String customersSQL = "UPDATE admins SET adm_name = ?, adm_email = ?, adm_address = ? WHERE user_id = ?";
            PreparedStatement addAdmin = connection.prepareStatement(customersSQL);
            addAdmin.setString(1, name);
            addAdmin.setString(2, email);
            addAdmin.setString(3, address);
            addAdmin.setInt(4, ManageUser.getCurrentUserID());
            addAdmin.executeUpdate();
            } catch (SQLException e) {
            System.out.println("Error on method updateAdmin: " + e);
        }
    }
    
    public void updateProduct(int theCurrentProduct, int changeToProductID, String productName, double price){
        try (Connection connection = DBConnection.getConnection()){
            String productSQL = "UPDATE products SET product_id = ?, prod_name = ?, prod_price = ? WHERE product_id = ?";
            PreparedStatement updateProduct = connection.prepareStatement(productSQL);
            updateProduct.setInt(1, changeToProductID);
            updateProduct.setString(2, productName);
            updateProduct.setDouble(3, price);
            updateProduct.setDouble(4, theCurrentProduct);
            updateProduct.executeUpdate();
            String message = "Successfully updated a product!";
            JOptionPane.showMessageDialog(null, message, "Product Update", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
            System.out.println("Error on method updateProduct: " + e);
        }
    }    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods for DELETING stuff or RESETTING tables while keeping its fields">
    public void voidAccount(){
        
        int currentID = ManageUser.getCurrentUserID();
        
        try (Connection connection = DBConnection.getConnection()) {
            //SETTING THE FIELDS TO NULL INSTEAD OF REMOVING COMPLETELY
            //TO AVOID DATA ANOMALIES WITH PURCHASE HISTORY
            String usersDeleteSQL = "UPDATE users SET user_username = null, user_password = null, role_id = 0 WHERE user_id = ?";
            PreparedStatement usersDelete = connection.prepareStatement(usersDeleteSQL);
            usersDelete.setInt(1, currentID);
            usersDelete.executeUpdate();
            
            ManageUser.clearSession();
            } catch (SQLException e) {
            System.out.println("Error on database method(deleteAccount): " + e.getMessage());
        }
    }
    
    public void resetOrderHistory(){
        try (Connection connection = DBConnection.getConnection()) {
            String ordersResetSQL = "TRUNCATE TABLE orders;";
            String order_itemsResetSQL = "TRUNCATE TABLE order_items;";
            PreparedStatement orders = connection.prepareStatement(ordersResetSQL);
            PreparedStatement order_items = connection.prepareStatement(order_itemsResetSQL);
            orders.executeUpdate();
            order_items.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully cleared history on transactions.");
            } catch (SQLException e) {
            System.out.println("Error on database method(deleteAccount): " + e.getMessage());
        }
    }
    
    public void deletePurchaseHistory(int customerID) {
        try (Connection connection = DBConnection.getConnection()) {
            //deleting order_items of this customer's orders first
            String sqlItems = """
                DELETE oi
                FROM order_items oi
                JOIN orders o ON oi.order_id = o.order_id
                WHERE o.customer_id = ?;
            """;
            PreparedStatement psItems = connection.prepareStatement(sqlItems);
            psItems.setInt(1, customerID);
            psItems.executeUpdate();

            //Deleting orders for this customer next the whole stuff
            String sqlOrders = "DELETE FROM orders WHERE customer_id = ?;";
            PreparedStatement psOrders = connection.prepareStatement(sqlOrders);
            psOrders.setInt(1, customerID);
            psOrders.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfully deleted history of customer ID: " + customerID);

        } catch (SQLException e) {
            System.out.println("Error on database method(clearPurchaseHistory): " + e.getMessage());
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods for SEARCHING data on the database">
    public void searchCustomer(String customerName){
        
        try (Connection connection = DBConnection.getConnection()) {
        
        String sql = """
            SELECT customer_id, user_id, cus_name FROM customers
            WHERE cus_name LIKE CONCAT('%', ?, '%');
        """;

        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setString(1, customerName);
        
        ResultSet rs = ps.executeQuery();
        
        StringBuilder results = new StringBuilder();
        
        while (rs.next()) {
            
            int tempCustomerID = rs.getInt("customer_id");
            String name = rs.getString("cus_name");

            results.append("Name: ").append(name)
                   .append(" -=- Customer ID: ").append(tempCustomerID)
                   .append("\n");
            }

        if (results.length() == 0) {
            JOptionPane.showMessageDialog(null, "No customers found with name: " + customerName);
        } else {
            JOptionPane.showMessageDialog(null, results.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
        
        } catch (SQLException e) {
            System.out.println("Error fetching customer data: " + e);
        }
    }
    
    public void searchCustomer(int customerID){
        
        try (Connection connection = DBConnection.getConnection()) {
            String usersDeleteSQL = "SELECT u.user_username,c.customer_id, c.cus_name, c.cus_email, c.cus_address\n" +
                                    "FROM users u\n" +
                                    "JOIN customers c ON u.user_id = c.user_id\n" +
                                    "WHERE c.customer_id = ?;";
            PreparedStatement ps = connection.prepareStatement(usersDeleteSQL);
            ps.setInt(1, customerID);
            
            ResultSet rs = ps.executeQuery();
            
            StringBuilder results = new StringBuilder();
        
        while (rs.next()) {
            
            int tempCustomerID = rs.getInt("customer_id");
            String name = rs.getString("cus_name");

            results.append("Name: ").append(name)
                   .append(" -=- Customer ID: ").append(tempCustomerID)
                   .append("\n");
            }

        if (results.length() == 0) {
            JOptionPane.showMessageDialog(null, "No customers found with the ID: " + customerID);
        } else {
            JOptionPane.showMessageDialog(null, results.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
        
        } catch (SQLException e) {
            System.out.println("Error fetching customer data: " + e);
        }
    }
    // </editor-fold>

}
