
package TVDemand;

public class EFAST {
    int totalr;
    int id;
    String title;

    public EFAST(int totalr, int id, String title) {
        this.totalr = totalr;
        this.id = id;
        this.title = title;
    }

    public int getTotalr() {
        return totalr;
    }

    public void setTotalr(int totalr) {
        this.totalr = totalr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
