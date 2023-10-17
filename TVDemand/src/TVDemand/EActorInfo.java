package TVDemand;


public class EActorInfo {
    int actorID;
    String firstname,lastname;

    public int getActorID() {
        return actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public EActorInfo(int actorID, String firstname, String lastname) {
        this.actorID = actorID;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
