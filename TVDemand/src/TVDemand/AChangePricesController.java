package TVDemand;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;


public class AChangePricesController implements Initializable {

    @FXML
    private AnchorPane HomeScene;
    @FXML
    private Label NameLabel;
    @FXML
    private ImageView HomeButton;
    @FXML
    private ImageView XButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button UpdateButton;
    @FXML
    private Button Xbutton;
    @FXML
    private TextField seriesonlyTF;
    @FXML
    private TextField filmonlyTF;
    @FXML
    private TextField seriesTF;
    @FXML
    private TextField filmTF;
    @FXML
    private Button changepriceButton;
    @FXML
    private Button createAccountButton;
    @FXML
    private Button DeleteAccountsButton;
    @FXML
    private Button memployeesButton;
    @FXML
    private Button IncomeButtons;
    @FXML
    private Button logoutButton;
    @FXML
    private Button logButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBConnection connectNow = new DBConnection();
        try {
            Connection con = connectNow.createConnection();
        String s = "SELECT film,series,filmseries,seriesfilm FROM prices;";
        Statement stat = con.createStatement();   
        ResultSet qr = stat.executeQuery(s);
        while(qr.next()){
        filmonlyTF.setText(String.valueOf(qr.getFloat("film")));
        filmTF.setText(String.valueOf(qr.getFloat("filmseries")));
        seriesonlyTF.setText(String.valueOf(qr.getFloat("series")));
        seriesTF.setText(String.valueOf(qr.getFloat("seriesfilm")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(AChangePricesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void homeScene(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("AHomeScene.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {
        float s,f,fs,sf;
        s=Float.parseFloat(seriesonlyTF.getText());
        f=Float.parseFloat(filmonlyTF.getText());
        sf=Float.parseFloat(seriesTF.getText());
        fs=Float.parseFloat(filmTF.getText());
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        String aa = "UPDATE prices SET film="+f+",series="+s+",filmseries="+fs+",seriesfilm="+sf+";";
        Statement stat = con.createStatement();   
        ResultSet qr = stat.executeQuery(aa);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','prices')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
    }

    @FXML
    private void cancelExit(ActionEvent event) {
        Stage stage = (Stage) Xbutton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cpSceneTransition(ActionEvent event) {
    }

    @FXML
    private void caSceneTransition(ActionEvent event) throws IOException {
        (CatalogueCController.cart).clear();       
        Parent troot = FXMLLoader.load(getClass().getResource("ACreateAcc.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void daSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("ADeleteCOE.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void meSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("AChangeModes.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void incomeSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("AIncomes.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
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
    private void logSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("ALog.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }
    
}
