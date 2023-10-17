/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package TVDemand;

import static TVDemand.ECityPopUpController.unknowncityid;
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

/**
 * FXML Controller class
 *
 * @author Nonths
 */
public class EAddressesAddPopUpController implements Initializable {

    @FXML
    private Button addCi;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField aTF;
    @FXML
    private TextField dTF;
    @FXML
    private TextField pcTF;
    @FXML
    private TextField pTF;
    @FXML
    private TableView<ECILIST> ciTable;
    @FXML
    private TableColumn<ECILIST, String> ciC;
    
    ObservableList<ECILIST> cit;
    private int cchosen=0;
    private String sciforadd;
    private int cforadd;
    private int alreadyaddadd=0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ciC.setCellValueFactory(new PropertyValueFactory<ECILIST,String>("city"));
        
        try {
            cit = getCitTable();
        } catch (SQLException ex) {
            Logger.getLogger(EAddressesAddPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        ciTable.setItems(cit);
    }  
    
    private ObservableList<ECILIST> getCitTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ECILIST> listC = FXCollections.observableArrayList();
                
        
        String s = "SELECT city FROM city;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ECILIST(rs.getString("city")));
        }
        
        return listC;  
    }

    @FXML
    private void AddDialog(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        
        String a = aTF.getText();
        String d = dTF.getText();
        String pc = pcTF.getText();
        String p = pTF.getText();
        
        if(cchosen==0){
        String h = "SELECT city_id FROM city WHERE city='Unknown';";
        Statement ha = con.createStatement();
        ResultSet haa = ha.executeQuery(h);
        while(haa.next())
        {
        cforadd=haa.getInt("city_id");
        }
        }
        else
        {
        String h = "SELECT city_id FROM city WHERE city='"+sciforadd+"';";
        Statement ha = con.createStatement();
        ResultSet haa = ha.executeQuery(h);
        while(haa.next())
        {
        cforadd=haa.getInt("city_id");
        }  
        }
        String aa = "SELECT address,district,city_id,postal_code,phone FROM address;";
        Statement staa = con.createStatement();
        ResultSet raa = staa.executeQuery(aa);
        
        while(raa.next())
        {
        if(raa.getString("address").equals(a) && raa.getString("district").equals(d) && raa.getString("postal_code").equals(pc) && raa.getString("phone").equals(p) && raa.getInt("city_id")==cforadd)
            {
             alreadyaddadd=1;
            }
        }
        if(alreadyaddadd==0)
        {
        String deez = "INSERT INTO address VALUES(NULL,'"+a+"','"+d+"',"+cforadd+",'"+pc+"','"+p+"');";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','address')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        alreadyaddadd=0;
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
        
        if (ciTable.getSelectionModel().getSelectedItem() != null) {
        ECILIST s = ciTable.getSelectionModel().getSelectedItem();
        sciforadd= s.getCity();
        }   
        cchosen=1;
        }
    }
    
}
