package TVDemand;

import static TVDemand.ECountryPopUpController.couidForPop;
import static TVDemand.ECountryPopUpController.unknowncountryid;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ECityPopUpController implements Initializable {

    @FXML
    private TableView<ECCi> cityTable;
    @FXML
    private TableColumn<ECCi, Integer> cityID;
    @FXML
    private TableColumn<ECCi, Integer> countryID;
    @FXML
    private TableColumn<ECCi, String> name;
    @FXML
    private Button addCo;
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteCo;
    @FXML
    private Label youcantTF;
    private int addedunknown=0;
    public static int  unknowncityid ;
    private int sci=0;
    public static int cityidForPop;
    private int  unknowncoforci;
    private int cant=0;
    
    ObservableList<ECCi> cic;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cityID.setCellValueFactory(new PropertyValueFactory<ECCi,Integer>("ciID"));
        name.setCellValueFactory(new PropertyValueFactory<ECCi,String>("name"));
        countryID.setCellValueFactory(new PropertyValueFactory<ECCi,Integer>("couID"));
        
        
        try {
            cic = getCiTable();
        } catch (SQLException ex) {
            Logger.getLogger(ECityPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        cityTable.setItems(cic);
    }    
    
    private ObservableList<ECCi> getCiTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ECCi> listC = FXCollections.observableArrayList();
                
        
        String s = "SELECT city_id,city,country_id FROM city;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ECCi(rs.getInt("city_id"),rs.getString("city"),rs.getInt("country_id")));
            if(rs.getString("city").equals("Unknown"))
            {
                addedunknown=1;
            }
        }
        String ch = "SELECT country_id FROM country WHERE country='Unknown';";
        Statement stc = conCat.createStatement();
        ResultSet rsc = stc.executeQuery(ch);
        while (rsc.next())
        {
            unknowncoforci=rsc.getInt("country_id");
        }
        if(addedunknown==0){
        String ss = "INSERT INTO city VALUES(NULL,'Unknown',"+unknowncoforci+");";
        Statement u = conCat.createStatement();
        ResultSet ul = u.executeQuery(ss);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','city')";
        Statement statfl = conCat.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        
        String aa = "SELECT city_id FROM city WHERE city='Unknown';";
        Statement a = conCat.createStatement();
        ResultSet lul = a.executeQuery(aa);
        
        while(lul.next())
            {
                listC.add(new ECCi(lul.getInt("city_id"),"Unknown",unknowncoforci));
                unknowncityid = lul.getInt("city_id");
            }
        }
        return listC;  
    }

    @FXML
    private void onClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
        sci=1;
        if (cityTable.getSelectionModel().getSelectedItem() != null) {
        ECCi s = cityTable.getSelectionModel().getSelectedItem();
        cityidForPop = s.getCiID();
        }   
        }
    }

    @FXML
    private void AddDialog(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ECityAddPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
       ECityAddPopUpController ECiPopUpController = fxmlLoader.getController();
        
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
    private void deletefromList(ActionEvent event) throws SQLException {
         DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        String sf = "SELECT city_id FROM city WHERE city='Unknown'";
            Statement stf = conCat.createStatement();
            ResultSet rsf = stf.executeQuery(sf);
            while(rsf.next()){
                if(rsf.getInt("city_id")==cityidForPop){
                cant=1;
                }
                if(cant==0){
            String s = "UPDATE address SET city_id="+rsf.getInt("city_id")+" WHERE city_id="+cityidForPop+";";
            Statement st = conCat.createStatement();
            ResultSet rs = st.executeQuery(s);
            String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','address')";
            Statement statfl = conCat.createStatement();
            ResultSet rfl = statfl.executeQuery(fl);
  
           
            String d = "DELETE FROM city WHERE city_id="+cityidForPop+";";
            Statement dt = conCat.createStatement();
            ResultSet rd = dt.executeQuery(d);
            String fld="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','city')";
            Statement statfld = conCat.createStatement();
           ResultSet rfld = statfld.executeQuery(fld);}}
            cant=0;
        
        getUpTable();
    }
    
    private void getUpTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ECCi> listC = FXCollections.observableArrayList();
        
        String s = "SELECT city_id,city,country_id FROM city;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ECCi(rs.getInt("city_id"),rs.getString("city"),rs.getInt("country_id")));
            
        }
        cityID.setCellValueFactory(new PropertyValueFactory<ECCi,Integer>("ciID"));
        name.setCellValueFactory(new PropertyValueFactory<ECCi,String>("name"));
        countryID.setCellValueFactory(new PropertyValueFactory<ECCi,Integer>("couID"));
        
        cityTable.setItems(listC);
    }
}
