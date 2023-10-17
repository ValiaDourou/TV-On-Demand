package TVDemand;

public class AIncomeTable {
    String year;
    String month;
    float tamount;
    String type;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public float getTamount() {
        return tamount;
    }

    public void setTamount(float tamount) {
        this.tamount = tamount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AIncomeTable(String year, String month, float tamount, String type) {
        this.year = year;
        this.month = month;
        this.tamount = tamount;
        this.type = type;
    }
    
}
