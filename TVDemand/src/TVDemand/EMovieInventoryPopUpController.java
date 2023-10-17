
package TVDemand;

import static TVDemand.EFilmInventoryPopUpController.finvidForPop;
import static TVDemand.ESeriesPopUpController.sidForPop;
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


public class EMovieInventoryPopUpController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private TableView<ESID> inventoryTable;
    @FXML
    private TableColumn<ESID, Integer> inventoryID;
    @FXML
    private Button addCo;
    @FXML
    private Button deleteCo;
    @FXML
    private Label youcantTF;
    ObservableList<ESID> esi;
    public static int sinvidForPop;
    private int ssinv=0;
    @FXML
    private TableColumn<ESID, Integer> seriesID;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       inventoryID.setCellValueFactory(new PropertyValueFactory<ESID,Integer>("inventoryID"));
       seriesID.setCellValueFactory(new PropertyValueFactory<ESID,Integer>("seriesID"));
        
        try {
            esi = getisTable();
        } catch (SQLException ex) {
            Logger.getLogger(EMovieInventoryPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
        inventoryTable.setItems(esi);
    }    
    
    private ObservableList<ESID> getisTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ESID> listC = FXCollections.observableArrayList();
                
        
        String s = "SELECT inventory_id,series_id FROM series_inventory;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next())
        {
            listC.add(new ESID(rs.getInt("inventory_id"),rs.getInt("series_id")));
        }

        return listC;   
    }



    @FXML
    private void Cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void onClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
        ssinv=1;
        if (inventoryTable.getSelectionModel().getSelectedItem() != null) {
        ESID s = inventoryTable.getSelectionModel().getSelectedItem();
        sinvidForPop = s.getInventoryID();

        }   
        }
        
    }
    
   

    @FXML
    private void AddDialog(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ESeriesInventoryPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
       ESeriesInventoryPopUpController ESeriesInventoryPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        getUpTable();
    }

    @FXML
    private void deletefromList(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        if(ssinv==1){
            String d = "DELETE FROM series_inventory WHERE inventory_id="+sinvidForPop+";";
            Statement dt = conCat.createStatement();
            ResultSet rd = dt.executeQuery(d);
                    String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','series inventory')";
        Statement statfl = conCat.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
            String a = "DELETE FROM inventory WHERE inventory_id="+sinvidForPop+";";
            Statement at = conCat.createStatement();
            ResultSet ra = at.executeQuery(a);
                    String fli="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','inventory')";
        Statement statfli = conCat.createStatement();
        ResultSet rfli = statfli.executeQuery(fli);}
        getUpTable();
        ssinv=0;
    }
    
    private void getUpTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ESID> listC = FXCollections.observableArrayList();
        
        String s = "SELECT inventory_id,series_id FROM series_inventory;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ESID(rs.getInt("inventory_id"),rs.getInt("series_id")));
            
        }
        inventoryID.setCellValueFactory(new PropertyValueFactory<ESID,Integer>("inventoryID"));
        seriesID.setCellValueFactory(new PropertyValueFactory<ESID,Integer>("seriesID"));
        
        inventoryTable.setItems(listC);
    }
}
