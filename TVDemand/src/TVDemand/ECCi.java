package TVDemand;


public class ECCi {
    int ciID;
    String name;
    int couID;

    public int getCiID() {
        return ciID;
    }

    public void setCiID(int ciID) {
        this.ciID = ciID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCouID() {
        return couID;
    }

    public void setCouID(int couID) {
        this.couID = couID;
    }

    public ECCi(int ciID, String name, int couID) {
        this.ciID = ciID;
        this.name = name;
        this.couID = couID;
    }
}
