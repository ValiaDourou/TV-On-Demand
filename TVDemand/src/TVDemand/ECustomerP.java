package TVDemand;

public class ECustomerP {
    int customerID;
    int paymentID;
    int prentalID;
    float amount;
    String pdate;

    public ECustomerP(int customerID, int paymentID, int prentalID, float amount, String pdate) {
        this.customerID = customerID;
        this.paymentID = paymentID;
        this.prentalID = prentalID;
        this.amount = amount;
        this.pdate = pdate;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public void setPrentalID(int prentalID) {
        this.prentalID = prentalID;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }
    
    public int getCustomerID() {
        return customerID;
    }
    
    public int getPaymentID() {
        return paymentID;
    }

    public int getPrentalID() {
        return prentalID;
    }

    public float getAmount() {
        return amount;
    }

    public String getPdate() {
        return pdate;
    }
}
