/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TVDemand;

/**
 *
 * @author Nonths
 */
public class CustomerP {
    int paymentID;
    int prentalID;
    float amount;
    String pdate;

    public CustomerP(int paymentID, int prentalID, float amount, String pdate) {
        this.paymentID = paymentID;
        this.prentalID = prentalID;
        this.amount = amount;
        this.pdate = pdate;
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
