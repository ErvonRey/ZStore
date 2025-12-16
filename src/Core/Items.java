package Core;

    /*
        This class is a valid example of encapsulation. Since it is storing the values and hiding
        the information such as the productID, productName, and the price. It wont allow any
        connection to the variable since it is made private. Can only be accessed through setters and getters.
    */

public class Items {
    
    private int productID;
    private String productName;
    private double productPrice;

    public Items(int productID, String productName, double productPrice){
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }
    
    //getters
    public int getProductID(){ return productID;}
    public String getProductName(){ return productName;}
    public double getProductPrice(){ return productPrice;}
    
    //setters
    public void setProductID(int productID){ this.productID = productID;}
    public void setProductName(String productName){ this.productName = productName;}
    public void setProductPrice(double productPrice){ this.productPrice = productPrice;}

}
