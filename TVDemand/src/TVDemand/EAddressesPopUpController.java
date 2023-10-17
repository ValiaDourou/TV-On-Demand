/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package TVDemand;

import static TVDemand.ECategoriesPopUpController.cidForPop;
import static TVDemand.ECityPopUpController.cityidForPop;
import static TVDemand.ECityPopUpController.unknowncityid;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nonths
 */
public class EAddressesPopUpController implements Initializable {

    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TableView<EADD> addressTable;
    @FXML
    private TableColumn<EADD, Integer> aid;
    @FXML
    private TableColumn<EADD, String> an;
    @FXML
    private TableColumn<EADD, String> di;
    @FXML
    private TableColumn<EADD, Integer> cid;
    @FXML
    private TableColumn<EADD, String> pc;
    @FXML
    private TableColumn<EADD, String> phone;

     ObservableList<EADD> aa;
     private int sa=0;
     public static int aidForPop;
     private int addedunknown=0;
     private int unknownaddid;
     private int unknownciforadd;
     private int cant=0;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aid.setCellValueFactory(new PropertyValueFactory<EADD,Integer>("aid"));
        an.setCellValueFactory(new PropertyValueFactory<EADD,String>("aname"));
        di.setCellValueFactory(new PropertyValueFactory<EADD,String>("adis"));
        cid.setCellValueFactory(new PropertyValueFactory<EADD,Integer>("ciid"));
        pc.setCellValueFactory(new PropertyValueFactory<EADD,String>("pc"));
        phone.setCellValueFactory(new PropertyValueFactory<EADD,String>("phone"));
        
        try {
            aa = getATable();
        } catch (SQLException ex) {
            Logger.getLogger(EAddressesPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        addressTable.setItems(aa);
        
    }  
    

    private ObservableList<EADD> getATable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<EADD> listC = FXCollections.observableArrayList();
                
        
        String s = "SELECT address_id,address,district,city_id,postal_code,phone FROM address;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new EADD(rs.getInt("address_id"),rs.getString("address"),rs.getString("district"),rs.getInt("city_id"),rs.getString("postal_code"),rs.getString("phone")));
            if(rs.getString("address").equals("Unknown"))
            {
                addedunknown=1;
            }
        }
        String ch = "SELECT city_id FROM city WHERE city='Unknown';";
        Statement stc = conCat.createStatement();
        ResultSet rsc = stc.executeQuery(ch);
        while (rsc.next())
        {
            unknownciforadd=rsc.getInt("city_id");
        }
        if(addedunknown==0){
        String ss = "INSERT INTO address VALUES(NULL,'Unknown','Unknown',"+unknownciforadd+",'Unknown','Unknown');";
        Statement u = conCat.createStatement();
        ResultSet ul = u.executeQuery(ss);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','address')";
        Statement statfl = conCat.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String aa = "SELECT address_id FROM address WHERE address='Unknown';";
        Statement a = conCat.createStatement();
        ResultSet lul = a.executeQuery(aa);
        
        while(lul.next())
            {
                listC.add(new EADD(lul.getInt("address_id"),"Unknown","Unknown",unknownciforadd,"Unknown","Unknown"));
                unknownaddid = lul.getInt("address_id");
            }
        }
        return listC;   
    }

    @FXML
    private void UpdateDialog(ActionEvent event) throws IOException, SQLException {
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        String aa = "SELECT address_id FROM address WHERE address='Unknown';";
        Statement a = conCat.createStatement();
        ResultSet lul = a.executeQuery(aa);
        while(lul.next()){
            if(lul.getInt("address_id")==aidForPop){
                cant=1;
            }
        }
        if(cant==0){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EAddressesUpdatePopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
       EAddressesUpdatePopUpController EUpPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        }
        cant=0;
        getUpTable();
    }

    @FXML
    private void deleteAdd(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();

            String oh = "SELECT address_id FROM address WHERE address='Unknown'";
            Statement dth = conCat.createStatement();
            ResultSet rdh = dth.executeQuery(oh);
            while(rdh.next()){
                if(rdh.getInt("address_id")==aidForPop){
                    cant=1;
                }
            if(cant==0){
            String s = "UPDATE customer SET address_id="+ rdh.getInt("address_id")+" WHERE address_id="+aidForPop+";";
            Statement st = conCat.createStatement();
            ResultSet rs = st.executeQuery(s);
            String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','customer');";
            Statement statfl = conCat.createStatement();
            ResultSet rfl = statfl.executeQuery(fl);
            String e = "UPDATE employee SET address_id="+rdh.getInt("address_id")+" WHERE address_id="+aidForPop+";";
            Statement et = conCat.createStatement();
            ResultSet re = et.executeQuery(e);
            String fle="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','employee');";
            Statement statfle = conCat.createStatement();
            ResultSet rfle = statfle.executeQuery(fle);
            String a = "UPDATE administrator SET address_id="+rdh.getInt("address_id")+" WHERE address_id="+aidForPop+";";
            Statement at = conCat.createStatement();
            ResultSet ra = at.executeQuery(a);
            String fla="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','administrator');";
            Statement statfla = conCat.createStatement();
            ResultSet rfla = statfla.executeQuery(fla);
            String d = "DELETE FROM address WHERE address_id="+aidForPop+";";
            Statement dt = conCat.createStatement();
            ResultSet rd = dt.executeQuery(d);
            String fld="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','address');";
            Statement statfld = conCat.createStatement();
            ResultSet rfld = statfld.executeQuery(fld);}}
            cant=0;
        
        getUpTable();
    }

    @FXML
    private void AddAdd(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EAddressesAddPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
       EAddressesAddPopUpController EAddPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        getUpTable();
    }

    @FXML
    private void Cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
        sa=1;
        if (addressTable.getSelectionModel().getSelectedItem() != null) {
        EADD s = addressTable.getSelectionModel().getSelectedItem();
        aidForPop = s.getAid();
        }   
        }
    }
    
    private void getUpTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<EADD> listC = FXCollections.observableArrayList();
        
        String s = "SELECT address_id,address,district,city_id,postal_code,phone FROM address;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new EADD(rs.getInt("address_id"),rs.getString("address"),rs.getString("district"),rs.getInt("city_id"),rs.getString("postal_code"),rs.getString("phone")));
            
        }
        aid.setCellValueFactory(new PropertyValueFactory<EADD,Integer>("aid"));
        an.setCellValueFactory(new PropertyValueFactory<EADD,String>("aname"));
        di.setCellValueFactory(new PropertyValueFactory<EADD,String>("adis"));
        cid.setCellValueFactory(new PropertyValueFactory<EADD,Integer>("ciid"));
        pc.setCellValueFactory(new PropertyValueFactory<EADD,String>("pc"));
        phone.setCellValueFactory(new PropertyValueFactory<EADD,String>("phone"));
        
        addressTable.setItems(listC);
    }
}
