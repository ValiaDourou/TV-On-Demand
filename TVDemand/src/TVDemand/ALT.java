
package TVDemand;


public class ALT {
    int lid;
    String use;
    String date;
    String state;
    String type;
    String table;

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public ALT(int lid, String use, String date, String state, String type, String table) {
        this.lid = lid;
        this.use = use;
        this.date = date;
        this.state = state;
        this.type = type;
        this.table = table;
    }
}
