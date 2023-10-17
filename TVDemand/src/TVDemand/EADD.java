package TVDemand;


public class EADD {
    int aid;
    String aname;
    String adis;
    int ciid;
    String pc;
    String phone;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAdis() {
        return adis;
    }

    public void setAdis(String adis) {
        this.adis = adis;
    }

    public int getCiid() {
        return ciid;
    }

    public void setCiid(int ciid) {
        this.ciid = ciid;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EADD(int aid, String aname, String adis, int ciid, String pc, String phone) {
        this.aid = aid;
        this.aname = aname;
        this.adis = adis;
        this.ciid = ciid;
        this.pc = pc;
        this.phone = phone;
    }
}
