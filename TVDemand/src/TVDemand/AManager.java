package TVDemand;

public class AManager {
    int id;
    String fname;
    String lname;
    String email;
    int aid;
    String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AManager(int id, String fname, String lname, String email, int aid, String date) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.aid = aid;
        this.date = date;
    }
}
