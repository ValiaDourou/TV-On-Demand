package TVDemand;

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


public class EAddressesUpdatePopUpController implements Initializable {

    @FXML
    private Button UpdateA;
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
    private TextField ciTF;
    @FXML
    private TableView<ECILIST> ciTable;
    @FXML
    private TableColumn<ECILIST, String> ciC;
    
    ObservableList<ECILIST> addu;
    private int cchosen=0;
    private String sciforup;
    private int cforup;
    private int alreadyaddup=0;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ciC.setCellValueFactory(new PropertyValueFactory<ECILIST,String>("city"));
        ciTF.setEditable(false);
        
        try {
            showInfo();

            addu = getCitTable();
        } catch (SQLException ex) {
            Logger.getLogger(EAddressesUpdatePopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            ciTable.setItems(addu);
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
    private void UpdateAdd(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        
        String a = aTF.getText();
        String d = dTF.getText();
        String pc = pcTF.getText();
        String p = pTF.getText();
        String ep = "SELECT city_id FROM city WHERE city='"+sciforup+"';";
        
        Statement ty = con.createStatement();
        ResultSet rt = ty.executeQuery(ep);
        while(rt.next()){
            cforup = rt.getInt("city_id");
        }
        if(cchosen==1){
        String up = "UPDATE address SET address='"+a+"',district='"+d+"',postal_code='"+pc+"',phone='"+p+"',city_id="+cforup+" WHERE address_id="+EAddressesPopUpController.aidForPop+";";
        Statement ll = con.createStatement();
        ResultSet re = ll.executeQuery(up);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','address')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        ciTF.setText(sciforup);
        }
        else{
        String up = "UPDATE address SET address='"+a+"',district='"+d+"',postal_code='"+pc+"',phone='"+p+"' WHERE address_id="+EAddressesPopUpController.aidForPop+";";
        Statement ll = con.createStatement();
        ResultSet re = ll.executeQuery(up);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','address')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
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
        sciforup= s.getCity();
        }   
        cchosen=1;
        }
    }
    
    private void showInfo() throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        
        String ada = "SELECT address,district,postal_code,city_id,phone FROM address WHERE address_id="+EAddressesPopUpController.aidForPop+";";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(ada);
        
        while(rs.next()){
            aTF.setText(rs.getString("address"));
            dTF.setText(rs.getString("district"));
            pcTF.setText(rs.getString("postal_code"));
            pTF.setText(rs.getString("phone"));
            
            String aaa = "SELECT city FROM city WHERE city_id="+rs.getInt("city_id")+";";
            Statement ty = conCat.createStatement();
            ResultSet rt = ty.executeQuery(aaa);
            while(rt.next()){
                ciTF.setText(rt.getString("city"));
            }
        }
    }
}
