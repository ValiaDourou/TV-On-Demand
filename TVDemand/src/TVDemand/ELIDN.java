
package TVDemand;

public class ELIDN {
    int languageid;
    String name;

    public int getLanguageid() {
        return languageid;
    }

    public void setLanguageid(int languageid) {
        this.languageid = languageid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ELIDN(int languageid, String name) {
        this.languageid = languageid;
        this.name = name;
    }
}
