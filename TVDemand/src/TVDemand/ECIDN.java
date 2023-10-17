package TVDemand;


public class ECIDN {
 int categoryID;
 String name;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ECIDN(int categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }
}
