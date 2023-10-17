
package TVDemand;

import static TVDemand.ECategoriesPopUpController.cidForPop;
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


public class ECountryPopUpController implements Initializable {

    @FXML
    private TableView<ECOUNTRY> countryTable;
    @FXML
    private TableColumn<ECOUNTRY, Integer> countryID;
    @FXML
    private TableColumn<ECOUNTRY, String> name;
    @FXML
    private Button addCo;
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteCo;
    @FXML
    private Label youcantTF;

    ObservableList<ECOUNTRY> cou;
    public static int couidForPop;
    private String counameForPop;
    private int scou=0;
    private int addedunknown=0;
    public static int unknowncountryid;
    private int cant=0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        countryID.setCellValueFactory(new PropertyValueFactory<ECOUNTRY,Integer>("couID"));
        name.setCellValueFactory(new PropertyValueFactory<ECOUNTRY,String>("name"));
        
        try {
            cou = getCouTable();
        } catch (SQLException ex) {
            Logger.getLogger(ECountryPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        countryTable.setItems(cou);
        
    } 
    
    private ObservableList<ECOUNTRY> getCouTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ECOUNTRY> listC = FXCollections.observableArrayList();
                
        
        String s = "SELECT country_id,country FROM country;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ECOUNTRY(rs.getInt("country_id"),rs.getString("country")));
            if(rs.getString("country").equals("Unknown"))
            {
                addedunknown=1;
            }
        }

        if(addedunknown==0){
        String ss = "INSERT INTO country VALUES(NULL,'Unknown');";
        Statement u = conCat.createStatement();
        ResultSet ul = u.executeQuery(ss);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','country')";
        Statement statfl = conCat.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        
        String aa = "SELECT country_id FROM country WHERE country='Unknown';";
        Statement a = conCat.createStatement();
        ResultSet lul = a.executeQuery(aa);
        
        while(lul.next())
            {
                listC.add(new ECOUNTRY(lul.getInt("country_id"),"Unknown"));
                unknowncountryid = lul.getInt("country_id");
            }
        }
        return listC;   
    }

    @FXML
    private void onClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
        scou=1;
        if (countryTable.getSelectionModel().getSelectedItem() != null) {
        ECOUNTRY s = countryTable.getSelectionModel().getSelectedItem();
        couidForPop = s.getCouID();
        counameForPop = s.getName();
        }   
        }
    }

    @FXML
    private void AddDialog(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ECountryAddPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
       ECountryAddPopUpController ECouPopUpController = fxmlLoader.getController();
        
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
        String f="SELECT country_id FROM country WHERE country='Unknown';";
        Statement tf=conCat.createStatement();
        ResultSet ft=tf.executeQuery(f);
        while(ft.next()){
            if(ft.getInt("country_id")==couidForPop){
                cant=1;
            }
            if(cant==0){
            String s = "UPDATE city SET country_id="+ft.getInt("country_id")+" WHERE country_id="+couidForPop+";";
            Statement st = conCat.createStatement();
            ResultSet rs = st.executeQuery(s);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','city')";
        Statement statfl = conCat.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
            String d = "DELETE FROM country WHERE country_id="+couidForPop+";";
            Statement dt = conCat.createStatement();
            ResultSet rd = dt.executeQuery(d);
        String fld="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','country')";
        Statement statfld = conCat.createStatement();
        ResultSet rfld = statfld.executeQuery(fld);
            }
        }
        cant=0;
        getUpTable();
    }
    
    private void getUpTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ECOUNTRY> listC = FXCollections.observableArrayList();
        
        String s = "SELECT country_id,country FROM country;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ECOUNTRY(rs.getInt("country_id"),rs.getString("country")));
            
        }
        countryID.setCellValueFactory(new PropertyValueFactory<ECOUNTRY,Integer>("couID"));
        name.setCellValueFactory(new PropertyValueFactory<ECOUNTRY,String>("name"));
        
        countryTable.setItems(listC);
    }
}
