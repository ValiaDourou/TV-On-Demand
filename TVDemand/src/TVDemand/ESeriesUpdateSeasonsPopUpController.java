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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nonths
 */
public class ESeriesUpdateSeasonsPopUpController implements Initializable {

    @FXML
    private Button updateButton;
    @FXML
    private TextField nosTF;
    @FXML
    private TextField noeTF;
    @FXML
    private TextField ryTF;
    @FXML
    private Button cancelButton;
    private int saddno,saddry,saddnoe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       DBConnection connectNow = new DBConnection();
         Connection con;
        try {
            con = connectNow.createConnection();
        
        

           String f = "SELECT season_number,release_year,number_of_episodes FROM seasons  WHERE series_id="+ ESeriesPopUpController.sidForPop+" AND season_number="+ESeriesUpdatePopUpController.saddnos+";";
        Statement stf = con.createStatement();
        ResultSet rf = stf.executeQuery(f);
        while(rf.next()){
            nosTF.setText(String.valueOf(rf.getInt("season_number")));
            noeTF.setText(String.valueOf(rf.getInt("number_of_episodes")));
            ryTF.setText(String.valueOf(rf.getInt("release_year")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(ESeriesUpdateSeasonsPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void UpdateDialog(ActionEvent event) throws SQLException, IOException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        saddno=Integer.valueOf(nosTF.getText());
        saddry=Integer.valueOf(ryTF.getText());
        saddnoe=Integer.valueOf(noeTF.getText());
        String deez = "UPDATE seasons SET season_number="+saddno+",release_year="+saddry+",number_of_episodes="+saddnoe+" WHERE series_id="+ESeriesPopUpController.sidForPop+" AND season_number="+ESeriesUpdatePopUpController.saddnos+";";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','seasons')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);

    }

    @FXML
    private void Cancel(ActionEvent event) {
    Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
}
