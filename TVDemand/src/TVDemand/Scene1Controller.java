package TVDemand;

import java.awt.TextField;
import java.sql.Statement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;




public class Scene1Controller implements Initializable {
    public static String Email;
    private int countC = 0;
    private int countA = 0;
    private int countE = 0;

    @FXML private javafx.scene.control.TextField usernameTextField;
    @FXML private Label loginMessageLabel;
    @FXML private Button signUp;
    @FXML
    private Button cancelButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void goToRegScene(ActionEvent event) throws IOException {
        
        Parent troot = FXMLLoader.load(getClass().getResource("RegistrationScene.fxml"));
        Scene regScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(regScene);
        window.show();
    }
    
    private void goToCuScene(ActionEvent event) throws IOException {
        
        Parent troot = FXMLLoader.load(getClass().getResource("CustomerScene.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }
    
    private void goToEmScene(ActionEvent event) throws IOException {
        
        Parent troot = FXMLLoader.load(getClass().getResource("EmployeeScene.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }
    private void goToAdmScene(ActionEvent event) throws IOException {
        
        Parent troot = FXMLLoader.load(getClass().getResource("AHomeScene.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }
    
    @FXML
    private void loginbutton(ActionEvent event) throws IOException, SQLException
    {
        String username = usernameTextField.getText();
        countC=0;
        
        if(usernameTextField.getText().isBlank() == false){
            
            validateLogin(event);
            
        }
        
    }
    
    private void signUpButton(ActionEvent event) throws IOException, SQLException
    {
         
        goToRegScene(event);
                
    }
    
    public void validateLogin(ActionEvent event) throws SQLException{
        
        
                
        DBConnection connectNow = new DBConnection();
        Connection conCu = connectNow.createConnection();
        Connection conAdmin = connectNow.createConnection();
        Connection conEm = connectNow.createConnection();
        String verifyLoginCu = "SELECT COUNT(*) FROM customer WHERE email = '" + usernameTextField.getText() + "'";
        String verifyLoginAdmin = "SELECT COUNT(*) FROM administrator WHERE email = '" + usernameTextField.getText() + "'";
        String verifyLoginEm = "SELECT COUNT(*) FROM employee WHERE email = '" + usernameTextField.getText() + "'";
        try{
            Statement statCu = conCu.createStatement();
            Statement statAdmin = conAdmin.createStatement();
            Statement statEm = conEm.createStatement();
            ResultSet queryResultCu = statCu.executeQuery(verifyLoginCu);
            ResultSet queryResultAdmin = statAdmin.executeQuery(verifyLoginAdmin);
            ResultSet queryResultEm = statEm.executeQuery(verifyLoginEm);
            
            
            while(queryResultCu.next()){
            if(queryResultCu.getInt(1) == 1){
                loginMessageLabel.setText("Welcome!");
                if(countC == 0){
                    Email = usernameTextField.getText();
                    goToCuScene(event);}
            }
            else{
                countC = 1;  /* kanto kai edw auto p ginetai apo panw */
            }
            }
            
            while(queryResultAdmin.next()){
            if(queryResultAdmin.getInt(1) == 1){
                loginMessageLabel.setText("Welcome!");
                 if(countA == 0){
                    Email = usernameTextField.getText();
                    goToAdmScene(event);}
            }
            else{
                countA = 1;  
            }
            }
            
            while(queryResultEm.next()){
            if(queryResultEm.getInt(1) == 1){
                loginMessageLabel.setText("Welcome!");
                if(countE == 0){
                    Email = usernameTextField.getText();
                    goToEmScene(event);}
                }
            else{
                countE = 1;  
            }
            }
            
            if(countA ==1 && countC == 1 && countE == 1 ){
                loginMessageLabel.setText("Invalid username.");
                countA=0;
                countC=0;
                countE=0;
            }
            
        }catch (Exception e){e.printStackTrace();}
        
        
        
    }

    

    @FXML
    private void cancelExit(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void loginbutton(MouseEvent event) {
    }

          

    
    }
    

