/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package TVDemand;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Nonths
 */
public class EPopUpCustomerInfoController implements Initializable {

    @FXML
    private TextField lastnameTF;
    @FXML
    private TextField firstnameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField districtTF;
    @FXML
    private Button updateButton;
    @FXML
    private TextField postalcodeTF;
    @FXML
    private TextField phonenumberTF;
    @FXML
    private TextField cityTF;
    @FXML
    private TextField countryTF;
    @FXML
    private CheckBox SeriesCheckBox;
    @FXML
    private CheckBox MoviesCheckBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emailTF.setEditable(false);
        try {
            customerDialog();
        } catch (SQLException ex) {
            Logger.getLogger(EPopUpCustomerInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void UpdateDialog(ActionEvent event) throws SQLException {
        DBConnection connect = new DBConnection();
        Connection concon = connect.createConnection();
        
        String city = cityTF.getText();
        String postalCode = postalcodeTF.getText();
        String country = countryTF.getText();
        String address = addressTF.getText();
        String lastName = lastnameTF.getText();
        String district = districtTF.getText();
        String firstName = firstnameTF.getText();
        String phoneNumber = phonenumberTF.getText();
        int series = 0;
        int movies = 0;
        
        Statement changeC = concon.createStatement();
        Statement changeA = concon.createStatement();
        Statement changeCo = concon.createStatement();
        Statement changeCi = concon.createStatement();
        Statement checkCou = concon.createStatement();
        Statement checkCit = concon.createStatement();
        Statement checkAddr = concon.createStatement();
        
        if(SeriesCheckBox.isSelected()){
        series = 1;
        }
        
        if(MoviesCheckBox.isSelected()){
        movies = 1;
        }
        
        if(series == 1 && movies == 0){
         String newDataCustomerC = "UPDATE customer SET first_name='" + firstName + "',last_name='" + lastName + "',customer_type='s' WHERE customer_id=" + ECustomerAccountController.cidForPop + ";"; 
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','customer')";
        Statement statfl = concon.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String newDataCustomerA = "SELECT address.address,address.postal_code,address.phone,address.district,address.city_id FROM address INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.customer_id =" +ECustomerAccountController.cidForPop + ";";
        String newDataCustomerCi = "SELECT city.city,city.city_id FROM city INNER JOIN address ON address.city_id = city.city_id INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.customer_id =" + ECustomerAccountController.cidForPop + ";";
        String newDataCustomerCo = "SELECT country.country,country.country_id FROM country INNER JOIN city ON country.country_id = city.country_id INNER JOIN address ON address.city_id = city.city_id INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.customer_id =" + ECustomerAccountController.cidForPop + ";";
        String ckeckCountry="SELECT country,country_id from country;";
        String ckeckCity="SELECT city,country_id,city_id from city;";
        String checkAddress="SELECT address_id,address,district,postal_code,city_id from address;";
        int coid=0;
        int ciid=0;
        int adid=0;
        int ccou=0;
        int ccit=0;
        int cadd=0;
        int countrytocity=0;
        int citytoaddress=0;
        int ctci=0;
        
        ResultSet changeCu = changeC.executeQuery(newDataCustomerC);
        ResultSet changeAd = changeA.executeQuery(newDataCustomerA);
        ResultSet changeCou = changeCo.executeQuery(newDataCustomerCo); 
        ResultSet changeCit = changeCi.executeQuery(newDataCustomerCi);
        ResultSet checkCo = checkCou.executeQuery(ckeckCountry);
        ResultSet checkCi = checkCit.executeQuery(ckeckCity);
        ResultSet checkAdd = checkAddr.executeQuery(checkAddress);
                
        while(checkCo.next())
        {
            if(checkCo.getString("country").equals(country))
            {
                ccou=1;
                countrytocity=checkCo.getInt("country_id");
            }
        }
        while(checkCi.next())
        {
            if(checkCi.getString("city").equals(city) && checkCi.getInt("country_id")==countrytocity)
            {
                ccit=1;
                citytoaddress=checkCi.getInt("city_id");
            }
        }
        while(checkAdd.next())
        {
            if(checkAdd.getString("address").equals(address) && checkAdd.getString("district").equals(district) && checkAdd.getString("postal_code").equals(postalCode) && checkAdd.getInt("city_id")==citytoaddress)
            {
                cadd=1;
                ctci=checkAdd.getInt("address_id");
            }
        }
        
        while(changeCou.next())
        {
            if(changeCou.getString("country.country").equals(country))
            {
               coid=1;
            }
        }

while(changeCit.next())
        {
            if(changeCit.getString("city.city").equals(city))
            {
               ciid=1;
            }
        }

while(changeAd.next())
        {
            if(changeAd.getString("address.address").equals(address) && changeAd.getString("address.district").equals(district) && changeAd.getString("address.postal_code").equals(postalCode) && changeAd.getString("address.phone").equals(phoneNumber))
            {
                adid=1;
            }
          } 

        if(coid==0 && ccou==0){
         String a="INSERT INTO country (country) VALUES ('"+country+"');";
         Statement aa=concon.createStatement();
            ResultSet Aaaa=aa.executeQuery(a);
                    String flc="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','country')";
        Statement statflc = concon.createStatement();
        ResultSet rflc = statflc.executeQuery(flc);
        }
        
        if((ciid==0 || coid==0)&&(ccit==0)){
            String d="SELECT country_id FROM country WHERE country='"+country+"';";
            Statement da=concon.createStatement();
            ResultSet daaa=da.executeQuery(d);
            while(daaa.next())
            {
             String b="INSERT INTO city (city,country_id) VALUES ('"+city+"',"+daaa.getInt("country_id")+");";
             Statement di=concon.createStatement();
            ResultSet dii=di.executeQuery(b);
                    String flci="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','city')";
        Statement statflci = concon.createStatement();
        ResultSet rflci = statflci.executeQuery(flci);
            }
        }
         
          if((adid==0 || ciid==0 || coid==0)&&(cadd==0)){
              String e="SELECT city_id FROM city WHERE city='"+city+"';";
            Statement ea=concon.createStatement();
            ResultSet eaaa=ea.executeQuery(e);
            while(eaaa.next()){
            
             String c="INSERT INTO address (address,district,city_id,postal_code,phone) VALUES ('"+address+"','"+district+"',"+ eaaa.getInt("city_id")  +",'"+postalCode+"','"+phoneNumber+"');";
             Statement eh=concon.createStatement();
ResultSet haaa=eh.executeQuery(c);
        String fla="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','address')";
        Statement statfla = concon.createStatement();
        ResultSet rfla = statfla.executeQuery(fla);
String ohhh="SELECT address_id FROM address WHERE address='"+address+"' AND city_id="+eaaa.getInt("city_id") +";";
            Statement doh=concon.createStatement();
            ResultSet dohh=doh.executeQuery(ohhh);
            while(dohh.next()){
String ii="UPDATE customer SET address_id=" + dohh.getInt("address_id") +" WHERE customer_id=" + ECustomerAccountController.cidForPop + ";";
Statement diih=concon.createStatement();
            ResultSet diihh=diih.executeQuery(ii);
                    String flcu="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','customer')";
        Statement statflcu = concon.createStatement();
        ResultSet rflcu = statflcu.executeQuery(flcu);
        }
          }
          }
        if(cadd==1&&ccit==1&&ccou==1)
        {
            String iii="UPDATE customer SET address_id=" + ctci +" WHERE customer_id=" + ECustomerAccountController.cidForPop + ";";
          Statement diihh=concon.createStatement();
            ResultSet diihhh=diihh.executeQuery(iii);
                    String flcuu="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','customer')";
        Statement statflcuu = concon.createStatement();
        ResultSet rflcuu = statflcuu.executeQuery(flcuu);
        }
        }
       
        if(series == 0 && movies == 1){
        String newDataCustomerC = "UPDATE customer SET first_name='" + firstName + "',last_name='" + lastName + "',customer_type='m' WHERE customer_id=" + ECustomerAccountController.cidForPop + ";"; 
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','customer')";
        Statement statfl = concon.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String newDataCustomerA = "SELECT address.address,address.postal_code,address.phone,address.district,address.city_id FROM address INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.customer_id =" +ECustomerAccountController.cidForPop + ";";
        String newDataCustomerCi = "SELECT city.city,city.city_id FROM city INNER JOIN address ON address.city_id = city.city_id INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.customer_id =" + ECustomerAccountController.cidForPop + ";";
        String newDataCustomerCo = "SELECT country.country,country.country_id FROM country INNER JOIN city ON country.country_id = city.country_id INNER JOIN address ON address.city_id = city.city_id INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.customer_id =" + ECustomerAccountController.cidForPop + ";";
        String ckeckCountry="SELECT country,country_id from country;";
        String ckeckCity="SELECT city,country_id,city_id from city;";
        String checkAddress="SELECT address_id,address,district,postal_code,city_id from address;";
        int coid=0;
        int ciid=0;
        int adid=0;
        int ccou=0;
        int ccit=0;
        int cadd=0;
        int countrytocity=0;
        int citytoaddress=0;
        int ctci=0;
        
        ResultSet changeCu = changeC.executeQuery(newDataCustomerC);
        ResultSet changeAd = changeA.executeQuery(newDataCustomerA);
        ResultSet changeCou = changeCo.executeQuery(newDataCustomerCo); 
        ResultSet changeCit = changeCi.executeQuery(newDataCustomerCi);
        ResultSet checkCo = checkCou.executeQuery(ckeckCountry);
        ResultSet checkCi = checkCit.executeQuery(ckeckCity);
        ResultSet checkAdd = checkAddr.executeQuery(checkAddress);
                
        while(checkCo.next())
        {
            if(checkCo.getString("country").equals(country))
            {
                ccou=1;
                countrytocity=checkCo.getInt("country_id");
            }
        }
        while(checkCi.next())
        {
            if(checkCi.getString("city").equals(city) && checkCi.getInt("country_id")==countrytocity)
            {
                ccit=1;
                citytoaddress=checkCi.getInt("city_id");
            }
        }
        while(checkAdd.next())
        {
            if(checkAdd.getString("address").equals(address) && checkAdd.getString("district").equals(district) && checkAdd.getString("postal_code").equals(postalCode) && checkAdd.getInt("city_id")==citytoaddress)
            {
                cadd=1;
                ctci=checkAdd.getInt("address_id");
            }
        }
        
        while(changeCou.next())
        {
            if(changeCou.getString("country.country").equals(country))
            {
               coid=1;
            }
        }

while(changeCit.next())
        {
            if(changeCit.getString("city.city").equals(city))
            {
               ciid=1;
            }
        }

while(changeAd.next())
        {
            if(changeAd.getString("address.address").equals(address) && changeAd.getString("address.district").equals(district) && changeAd.getString("address.postal_code").equals(postalCode) && changeAd.getString("address.phone").equals(phoneNumber))
            {
                adid=1;
            }
          } 

        if(coid==0 && ccou==0){
         String a="INSERT INTO country (country) VALUES ('"+country+"');";
         Statement aa=concon.createStatement();
            ResultSet Aaaa=aa.executeQuery(a);
                    String flc="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','country')";
        Statement statflc = concon.createStatement();
        ResultSet rflc = statflc.executeQuery(flc);
        }
        
        if((ciid==0 || coid==0)&&(ccit==0)){
            String d="SELECT country_id FROM country WHERE country='"+country+"';";
            Statement da=concon.createStatement();
            ResultSet daaa=da.executeQuery(d);
            while(daaa.next())
            {
             String b="INSERT INTO city (city,country_id) VALUES ('"+city+"',"+daaa.getInt("country_id")+");";
             Statement di=concon.createStatement();
            ResultSet dii=di.executeQuery(b);
                    String flci="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','city')";
        Statement statflci = concon.createStatement();
        ResultSet rflci = statflci.executeQuery(flci);
            }
        }
         
          if((adid==0 || ciid==0 || coid==0)&&(cadd==0)){
              String e="SELECT city_id FROM city WHERE city='"+city+"';";
            Statement ea=concon.createStatement();
            ResultSet eaaa=ea.executeQuery(e);
            while(eaaa.next()){
            
             String c="INSERT INTO address (address,district,city_id,postal_code,phone) VALUES ('"+address+"','"+district+"',"+ eaaa.getInt("city_id")  +",'"+postalCode+"','"+phoneNumber+"');";
             Statement eh=concon.createStatement();
ResultSet haaa=eh.executeQuery(c);
        String fla="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','address')";
        Statement statfla = concon.createStatement();
        ResultSet rfla = statfla.executeQuery(fla);
String ohhh="SELECT address_id FROM address WHERE address='"+address+"' AND city_id="+eaaa.getInt("city_id") +";";
            Statement doh=concon.createStatement();
            ResultSet dohh=doh.executeQuery(ohhh);
            while(dohh.next()){
String ii="UPDATE customer SET address_id=" + dohh.getInt("address_id") +" WHERE customer_id=" + ECustomerAccountController.cidForPop + ";";
Statement diih=concon.createStatement();
            ResultSet diihh=diih.executeQuery(ii);
                    String flcuu="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','customer')";
        Statement statflcuu = concon.createStatement();
        ResultSet rflcuu = statflcuu.executeQuery(flcuu);
        }
          }
          }
        if(cadd==1&&ccit==1&&ccou==1)
        {
            String iii="UPDATE customer SET address_id=" + ctci +" WHERE customer_id=" + ECustomerAccountController.cidForPop + ";";
          Statement diihh=concon.createStatement();
            ResultSet diihhh=diihh.executeQuery(iii);
                    String flcuuu="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','customer')";
        Statement statflcuuu = concon.createStatement();
        ResultSet rflcuuu = statflcuuu.executeQuery(flcuuu);
        }
        
        
}
        
        if(series == 1 && movies == 1){
        String newDataCustomerC = "UPDATE customer SET first_name='" + firstName + "',last_name='" + lastName + "',customer_type='ms' WHERE customer_id=" + ECustomerAccountController.cidForPop + ";"; 
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','customer')";
        Statement statfl = concon.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String newDataCustomerA = "SELECT address.address,address.postal_code,address.phone,address.district,address.city_id FROM address INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.customer_id =" +ECustomerAccountController.cidForPop + ";";
        String newDataCustomerCi = "SELECT city.city,city.city_id FROM city INNER JOIN address ON address.city_id = city.city_id INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.customer_id =" + ECustomerAccountController.cidForPop + ";";
        String newDataCustomerCo = "SELECT country.country,country.country_id FROM country INNER JOIN city ON country.country_id = city.country_id INNER JOIN address ON address.city_id = city.city_id INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.customer_id =" + ECustomerAccountController.cidForPop + ";";
        String ckeckCountry="SELECT country,country_id from country;";
        String ckeckCity="SELECT city,country_id,city_id from city;";
        String checkAddress="SELECT address_id,address,district,postal_code,city_id from address;";
        int coid=0;
        int ciid=0;
        int adid=0;
        int ccou=0;
        int ccit=0;
        int cadd=0;
        int countrytocity=0;
        int citytoaddress=0;
        int ctci=0;
        
        ResultSet changeCu = changeC.executeQuery(newDataCustomerC);
        ResultSet changeAd = changeA.executeQuery(newDataCustomerA);
        ResultSet changeCou = changeCo.executeQuery(newDataCustomerCo); 
        ResultSet changeCit = changeCi.executeQuery(newDataCustomerCi);
        ResultSet checkCo = checkCou.executeQuery(ckeckCountry);
        ResultSet checkCi = checkCit.executeQuery(ckeckCity);
        ResultSet checkAdd = checkAddr.executeQuery(checkAddress);
                
        while(checkCo.next())
        {
            if(checkCo.getString("country").equals(country))
            {
                ccou=1;
                countrytocity=checkCo.getInt("country_id");
            }
        }
        while(checkCi.next())
        {
            if(checkCi.getString("city").equals(city) && checkCi.getInt("country_id")==countrytocity)
            {
                ccit=1;
                citytoaddress=checkCi.getInt("city_id");
            }
        }
        while(checkAdd.next())
        {
            if(checkAdd.getString("address").equals(address) && checkAdd.getString("district").equals(district) && checkAdd.getString("postal_code").equals(postalCode) && checkAdd.getInt("city_id")==citytoaddress)
            {
                cadd=1;
                ctci=checkAdd.getInt("address_id");
            }
        }
        
        while(changeCou.next())
        {
            if(changeCou.getString("country.country").equals(country))
            {
               coid=1;
            }
        }

while(changeCit.next())
        {
            if(changeCit.getString("city.city").equals(city))
            {
               ciid=1;
            }
        }

while(changeAd.next())
        {
            if(changeAd.getString("address.address").equals(address) && changeAd.getString("address.district").equals(district) && changeAd.getString("address.postal_code").equals(postalCode) && changeAd.getString("address.phone").equals(phoneNumber))
            {
                adid=1;
            }
          } 

        if(coid==0 && ccou==0){
         String a="INSERT INTO country (country) VALUES ('"+country+"');";
         Statement aa=concon.createStatement();
            ResultSet Aaaa=aa.executeQuery(a);
                    String flc="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','country')";
        Statement statflc = concon.createStatement();
        ResultSet rflc = statflc.executeQuery(flc);
        }
        
        if((ciid==0 || coid==0)&&(ccit==0)){
            String d="SELECT country_id FROM country WHERE country='"+country+"';";
            Statement da=concon.createStatement();
            ResultSet daaa=da.executeQuery(d);
            while(daaa.next())
            {
             String b="INSERT INTO city (city,country_id) VALUES ('"+city+"',"+daaa.getInt("country_id")+");";
             Statement di=concon.createStatement();
            ResultSet dii=di.executeQuery(b);
                    String flci="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','city')";
        Statement statflci = concon.createStatement();
        ResultSet rflci = statflci.executeQuery(flci);
            }
        }
         
          if((adid==0 || ciid==0 || coid==0)&&(cadd==0)){
              String e="SELECT city_id FROM city WHERE city='"+city+"';";
            Statement ea=concon.createStatement();
            ResultSet eaaa=ea.executeQuery(e);
            while(eaaa.next()){
            
             String c="INSERT INTO address (address,district,city_id,postal_code,phone) VALUES ('"+address+"','"+district+"',"+ eaaa.getInt("city_id")  +",'"+postalCode+"','"+phoneNumber+"');";
             Statement eh=concon.createStatement();
ResultSet haaa=eh.executeQuery(c);
        String fla="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','address')";
        Statement statfla = concon.createStatement();
        ResultSet rfla = statfla.executeQuery(fla);
String ohhh="SELECT address_id FROM address WHERE address='"+address+"' AND city_id="+eaaa.getInt("city_id") +";";
            Statement doh=concon.createStatement();
            ResultSet dohh=doh.executeQuery(ohhh);
            while(dohh.next()){
String ii="UPDATE customer SET address_id=" + dohh.getInt("address_id") +" WHERE customer_id=" + ECustomerAccountController.cidForPop + ";";
Statement diih=concon.createStatement();
            ResultSet diihh=diih.executeQuery(ii);
                    String flcu="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','customer')";
        Statement statflcu = concon.createStatement();
        ResultSet rflcu = statflcu.executeQuery(flcu);
        }
          }
          }
        if(cadd==1&&ccit==1&&ccou==1)
        {
            String iii="UPDATE customer SET address_id=" + ctci +" WHERE customer_id=" + ECustomerAccountController.cidForPop + ";";
          Statement diihh=concon.createStatement();
            ResultSet diihhh=diihh.executeQuery(iii);
                    String flcuu="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','address')";
        Statement statflcuu = concon.createStatement();
        ResultSet rflcuu = statflcuu.executeQuery(flcuu);
        }
        }
        }
    
    private void customerDialog() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conc = connectNow.createConnection();
        
        String a = "SELECT c.first_name,c.last_name,c.email,c.customer_type,a.address,a.district,a.postal_code,a.phone,ci.city,co.country FROM customer AS c INNER JOIN address AS a ON a.address_id=c.address_id INNER JOIN city AS ci ON ci.city_id=a.city_id INNER JOIN country AS co ON co.country_id=ci.country_id WHERE c.customer_id="+ECustomerAccountController.cidForPop+";";
        
        Statement statE = conc.createStatement();
            
        ResultSet r = statE.executeQuery(a); 
        
        while(r.next()){
        firstnameTF.setText(r.getString("c.first_name")); 
        lastnameTF.setText(r.getString("c.last_name")); 
        emailTF.setText(r.getString("c.email")); 
        addressTF.setText(r.getString("a.address")); 
        districtTF.setText(r.getString("a.district")); 
        postalcodeTF.setText(r.getString("a.postal_code")); 
        phonenumberTF.setText(r.getString("a.phone"));
        cityTF.setText(r.getString("ci.city"));
        countryTF.setText(r.getString("co.country"));
        
        if((r.getString("c.customer_type")).equals("s")){
            SeriesCheckBox.setSelected(true);
        }
        
        if((r.getString("c.customer_type")).equals("m")){
            MoviesCheckBox.setSelected(true);
        }
        
        if((r.getString("c.customer_type")).equals("ms")){
            SeriesCheckBox.setSelected(true);
            MoviesCheckBox.setSelected(true);
        }
        }
    }
}
