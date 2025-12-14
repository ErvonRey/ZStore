package Core;

//import here:
import DatabaseConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class RecordTransaction {
    
    /*
        On this class, this is soley for creating entries on the table
        orders, and order_items on the database. This is also an example of encapsulation
        principles.
    */
    
    //variable for the reference id in this case its order id.
    private int orderID;
    
    //similar to the Functions class, this needs confirmations too, thus calling Confirmation class
    Confirmation confirm = new Confirmation();
    
    //Similar to the method on Functions class the method generateUserID().
    //this is so it can automatically generate the orderID.
    private void generateOrderID(){
        
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int tempOrderID = rs.getInt("order_id");
                int finalID = tempOrderID + 1;
                orderID = finalID;
            } else {
                orderID = 5000;
                //sets up the default lowest id value to 5,000
            }
        } catch (SQLException e) {
            System.out.println("Error on database method(autoIncrement orderID): " + e.getMessage());
        }
    }
    
    /*
        This is the method for adding an order, if the customer and clerk is successful at their transaction
        their transaction will generate an ID in which it will use the same orderID in the addOrderItem to
        identify the items were bought by this specific customer and was entertained by this specific clerk
        also kept the total amount to save total money spent.
    */
    public void addOrder(int customerID, int clerkID, double total){
        
        //generates the orderID, increment one from the latest id.
        generateOrderID();
        
        try (Connection connection = DBConnection.getConnection()){
            String orderSQL = "INSERT INTO orders(order_id, customer_id, clerk_id, order_total, order_date)"
                    + "VALUES(?,?,?,?,NOW())";
            PreparedStatement order = connection.prepareStatement(orderSQL);
            order.setInt(1, orderID);
            order.setInt(2, customerID);
            order.setInt(3, clerkID);
            order.setDouble(4, total);
            order.executeUpdate();
            String message = "Transaction is successful and has been saved!";
            JOptionPane.showMessageDialog(null, message, "Order Request", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            String errorMessage = "Something went wrong...";
            JOptionPane.showMessageDialog(null, errorMessage, "Order Request", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*
        This method will be called repeatedly for each product, it is called on the ClerkPage. To be
        more specific on the method checkOut(int clerkID, int customerID, double totalPrice);
    */
    public void addOrderItem(int productID, int quantity){
        
        //checking if the product exists. Just to catch any funny weird bugs
        if(confirm.doesProductExist(productID)==false) { return; };
        
        try (Connection connection = DBConnection.getConnection()){
            String OrderItemSQL = "INSERT INTO order_items(order_id, product_id, orditem_quantity)"
                    + "VALUES(?, ?, ?)";
            PreparedStatement orderItem = connection.prepareStatement(OrderItemSQL);
            orderItem.setInt(1, orderID);
            orderItem.setInt(2, productID);
            orderItem.setInt(3, quantity);
            orderItem.executeUpdate();
        } catch (SQLException e) {
            String errorMessage = "Something went wrong...";
            JOptionPane.showMessageDialog(null, errorMessage, "Order Request", JOptionPane.ERROR_MESSAGE);
        }
    }

}
