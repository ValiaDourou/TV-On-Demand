
package TVDemand;


public class APersonTable {
    int id;
    String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public APersonTable(int id, String email) {
        this.id = id;
        this.email = email;
    }
    
}
