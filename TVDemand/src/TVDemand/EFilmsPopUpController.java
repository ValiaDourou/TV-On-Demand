package TVDemand;

import static TVDemand.EActorsPopUpController.aidForPop;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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


public class EFilmsPopUpController implements Initializable {

    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TableView<EFT> filmTable;
    @FXML
    private TableColumn<EFT, Integer> fid;
    @FXML
    private TableColumn<EFT, String> ti;
    @FXML
    private TableColumn<EFT, String> de;
    @FXML
    private TableColumn<EFT, Integer> ry;
    @FXML
    private TableColumn<EFT, String> la;
    @FXML
    private TableColumn<EFT, String> ogla;
    @FXML
    private TableColumn<EFT, Integer> le;
    @FXML
    private TableColumn<EFT, String> ra;
    @FXML
    private TableColumn<EFT, String> sf;
    
    ObservableList<EFT> eF;
    private int a=0;
    public static int fidForPop;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       fid.setCellValueFactory(new PropertyValueFactory<EFT,Integer>("filmid"));
       ti.setCellValueFactory(new PropertyValueFactory<EFT,String>("title"));
       de.setCellValueFactory(new PropertyValueFactory<EFT,String>("description"));
       ry.setCellValueFactory(new PropertyValueFactory<EFT,Integer>("releaseyear"));
       la.setCellValueFactory(new PropertyValueFactory<EFT,String>("language"));
       ogla.setCellValueFactory(new PropertyValueFactory<EFT,String>("oglanguage"));
       le.setCellValueFactory(new PropertyValueFactory<EFT,Integer>("length"));
       ra.setCellValueFactory(new PropertyValueFactory<EFT,String>("rating"));
       sf.setCellValueFactory(new PropertyValueFactory<EFT,String>("specialfeatures"));
       
        
        try {
            eF = getEFilmInfo();
        } catch (SQLException ex) {
            Logger.getLogger(EFilmsPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
       filmTable.setItems(eF);
    }    

    private ObservableList<EFT> getEFilmInfo() throws SQLException{
        
        
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<EFT> listC = FXCollections.observableArrayList();
                
        try{
             
             String c = "SELECT f.film_id,f.title,f.description,f.release_year,f.length,f.rating,f.special_features,l.name,ol.name FROM film AS f INNER JOIN language AS l ON l.language_id=f.language_id LEFT JOIN language AS ol ON ol.language_id=f.original_language_id;";
             
             Statement statCat = conCat.createStatement();
            
             ResultSet rC = statCat.executeQuery(c);
             
             while(rC.next()){
             listC.add(new EFT(rC.getInt("f.film_id"),rC.getString("f.title"),rC.getString("f.description"),rC.getInt("f.release_year"),rC.getString("l.name"),rC.getString("ol.name"),rC.getInt("f.length"),rC.getString("f.rating"),rC.getString("f.special_features")));
             }
             
        }
        catch (Exception e){}
        return listC;
        }
    
    @FXML
    private void UpdateDialog(ActionEvent event) throws IOException {
        if(a==1){
            
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EFilmUpdatePopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        EFilmUpdatePopUpController EPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        
        a=0;
    }
        if(EFilmUpdatePopUpController.fupfin==1){
        Parent troot = FXMLLoader.load(getClass().getResource("EFilmsPopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
        }
    }

    @FXML
    private void deleteActor(ActionEvent event) throws SQLException, IOException {
         if(a==1)
        {
              a=0;
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        String a="DELETE FROM film_category WHERE film_id=" + fidForPop + ";"; 
        Statement st=conCat.createStatement();
        ResultSet r=st.executeQuery(a);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','film category')";
        Statement statfl = conCat.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String b="DELETE FROM film_actor WHERE film_id=" + fidForPop + ";"; 
        Statement stb=conCat.createStatement();
        ResultSet rb=stb.executeQuery(b);
                String fla="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','film actor')";
        Statement statfla = conCat.createStatement();
        ResultSet rfla = statfla.executeQuery(fla);
        String c="DELETE FROM film_inventory WHERE film_id=" + fidForPop + ";"; 
        Statement stc=conCat.createStatement();
        ResultSet rc=stc.executeQuery(c);
                String fli="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','film inventory')";
        Statement statfli = conCat.createStatement();
        ResultSet rfli = statfli.executeQuery(fli);
        String d="DELETE FROM film WHERE film_id=" + fidForPop + ";"; 
        Statement std=conCat.createStatement();
        ResultSet rd=std.executeQuery(d);
                String flf="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','film')";
        Statement statflf = conCat.createStatement();
        ResultSet rflf = statflf.executeQuery(flf);
         Parent troot = FXMLLoader.load(getClass().getResource("EFilmsPopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
        }
    }

    @FXML
    private void AddActor(ActionEvent event) throws IOException {
            
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EFilmAddPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        EFilmAddPopUpController EPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        if(EFilmAddPopUpController.finfAddA==1){
        Parent troot = FXMLLoader.load(getClass().getResource("EFilmsPopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();}
    }

    @FXML
    private void Cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         a=1;
         
        if (filmTable.getSelectionModel().getSelectedItem() != null) {
        EFT s = filmTable.getSelectionModel().getSelectedItem();
        fidForPop = s.getFilmid();   
    }   
        }
    }
    
}
