/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package TVDemand;

import static TVDemand.ESeriesAddPopUpController.ncsForPop;
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

/**
 * FXML Controller class
 *
 * @author Nonths
 */
public class ELanguagesPopUpController implements Initializable {

    @FXML
    private TableView<ELIDN> langTable;
    @FXML
    private TableColumn<ELIDN, Integer> languageID;
    @FXML
    private TableColumn<ELIDN, String> name;
    @FXML
    private Button addL;
    @FXML
    private Button cancelButton;
    @FXML
    private Label youcantTF;
    @FXML
    private Button deleteL;
    
    private int sl=0;
    public static int lidForPop;
    private int addedunknown=0;
    ObservableList<ELIDN> lc;
    private String lnameForPop;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        languageID.setCellValueFactory(new PropertyValueFactory<ELIDN,Integer>("languageid"));
        name.setCellValueFactory(new PropertyValueFactory<ELIDN,String>("name"));
        
        try {
            lc = getLTable();
        } catch (SQLException ex) {
            Logger.getLogger(ELanguagesPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        langTable.setItems(lc);
    }    

    @FXML
    private void onClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
        sl=1;
        if (langTable.getSelectionModel().getSelectedItem() != null) {
        ELIDN s = langTable.getSelectionModel().getSelectedItem();
        lidForPop = s.getLanguageid();
        lnameForPop = s.getName();
        }   
        }
    }

    @FXML
    private void deletefromList(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        if(lnameForPop.equals("Unknown"))
        {
            youcantTF.setText("You can't erase this value!");
        }
        else
        {
            String a = "SELECT language_id FROM language WHERE name='Unknown';";
            Statement sa = conCat.createStatement();
            ResultSet ra = sa.executeQuery(a);
            while(ra.next()){
            String s = "UPDATE film SET language_id="+ra.getInt("language_id")+" WHERE language_id="+lidForPop+" OR original_language_id="+lidForPop+";";
            Statement st = conCat.createStatement();
            ResultSet rs = st.executeQuery(s);
                    String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','film')";
        Statement statfl = conCat.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
            String g = "UPDATE series SET language_id="+ra.getInt("language_id")+" WHERE language_id="+lidForPop+" OR original_language_id="+lidForPop+";";
            Statement gt = conCat.createStatement();
            ResultSet rg = gt.executeQuery(g);
                    String fls="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','series')";
        Statement statfls = conCat.createStatement();
        ResultSet rfls = statfls.executeQuery(fls);
            }
            
            String d = "DELETE FROM language WHERE language_id="+lidForPop+";";
            Statement dt = conCat.createStatement();
            ResultSet rd = dt.executeQuery(d);
                    String fll="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','language')";
        Statement statfll = conCat.createStatement();
        ResultSet rfll = statfll.executeQuery(fll);
        }
        getUpTable();
    }

    @FXML
    private void AddDialog(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ELanguagesAddPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
       ELanguagesAddPopUpController ELangPopUpController = fxmlLoader.getController();
        
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
    
    private ObservableList<ELIDN> getLTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ELIDN> listC = FXCollections.observableArrayList();
                
        
        String s = "SELECT language_id,name FROM language;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ELIDN(rs.getInt("language_id"),rs.getString("name")));
            if(rs.getString("name").equals("Unknown"))
            {
                addedunknown=1;
            }
        }
        
        if(addedunknown==0){
        String ss = "INSERT INTO language VALUES(NULL,'Unknown');";
        Statement u = conCat.createStatement();
        ResultSet ul = u.executeQuery(ss);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','language')";
        Statement statfl = conCat.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String aa = "SELECT language_id FROM language WHERE name='Unknown';";
        Statement a = conCat.createStatement();
        ResultSet lul = a.executeQuery(aa);
        
        while(lul.next())
            {
                listC.add(new ELIDN(lul.getInt("language_id"),"Unknown"));
            }
        }
        return listC;   
    }

    private void getUpTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ELIDN> listC = FXCollections.observableArrayList();
        
        String s = "SELECT language_id,name FROM language;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ELIDN(rs.getInt("language_id"),rs.getString("name")));
            
        }
        languageID.setCellValueFactory(new PropertyValueFactory<ELIDN,Integer>("languageid"));
        name.setCellValueFactory(new PropertyValueFactory<ELIDN,String>("name"));
        
        langTable.setItems(listC);
    }
    
}
