package TVDemand;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class MyCartList {
    int inventoryID;
    String title;
    String type;

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MyCartList(int inventoryID, String title, String type) {
        this.inventoryID = inventoryID;
        this.title = title;
        this.type = type;
    }
    }
