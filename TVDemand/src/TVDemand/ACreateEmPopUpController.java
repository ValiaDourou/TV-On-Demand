
package TVDemand;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ACreateEmPopUpController implements Initializable {

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
    private Button cancelButton;
    @FXML
    private Button registerUser;
    @FXML
    private Label registrySuccessful;
    @FXML
    private Button closeButton;
    private int a = 0,alreadyc=0,alreadyci=0,alreadya=0;
    private int coufci,cifora,aforc;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void cancelSceneChange(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("ACreateAcc.fxml"));
        Scene regScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(regScene);
        window.show();
    }

    @FXML
    private void registerUser(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        
        String verifyLogin;
        String city = CityTextField.getText();
        String email = EmailTextField.getText();
        String postalCode = PCTextField.getText();
        String country = CountryTextField.getText();
        String address = AddressTextField.getText();
        String lastName = LastNameTextField.getText();
        String district = DistrictTextField.getText();
        String firstName = FirstNameTextField.getText();
        String phoneNumber = PhoneNumberTextField.getText();

        int error = 0;
        
        try{
            
            String check = "SELECT * FROM toBeRegistered WHERE username ='" + email + "'";
            String checkA = "SELECT * FROM administrator WHERE email ='" + email + "'";
            String checkC = "SELECT * FROM customer WHERE email ='" + email + "'";
            String checkE = "SELECT * FROM employee WHERE email ='" + email + "'";
            
            Statement stat = con.createStatement();
            Statement statC = con.createStatement();
            Statement statA = con.createStatement();
            Statement statE = con.createStatement();
            Statement reg = con.createStatement();
           
            ResultSet queryResult = stat.executeQuery(check);
            ResultSet queryResultC = statC.executeQuery(checkC);
            ResultSet queryResultA = statA.executeQuery(checkA);
            ResultSet queryResultE = statE.executeQuery(checkE);
            
            while(queryResult.next()){
            if(queryResult.getString("username").equals(email)){
                error = 1;
                registrySuccessful.setText("This account has already been taken into consideration.");}}
            
            while(queryResultC.next()){
            if(queryResultC.getString("email").equals(email)){
                error = 1;
                registrySuccessful.setText("This email has already been registered.");}}
                
            while(queryResultA.next()){
            if(queryResultA.getString("email").equals(email)){
                error = 1;
                registrySuccessful.setText("This email has already been registered.");}}
            
            while(queryResultE.next()){
            if(queryResultE.getString("email").equals(email)){
                error = 1;
                registrySuccessful.setText("This email has already been registered.");}}  
            
            
            
            
        if(error == 0){

        String b = "SELECT country,country_id FROM country;";
        Statement bb=con.createStatement();
        ResultSet ba=bb.executeQuery(b);
        while(ba.next())
        {
            if(ba.getString("country").equals(country))
            {
                alreadyc=1;
                coufci=ba.getInt("country_id");
            }
        }
         String c = "SELECT city,country_id,city_id FROM city;";
        Statement cc=con.createStatement();
        ResultSet ca=cc.executeQuery(c);
        while(ca.next())
        {
            if(ca.getString("city").equals(city) && ca.getInt("country_id")==coufci)
            {
                alreadyci=1;
                cifora=ca.getInt("city_id");
            }
        }
        String d = "SELECT address_id,address,district,postal_code,phone,city_id FROM address;";
        Statement dd=con.createStatement();
        ResultSet da=dd.executeQuery(d);
        while(da.next())
        {
            if(da.getString("address").equals(address) && da.getString("district").equals(district) && da.getString("postal_code").equals(postalCode) && da.getString("phone").equals(phoneNumber) && da.getInt("city_id")==cifora)
            {
                alreadya=1;
                aforc=da.getInt("address_id");
            }
        }
        if(alreadyc==0){
        String e = "INSERT INTO country VALUES(NULL,'"+country+"');";
        Statement ee=con.createStatement();
        ResultSet ea=ee.executeQuery(e);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','country')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String eee = "SELECT country_id FROM country WHERE country='"+country+"';";
        Statement bee=con.createStatement();
        ResultSet eea=bee.executeQuery(eee);
        while(eea.next())
        {
                coufci=eea.getInt("country_id");
        }
        
        }
        if(alreadyci==0){
        String f = "INSERT INTO city VALUES(NULL,'"+city+"',"+coufci+");";
        Statement ff=con.createStatement();
        ResultSet fa=ff.executeQuery(f);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','city')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String see = "SELECT city_id FROM city WHERE city='"+city+"' AND country_id="+coufci+";";
        Statement sbee=con.createStatement();
        ResultSet sea=sbee.executeQuery(see);
        while(sea.next())
        {
                cifora=sea.getInt("city_id");
        }
        
        }
        if(alreadya==0){
        String g = "INSERT INTO address VALUES(NULL,'"+address+"','"+district+"',"+cifora+",'"+postalCode+"','"+phoneNumber+"');";
        Statement gg=con.createStatement();
        ResultSet ga=gg.executeQuery(g);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','address')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String h = "SELECT address_id FROM address WHERE address='"+address+"' AND district='"+district+"' AND postal_code='"+postalCode+"' AND phone='"+phoneNumber+"' AND city_id="+cifora+";";
        Statement hh=con.createStatement();
        ResultSet ha=hh.executeQuery(h);
        while(ha.next())
        {
                aforc=ha.getInt("address_id");
        }
        
        }
        String i = "INSERT INTO employee VALUES(NULL,'"+firstName+"','"+lastName+"','"+email+"',"+aforc+",CURDATE());";
        Statement ii=con.createStatement();
        ResultSet ia=ii.executeQuery(i);
        String flc="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','employee')";
        Statement statflc = con.createStatement();
        ResultSet rflc = statflc.executeQuery(flc);
        registrySuccessful.setText("The account has been created."); 
        
        } 
        }catch(SQLException e){}
    }

    @FXML
    private void closeButton(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("ACreateAcc.fxml"));
        Scene regScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(regScene);
        window.show();
    }
    
}
