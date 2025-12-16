package Core;

public class CartItem {
    
    /*
        This class is a valid example of encapsulation. Since it is storing the values and hiding
        the information such as the productID, productName, price, and quantity. It wont allow any
        connection since it is made private. Can only be accessed through setters and getters.
    */

    //variables
    private int productID;
    private String productName;
    private double price;
    private int quantity;
    
    //the constructor to store the information of the item
    public CartItem(int productID, String productName, double price, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
    
    //the getters
    public int getProductId() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    //setter for updating the item
    public void setProductId(int productId) {
        this.productID = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
