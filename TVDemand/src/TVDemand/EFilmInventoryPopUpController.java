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


public class EFilmInventoryPopUpController implements Initializable {

    @FXML
    private TableView<EFI> inventoryTable;
    @FXML
    private TableColumn<EFI, Integer> filmID;
    @FXML
    private Button addCo;
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteCo;
    @FXML
    private Label youcantTF;
    @FXML
    private TableColumn<EFI, Integer> inventoryID;

    ObservableList<EFI> efi;
    public static int finvidForPop;
    private int sfinv=0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inventoryID.setCellValueFactory(new PropertyValueFactory<EFI,Integer>("inventoryID"));
        filmID.setCellValueFactory(new PropertyValueFactory<EFI,Integer>("filmID"));
        

        try {
            efi = getifTable();
        } catch (SQLException ex) {
            Logger.getLogger(EFilmInventoryPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        inventoryTable.setItems(efi);
        
    }    
    
    private ObservableList<EFI> getifTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<EFI> listC = FXCollections.observableArrayList();
                
        
        String s = "SELECT inventory_id,film_id FROM film_inventory;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next())
        {
            listC.add(new EFI(rs.getInt("inventory_id"),rs.getInt("film_id")));
        }

        return listC;   
    }

    @FXML
    private void onClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
        sfinv=1;
        if (inventoryTable.getSelectionModel().getSelectedItem() != null) {
        EFI s = inventoryTable.getSelectionModel().getSelectedItem();
        finvidForPop = s.getInventoryID();
        System.out.println(finvidForPop);
        }   
        }
    }

    @FXML
    private void AddDialog(ActionEvent event) throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EFilmInventoryAddPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
       EFilmInventoryAddPopUpController EFilmInventoryPopUpController = fxmlLoader.getController();
        
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
        
            String d = "DELETE FROM film_inventory WHERE inventory_id="+finvidForPop+";";
            Statement dt = conCat.createStatement();
            ResultSet rd = dt.executeQuery(d);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','film inventory')";
        Statement statfl = conCat.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
            String a = "DELETE FROM inventory WHERE inventory_id="+finvidForPop+";";
            Statement at = conCat.createStatement();
            ResultSet ra = at.executeQuery(a);
        String fli="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','inventory')";
        Statement statfli = conCat.createStatement();
        ResultSet rfli = statfli.executeQuery(fli);
        getUpTable();
    }
    
    private void getUpTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<EFI> listC = FXCollections.observableArrayList();
        
        String s = "SELECT inventory_id,film_id FROM film_inventory;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new EFI(rs.getInt("inventory_id"),rs.getInt("film_id")));
            
        }
        inventoryID.setCellValueFactory(new PropertyValueFactory<EFI,Integer>("inventoryID"));
        filmID.setCellValueFactory(new PropertyValueFactory<EFI,Integer>("filmID"));
        
        inventoryTable.setItems(listC);
    }
}
