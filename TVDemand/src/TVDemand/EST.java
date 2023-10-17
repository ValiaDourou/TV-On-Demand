package TVDemand;


public class EST {
    int seriesid;
    String title;
    String description;
    int releaseyear;
    String language;
    String oglanguage;
    int noofseasons;
    String rating;
    String specialfeatures;
    int noofepisodes;
    String completed;

    public int getSeriesid() {
        return seriesid;
    }

    public void setSeriesid(int seriesid) {
        this.seriesid = seriesid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(int releaseyear) {
        this.releaseyear = releaseyear;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOglanguage() {
        return oglanguage;
    }

    public void setOglanguage(String oglanguage) {
        this.oglanguage = oglanguage;
    }

    public int getNoofseasons() {
        return noofseasons;
    }

    public void setNoofseasons(int noofseasons) {
        this.noofseasons = noofseasons;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialfeatures() {
        return specialfeatures;
    }

    public void setSpecialfeatures(String specialfeatures) {
        this.specialfeatures = specialfeatures;
    }

    public int getNoofepisodes() {
        return noofepisodes;
    }

    public void setNoofepisodes(int noofepisodes) {
        this.noofepisodes = noofepisodes;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public EST(int seriesid, String title, String description, int releaseyear, String language, String oglanguage, int noofseasons, String rating, String specialfeatures, int noofepisodes, String completed) {
        this.seriesid = seriesid;
        this.title = title;
        this.description = description;
        this.releaseyear = releaseyear;
        this.language = language;
        this.oglanguage = oglanguage;
        this.noofseasons = noofseasons;
        this.rating = rating;
        this.specialfeatures = specialfeatures;
        this.noofepisodes = noofepisodes;
        this.completed = completed;
    }
    
}
