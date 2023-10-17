package TVDemand;

public class ESID {
    
    int inventoryID;
    int seriesID;

    public int getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(int seriesID) {
        this.seriesID = seriesID;
    }

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public ESID(int inventoryID,int seriesID) {
        this.seriesID = seriesID;
        this.inventoryID = inventoryID;
    }
}
