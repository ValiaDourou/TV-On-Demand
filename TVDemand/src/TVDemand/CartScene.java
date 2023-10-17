package TVDemand;

public class CartScene {
int inventoryID;
String title;

    public int getInventoryID() {
        return inventoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CartScene(int inventoryID, String title) {
        this.inventoryID = inventoryID;
        this.title = title;
    }
  
}
