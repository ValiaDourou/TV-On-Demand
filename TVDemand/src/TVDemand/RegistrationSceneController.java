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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegistrationSceneController implements Initializable {

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
    private Button registerUser;
    @FXML
    private Label registrySuccessful;
    @FXML
    private Button cancelButton;
    @FXML
    private Button closeButton;
    
    
    
    @FXML
    public void registerUser() throws SQLException{
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
        int series = 0;
        int movies = 0;
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
                registrySuccessful.setText("Your account has already been taken into consideration.");}}
            
            while(queryResultC.next()){
            if(queryResultC.getString("email").equals(email)){
                error = 1;
                registrySuccessful.setText("Customer.This email has already been registered.");}}
                
            while(queryResultA.next()){
            if(queryResultA.getString("email").equals(email)){
                error = 1;
                registrySuccessful.setText("Admin.This email has already been registered.");}}
            
            while(queryResultE.next()){
            if(queryResultE.getString("email").equals(email)){
                error = 1;
                registrySuccessful.setText("Employee.This email has already been registered.");}}  
            
            
            
            
        if(error == 0){
            
            
        if(SeriesCheckBox.isSelected()){
        series = 1;
        }
        
        if(MoviesCheckBox.isSelected()){
        movies = 1;
        }
        
        if(series == 1 && movies == 0){
        verifyLogin = "INSERT INTO toBeRegistered VALUES ('" + firstName + "','" + lastName + "','" + email + "','" + address + "','" + district + "','" + postalCode + "','" + phoneNumber + "','" + city + "','" + country + "','s')";     
        ResultSet regSet = reg.executeQuery(verifyLogin);
        }
        
        if(series == 0 && movies == 1){
        verifyLogin = "INSERT INTO toBeRegistered VALUES ('" + firstName + "','" + lastName + "','" + email + "','" + address + "','" + district + "','" + postalCode + "','" + phoneNumber + "','" + city + "','" + country + "','m')";     
        ResultSet regSet = reg.executeQuery(verifyLogin);
        }
        
        if(series == 1 && movies == 1){
        verifyLogin = "INSERT INTO toBeRegistered VALUES ('" + firstName + "','" + lastName + "','" + email + "','" + address + "','" + district + "','" + postalCode + "','" + phoneNumber + "','" + city + "','" + country + "','ms')";     
        ResultSet regSet = reg.executeQuery(verifyLogin);
        }
        
        registrySuccessful.setText("Your request will be taken into consideration."); 
        
        } 
        }catch(SQLException e){}
        
        
        
       
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancelSceneChange(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene regScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(regScene);
        window.show();
    }

    @FXML
    private void closeButton(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
}
