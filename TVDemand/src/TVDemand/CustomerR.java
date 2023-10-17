/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TVDemand;

/**
 *
 * @author Nonths
 */
public class CustomerR {
    int rentalID;
    String date;
    int inventoryID;
    String type;

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

    public CustomerR(int rentalID, String date, int inventoryID, String type) {
        this.rentalID = rentalID;
        this.date = date;
        this.inventoryID = inventoryID;
        this.type = type;
    }
}
