package TVDemand;

public class ECustomerR {
    int customerID;
    int rentalID;
    String date;
    int inventoryID;
    String type;
    
    public int getCustomerID() {
        return customerID;
    }

    public int getRentalID() {
        return rentalID;
    }

    public String getDate() {
        return date;
    }

    public int getInventoryID() {
        return inventoryID;
    }

    public String getType() {
        return type;
    }
    
    
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    public void setRentalID(int rentalID) {
        this.rentalID = rentalID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ECustomerR(int customerID, int rentalID, String date, int inventoryID, String type) {
        this.customerID = customerID;
        this.rentalID = rentalID;
        this.date = date;
        this.inventoryID = inventoryID;
        this.type = type;
    }    
}
