package TVDemand;


public class EFT {
    int filmid;
    String title;
    String description;
    int releaseyear;
    String language;
    String oglanguage;
    int length;
    String rating;
    String specialfeatures;

    public int getFilmid() {
        return filmid;
    }

    public void setFilmid(int filmid) {
        this.filmid = filmid;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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

    public EFT(int filmid, String title, String description, int releaseyear, String language, String oglanguage, int length, String rating, String specialfeatures) {
        this.filmid = filmid;
        this.title = title;
        this.description = description;
        this.releaseyear = releaseyear;
        this.language = language;
        this.oglanguage = oglanguage;
        this.length = length;
        this.rating = rating;
        this.specialfeatures = specialfeatures;
    }
}
