package TVDemand;

import static TVDemand.Scene1Controller.Email;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Nonths
 */
public class CustomerSceneController implements Initializable {

    
    
    
    private String firstName;
    @FXML
    private Button MyAccountButton;
    @FXML
    private Button CatalogueButton;
    @FXML
    private Button MyCartButton;
    @FXML
    private ImageView HomeButton;
    @FXML
    private Label NameLabel;
    @FXML
    private Button Xbutton;
    @FXML
    private ImageView XButton;
    @FXML
    private AnchorPane HomeScene;
    @FXML
    private Button homeButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button MyRentalsButton;
    
    

    @FXML
    private void cancelExit(ActionEvent event) {
        Stage stage = (Stage) Xbutton.getScene().getWindow();
        stage.close();
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {        
            showName();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void homeScene(ActionEvent event) throws IOException {
               
        Parent troot = FXMLLoader.load(getClass().getResource("CustomerScene.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    
    }
    
    public void showName() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        
        String showNameCu = "SELECT first_name FROM customer WHERE email = '" + Scene1Controller.Email + "'";
        try{
            Statement stat = con.createStatement();   
            ResultSet queryResult = stat.executeQuery(showNameCu);
            while(queryResult.next()){
            
           NameLabel.setText(queryResult.getString("first_name") + "!");  
        }
         }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private void myAccountSceneTransition(ActionEvent event){
        try{
        Parent troot = FXMLLoader.load(getClass().getResource("MyAccountC.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
        }catch (IOException e){e.printStackTrace();}
    }
    
    @FXML
    private void myRentalSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("MyRentalsC.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void CatalogueSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("CatalogueC.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void myCartSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("MyCartC.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }
    
}
