package TVDemand;


public class Seasons {
  int NoOfSeasons;
  int releaseYear;
  int NoOfEpisodes;  

    public int getNoOfSeasons() {
        return NoOfSeasons;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getNoOfEpisodes() {
        return NoOfEpisodes;
    }

    public void setNoOfSeasons(int NoOfSeasons) {
        this.NoOfSeasons = NoOfSeasons;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setNoOfEpisodes(int NoOfEpisodes) {
        this.NoOfEpisodes = NoOfEpisodes;
    }

    public Seasons(int NoOfSeasons, int releaseYear, int NoOfEpisodes) {
        this.NoOfSeasons = NoOfSeasons;
        this.releaseYear = releaseYear;
        this.NoOfEpisodes = NoOfEpisodes;
    }
}
