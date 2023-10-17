/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package TVDemand;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nonths
 */
public class MyAccountCController implements Initializable {

    @FXML
    private Button CatalogueButton;
    @FXML
    private Button MyCartButton;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane HomeScene;
    @FXML
    private Button Xbutton;
    @FXML
    private Label NameLabel;
    @FXML
    private ImageView HomeButton;
    @FXML
    private ImageView XButton;
    @FXML
    private Button homeButton;
    @FXML
    private TextField FirstNameTextField;
    @FXML
    private TextField LastNameTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField AddressTextField;
    @FXML
    private TextField DistrictTextField;
    @FXML
    private TextField PCTextField;
    @FXML
    private TextField PhoneNumberTextField;
    @FXML
    private TextField CityTextField;
    @FXML
    private TextField CountryTextField;
    @FXML
    private CheckBox SeriesCheckBox;
    @FXML
    private CheckBox MoviesCheckBox;
    @FXML
    private Button MyAccountButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button MyRentalsButton;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            EmailTextField.setEditable(false);
            cuData();
        } catch (SQLException ex) {
            Logger.getLogger(MyAccountCController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        (CatalogueCController.cart).clear();
        Parent troot = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void cancelExit(ActionEvent event) {
        Stage stage = (Stage) Xbutton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void homeScene(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("CustomerScene.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    
    private void cuData() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        
        
        String showDataFCu = "SELECT c.first_name,c.last_name,c.email,a.address,a.district,a.postal_code,a.phone,ci.city,co.country,c.customer_type FROM customer AS c INNER JOIN address AS a ON c.address_id=a.address_id INNER JOIN city AS ci ON ci.city_id=a.city_id INNER JOIN country AS co ON co.country_id=ci.country_id WHERE c.email='" + Scene1Controller.Email + "'";
        
        
        
        try{
            Statement stat = con.createStatement();

            ResultSet queryResult = stat.executeQuery(showDataFCu);
            
            while(queryResult.next()){           
            FirstNameTextField.setText(queryResult.getString("c.first_name"));
            LastNameTextField.setText(queryResult.getString("c.last_name"));  
            EmailTextField.setText(queryResult.getString("c.email"));  
            AddressTextField.setText(queryResult.getString("a.address"));  
            DistrictTextField.setText(queryResult.getString("a.district"));  
            PCTextField.setText(queryResult.getString("a.postal_code"));  
            PhoneNumberTextField.setText(queryResult.getString("a.phone"));  
            CityTextField.setText(queryResult.getString("ci.city"));  
            CountryTextField.setText(queryResult.getString("co.country"));
            
            if(queryResult.getString("c.customer_type").equals("ms") ){
            SeriesCheckBox.setSelected(true);
            MoviesCheckBox.setSelected(true);
            }
        
            if(queryResult.getString("c.customer_type").equals("s") ){
            SeriesCheckBox.setSelected(true);
            }
            
            if(queryResult.getString("c.customer_type").equals("m") ){
            MoviesCheckBox.setSelected(true);
            }
            }
        }catch (Exception e){e.printStackTrace();}
    }
    
    @FXML
        private void saveData() throws SQLException{
        DBConnection connect = new DBConnection();
        Connection concon = connect.createConnection();
        
        String city = CityTextField.getText();
        String postalCode = PCTextField.getText();
        String country = CountryTextField.getText();
        String address = AddressTextField.getText();
        String lastName = LastNameTextField.getText();
        String district = DistrictTextField.getText();
        String firstName = FirstNameTextField.getText();
        String phoneNumber = PhoneNumberTextField.getText();
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
         String newDataCustomerC = "UPDATE customer SET first_name='" + firstName + "',last_name='" + lastName + "',customer_type='s' WHERE email='" + Scene1Controller.Email + "';"; 
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','customer')";
        Statement statfl = concon.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String newDataCustomerA = "SELECT address.address,address.postal_code,address.phone,address.district,address.city_id FROM address INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.email ='" + Scene1Controller.Email+"';";
        String newDataCustomerCi = "SELECT city.city,city.city_id FROM city INNER JOIN address ON address.city_id = city.city_id INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.email ='" + Scene1Controller.Email+"';";
        String newDataCustomerCo = "SELECT country.country,country.country_id FROM country INNER JOIN city ON country.country_id = city.country_id INNER JOIN address ON address.city_id = city.city_id INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.email ='" + Scene1Controller.Email+"';";
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
String ii="UPDATE customer SET address_id=" + dohh.getInt("address_id") +" WHERE email='" + Scene1Controller.Email + "';";
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
            String iii="UPDATE customer SET address_id=" + ctci +" WHERE email='" + Scene1Controller.Email + "';";
          Statement diihh=concon.createStatement();
            ResultSet diihhh=diihh.executeQuery(iii);
                    String flcu="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','customer')";
        Statement statflcu = concon.createStatement();
        ResultSet rflcu = statflcu.executeQuery(flcu);
        }
        }
       
        if(series == 0 && movies == 1){
         String newDataCustomerC = "UPDATE customer SET first_name='" + firstName + "',last_name='" + lastName + "',customer_type='m' WHERE email='" + Scene1Controller.Email + "';"; 
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','customer')";
        Statement statfl = concon.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String newDataCustomerA = "SELECT address.address,address.postal_code,address.phone,address.district,address.city_id FROM address INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.email ='" + Scene1Controller.Email+"';";
        String newDataCustomerCi = "SELECT city.city,city.city_id FROM city INNER JOIN address ON address.city_id = city.city_id INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.email ='" + Scene1Controller.Email+"';";
        String newDataCustomerCo = "SELECT country.country,country.country_id FROM country INNER JOIN city ON country.country_id = city.country_id INNER JOIN address ON address.city_id = city.city_id INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.email ='" + Scene1Controller.Email+"';";
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
String ii="UPDATE customer SET address_id=" + dohh.getInt("address_id") +" WHERE email='" + Scene1Controller.Email + "';";
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
            String iii="UPDATE customer SET address_id=" + ctci +" WHERE email='" + Scene1Controller.Email + "';";
          Statement diihh=concon.createStatement();
            ResultSet diihhh=diihh.executeQuery(iii);
                    String flcuu="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','customer')";
        Statement statflcuu = concon.createStatement();
        ResultSet rflcuu = statflcuu.executeQuery(flcuu);
        }
        
        
}
        
        if(series == 1 && movies == 1){
        String newDataCustomerC = "UPDATE customer SET first_name='" + firstName + "',last_name='" + lastName + "',customer_type='ms' WHERE email='" + Scene1Controller.Email + "';"; 
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','customer')";
        Statement statfl = concon.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String newDataCustomerA = "SELECT address.address,address.postal_code,address.phone,address.district,address.city_id FROM address INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.email ='" + Scene1Controller.Email+"';";
        String newDataCustomerCi = "SELECT city.city,city.city_id FROM city INNER JOIN address ON address.city_id = city.city_id INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.email ='" + Scene1Controller.Email+"';";
        String newDataCustomerCo = "SELECT country.country,country.country_id FROM country INNER JOIN city ON country.country_id = city.country_id INNER JOIN address ON address.city_id = city.city_id INNER JOIN customer ON customer.address_id = address.address_id WHERE customer.email ='" + Scene1Controller.Email+"';";
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
String ii="UPDATE customer SET address_id=" + dohh.getInt("address_id") +" WHERE email='" + Scene1Controller.Email + "';";
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
            String iii="UPDATE customer SET address_id=" + ctci +" WHERE email='" + Scene1Controller.Email + "';";
          Statement diihh=concon.createStatement();
            ResultSet diihhh=diihh.executeQuery(iii);
                    String flcuuu="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','customer')";
        Statement statflcuuu = concon.createStatement();
        ResultSet rflcuuu = statflcuuu.executeQuery(flcuuu);
        }
        }
        }

    @FXML
    private void MyRentalsSceneTransitionFromMyAccount(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("MyRentalsC.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void CatalogueSceneTransitionFromMyAccount(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("CatalogueC.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }
    
    @FXML
    private void MyCartSceneTransitionFromMyAccount(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("MyCartC.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    
    
}
