package Core;

//import here:

import DatabaseConnection.DBConnection;
import User.ManageUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;



public class TableHandler {

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
    
    public static DefaultTableModel getSearchedProduct(String searchProduct){
        
        String[] columns = { "Product ID", "Product Name", "Price"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        try (Connection connection = DBConnection.getConnection()) {
        
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

}
