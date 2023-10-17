package TVDemand;


public class customerCatalogue {
    int inventoryID;
    String title;
    String type;

    public int getInventoryID() {
        return inventoryID;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public customerCatalogue(int inventoryID, String title, String type) {
        this.inventoryID = inventoryID;
        this.title = title;
        this.type = type;
    }

    
    }
    
