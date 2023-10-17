package TVDemand;

import static TVDemand.ELanguagesPopUpController.lidForPop;
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


public class ECategoriesPopUpController implements Initializable {

    @FXML
    private TableView<ECIDN> categoryTable;
    @FXML
    private TableColumn<ECIDN, Integer> categoryID;
    @FXML
    private TableColumn<ECIDN, String> name;
    @FXML
    private Button addC;
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteC;
    @FXML
    private Label youcantTF;
    
    private int sc=0;
    public static int cidForPop;
    ObservableList<ECIDN> cc;
    private String cnameForPop;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoryID.setCellValueFactory(new PropertyValueFactory<ECIDN,Integer>("categoryID"));
        name.setCellValueFactory(new PropertyValueFactory<ECIDN,String>("name"));
        
        
        try {
            cc = getCTable();
        } catch (SQLException ex) {
            Logger.getLogger(ECategoriesPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        categoryTable.setItems(cc);
        
    }    

    @FXML
    private void onClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
        sc=1;
        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
        ECIDN s = categoryTable.getSelectionModel().getSelectedItem();
        cidForPop = s.getCategoryID();
        cnameForPop = s.getName();
        }   
        }
    }

    @FXML
    private void AddDialog(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ECategoriesAddPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
       ECategoriesAddPopUpController ECatPopUpController = fxmlLoader.getController();
        
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

            
            String s = "DELETE FROM film_category WHERE category_id="+cidForPop+";";
            Statement st = conCat.createStatement();
            ResultSet rs = st.executeQuery(s);
            String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','film category')";
            Statement statfl = conCat.createStatement();
            ResultSet rfl = statfl.executeQuery(fl);
            String a = "DELETE FROM series_category WHERE category_id="+cidForPop+";";
            Statement at = conCat.createStatement();
            ResultSet ra = at.executeQuery(a);
            String fls="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','series category')";
            Statement statfls = conCat.createStatement();
            ResultSet rfls = statfls.executeQuery(fls);
            
            String d = "DELETE FROM category WHERE category_id="+cidForPop+";";
            Statement dt = conCat.createStatement();
            ResultSet rd = dt.executeQuery(d);
            String flc="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','category')";
            Statement statflc = conCat.createStatement();
            ResultSet rflc = statflc.executeQuery(flc);
        
        getUpTable();
    }
    
    private ObservableList<ECIDN> getCTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ECIDN> listC = FXCollections.observableArrayList();
                
        
        String s = "SELECT category_id,name FROM category;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ECIDN(rs.getInt("category_id"),rs.getString("name")));
        }

        return listC;   
    }

    private void getUpTable() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ECIDN> listC = FXCollections.observableArrayList();
        
        String s = "SELECT category_id,name FROM category;";
        Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ECIDN(rs.getInt("category_id"),rs.getString("name")));
            
        }
        categoryID.setCellValueFactory(new PropertyValueFactory<ECIDN,Integer>("categoryID"));
        name.setCellValueFactory(new PropertyValueFactory<ECIDN,String>("name"));
        
        categoryTable.setItems(listC);
    }
    
}
