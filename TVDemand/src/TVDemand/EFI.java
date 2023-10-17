package TVDemand;

public class EFI {
    int inventoryID;
    int filmID;

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public EFI(int inventoryID, int filmID) {
        this.inventoryID = inventoryID;
        this.filmID = filmID;
    }
    
    
}
