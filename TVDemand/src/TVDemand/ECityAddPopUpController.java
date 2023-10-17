/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package TVDemand;

import static TVDemand.ECityPopUpController.cityidForPop;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ECityAddPopUpController implements Initializable {

    @FXML
    private Button addC;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField nTF;
    @FXML
    private TableView<ECiCou> couTable;
    @FXML
    private TableColumn<ECiCou, String> couC;
    
    ObservableList<ECiCou> nc;
    private int cchosen=0;
    private int couforcity;
    private int alreadyaddci=0;
    private String scouforcity;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        couC.setCellValueFactory(new PropertyValueFactory<ECiCou,String>("countryname"));
        
        try {
            nc = getCiCouTable();
        } catch (SQLException ex) {
            Logger.getLogger(ECityAddPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        couTable.setItems(nc);
    }    
    
    private ObservableList<ECiCou> getCiCouTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ECiCou> listC = FXCollections.observableArrayList();
                
        
        String s = "SELECT country FROM country;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ECiCou(rs.getString("country")));
        }
        return listC;
    }
        
    @FXML
    private void AddDialog(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        
        String l = nTF.getText();
        
        if(cchosen==0){
        String h = "SELECT country_id FROM country WHERE country='Unknown';";
        Statement ha = con.createStatement();
        ResultSet haa = ha.executeQuery(h);
        while(haa.next())
        {
        couforcity=haa.getInt("country_id");
        }
        }
        else
        {
        String h = "SELECT country_id FROM country WHERE country='"+scouforcity+"';";
        Statement ha = con.createStatement();
        ResultSet haa = ha.executeQuery(h);
        while(haa.next())
        {
        couforcity=haa.getInt("country_id");
        }  
        }
        String aa = "SELECT city,country_id FROM city;";
        Statement staa = con.createStatement();
        ResultSet raa = staa.executeQuery(aa);
        
        while(raa.next())
        {
        if(raa.getString("city").equals(l) && raa.getInt("country_id")==couforcity)
            {
             alreadyaddci=1;
            }
        }
        if(alreadyaddci==0)
        {
        String deez = "INSERT INTO city VALUES(NULL,'"+l+"',"+couforcity+");";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','city')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        alreadyaddci=0;
        cchosen=0;
    }

    @FXML
    private void Cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void OnClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
        
        if (couTable.getSelectionModel().getSelectedItem() != null) {
        ECiCou s = couTable.getSelectionModel().getSelectedItem();
        scouforcity= s.getCountryname();
        }   
        cchosen=1;
        }
    }
    
}
