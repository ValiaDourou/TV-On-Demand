
package TVDemand;


public class SeriesCatalogueList {
    int noseason;
    int releaseyear;
    int noofepisodes;

    public SeriesCatalogueList(int noseason, int releaseyear, int noofepisodes) {
        this.noseason = noseason;
        this.releaseyear = releaseyear;
        this.noofepisodes = noofepisodes;
    }

    public void setNoseason(int noseason) {
        this.noseason = noseason;
    }

    public void setReleaseyear(int releaseyear) {
        this.releaseyear = releaseyear;
    }

    public void setNoofepisodes(int noofepisodes) {
        this.noofepisodes = noofepisodes;
    }

    public int getNoseason() {
        return noseason;
    }

    public int getReleaseyear() {
        return releaseyear;
    }

    public int getNoofepisodes() {
        return noofepisodes;
    }
    
    
}
