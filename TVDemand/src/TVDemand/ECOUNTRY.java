package TVDemand;


public class ECOUNTRY {
    int couID;
    String name;

    public int getCouID() {
        return couID;
    }

    public void setCouID(int couID) {
        this.couID = couID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ECOUNTRY(int couID, String name) {
        this.couID = couID;
        this.name = name;
    }
}
