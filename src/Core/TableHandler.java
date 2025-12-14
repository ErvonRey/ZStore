package Core;

//import here:
import DatabaseConnection.DBConnection;
import User.ManageUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/*
    This class is for the DefaultTableModel, so we can handle the gathering of the data
    and pass it through the DefaultTableModel so it can be called and the model could be referenced.
*/

public class TableHandler {

    //This is for fetching and setting up the model for the purchase list of the customer
    public static DefaultTableModel getCustomerPurchaseTable(){
        
        String[] columns = { "Order Date", "Order ID", "Product Name", "Quantity", "Price" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        try (Connection connection = DBConnection.getConnection()) {
        
        String sql = """
            SELECT 
                o.order_date,
                o.order_id,
                p.prod_name,
                oi.orditem_quantity,
                p.prod_price
            FROM order_items oi
            JOIN orders o ON oi.order_id = o.order_id
            JOIN products p ON oi.product_id = p.product_id
            WHERE o.customer_id = ?
        """;

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, ManageUser.getCurrentCustomerID());
        ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String orderDate = rs.getString("order_date");
                int orderId = rs.getInt("order_id");
                String productId = rs.getString("prod_name");
                int quantity = rs.getInt("orditem_quantity");
                double price = rs.getDouble("prod_price");

                model.addRow(new Object[]{
                    orderDate,
                    orderId,
                    productId,
                    quantity,
                    price
                });
            }

        } catch (Exception e) {
            System.out.println("Error fetching order data: " + e);
        }
        
        return model;
    }
    
    //this is for returning a table model with all the admins listed on the database
    public static DefaultTableModel getAdminUsersTable(){
        
        String[] columns = { "User ID", "Admin ID", "Name", "Email"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        try (Connection connection = DBConnection.getConnection()) {
        
        String sql = """
            SELECT 
                u.user_id,
                a.admin_id,
                a.adm_name,
                a.adm_email
            FROM users u
            JOIN admins a ON a.user_id = u.user_id
        """;

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("user_id");
                int adminID = rs.getInt("admin_id");
                String name = rs.getString("adm_name");
                String email = rs.getString("adm_email");

                model.addRow(new Object[]{
                    userID,
                    adminID,
                    name,
                    email
                });
            }

        } catch (Exception e) {
            System.out.println("Error fetching order data: " + e);
        }
        
        return model;
    }
    
    //similar to the getAdminUsersTable(), but for customers.
    public static DefaultTableModel getCustomerUsersTable(){
        
        String[] columns = { "User ID", "Customer ID", "Name", "Email", "Money Spent"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        try (Connection connection = DBConnection.getConnection()) {
        
        String sql = """
            SELECT 
                u.user_id,
                c.customer_id,
                c.cus_name,
                c.cus_email,
                SUM(o.order_total) AS money_spent
            FROM users u
            JOIN customers c ON c.user_id = u.user_id
            LEFT JOIN orders o ON o.customer_id = c.customer_id
            GROUP BY u.user_id, c.customer_id, c.cus_name, c.cus_email;
        """;

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("user_id");
                int customerID = rs.getInt("customer_id");
                String name = rs.getString("cus_name");
                String email = rs.getString("cus_email");
                String moneySpent = rs.getString("money_spent");

                model.addRow(new Object[]{
                    userID,
                    customerID,
                    name,
                    email,
                    moneySpent
                });
            }

        } catch (Exception e) {
            System.out.println("Error fetching order data: " + e);
        }
        
        return model;
    }
    
    //similar to the getAdminUsersTable(), and the getCustomerUsersTable() but for the clerk.
    public static DefaultTableModel getClerkUsersTable(){
        
        String[] columns = { "User ID", "Clerk ID", "Name", "Email"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        try (Connection connection = DBConnection.getConnection()) {
        
        String sql = """
            SELECT 
                u.user_id,
                c.clerk_id,
                c.cle_name,
                c.cle_email
            FROM users u
            JOIN clerks c ON c.user_id = u.user_id
        """;

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("user_id");
                int clerkID = rs.getInt("clerk_id");
                String name = rs.getString("cle_name");
                String email = rs.getString("cle_email");

                model.addRow(new Object[]{
                    userID,
                    clerkID,
                    name,
                    email
                });
            }

        } catch (Exception e) {
            System.out.println("Error fetching order data: " + e);
        }
        
        return model;
    }
    
    //this is for returning a table model with all the products available on the database
    public static DefaultTableModel getProductTable(){
        String[] columns = { "Product ID", "Product Name", "Price"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
             @Override
            public boolean isCellEditable(int row, int column) {
                return false; //not allowing editing of cells
            }
        };
        
        try (Connection connection = DBConnection.getConnection()) {
        
        String sql = """
            SELECT
            
            product_id,
            prod_name,
            prod_price
            
            FROM
            
            products         
            ORDER BY
            prod_name
            ASC;
        """;

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int productID = rs.getInt("product_id");
                String productName = rs.getString("prod_name");
                double price = rs.getDouble("prod_price");

                model.addRow(new Object[]{
                    productID,
                    productName,
                    price,
                });
            }

        } catch (Exception e) {
            System.out.println("Error fetching order data: " + e);
        }
        
        return model;
    }
    
    /*
        This method returns the searched product via Product Name and the below this method has the same
        method name but with the different data type. This demonstrates polymorphism, specifically;
        method overloading, not overriding. Since it has the same method name but different datatype parameters.
    */
    public static DefaultTableModel getSearchedProduct(String searchProduct){
        
        String[] columns = { "Product ID", "Product Name", "Price"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        try (Connection connection = DBConnection.getConnection()) {
        
        //returns products whose names contain the search string,
        //similar to Java's variable.matches("") but in SQL.
        String sql = """
            SELECT * FROM products
            WHERE prod_name LIKE CONCAT('%', ?, '%');
        """;

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + searchProduct + "%");
        ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int productID = rs.getInt("product_id");
                String productName = rs.getString("prod_name");
                double price = rs.getDouble("prod_price");

                model.addRow(new Object[]{
                    productID,
                    productName,
                    price,
                });
            }
            
        } catch (Exception e) {
            System.out.println("Error fetching order data: " + e);
        }
        
        return model;
    }
    
    //same method name but different datatype in the parameter
    //search via ID but will return to the getSearchedProduct(productName) method.
    public static DefaultTableModel getSearchedProduct(int productID) {
        
        try (Connection connection = DBConnection.getConnection()) {

            String sql = """
                SELECT * FROM products
                WHERE product_id = ?;
            """;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) { //if product ID exist then get its name then return it to clerk
                String productName = rs.getString("prod_name");
                return getSearchedProduct(productName);
            }

        } catch (Exception e) {
            System.out.println("Error fetching product by ID: " + e);
        }
        
        //returns nothing since not found
        return new DefaultTableModel(
        new String[]{"Product ID", "Product Name", "Price"}, 
        0
        );
    }
}
