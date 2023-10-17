/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package TVDemand;

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

/**
 * FXML Controller class
 *
 * @author Nonths
 */
public class ESeriesPopUpController implements Initializable {

    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button updateButton;
    @FXML
    private TableView<EST> SeriesTable;
    @FXML
    private TableColumn<EST, String> ti;
    @FXML
    private TableColumn<EST, String> de;
    @FXML
    private TableColumn<EST, Integer> ry;
    @FXML
    private TableColumn<EST, String> la;
    @FXML
    private TableColumn<EST, String> ogla;
    @FXML
    private TableColumn<EST, String> ra;
    @FXML
    private TableColumn<EST, String> sf;
    @FXML
    private TableColumn<EST, Integer> noe;
    @FXML
    private TableColumn<EST, String> co;
    @FXML
    private TableColumn<EST, Integer> sid;

    ObservableList<EST> eS;
    private int a=0;
    public static int sidForPop;
    @FXML
    private TableColumn<EST, Integer> nos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       sid.setCellValueFactory(new PropertyValueFactory<EST,Integer>("seriesid"));
       ti.setCellValueFactory(new PropertyValueFactory<EST,String>("title"));
       de.setCellValueFactory(new PropertyValueFactory<EST,String>("description"));
       ry.setCellValueFactory(new PropertyValueFactory<EST,Integer>("releaseyear"));
       la.setCellValueFactory(new PropertyValueFactory<EST,String>("language"));
       ogla.setCellValueFactory(new PropertyValueFactory<EST,String>("oglanguage"));
       nos.setCellValueFactory(new PropertyValueFactory<EST,Integer>("noofseasons"));
       ra.setCellValueFactory(new PropertyValueFactory<EST,String>("rating"));
       sf.setCellValueFactory(new PropertyValueFactory<EST,String>("specialfeatures"));
       noe.setCellValueFactory(new PropertyValueFactory<EST,Integer>("noofepisodes"));
       co.setCellValueFactory(new PropertyValueFactory<EST,String>("completed"));
        
        
        try {
            eS = getESeriesInfo();
        } catch (SQLException ex) {
            Logger.getLogger(ESeriesPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       SeriesTable.setItems(eS);
    }    

    @FXML
    private void deleteSeries(ActionEvent event) throws IOException, SQLException {
         if(a==1)
        {
              a=0;
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        String a="DELETE FROM series_category WHERE series_id=" + sidForPop + ";"; 
        Statement st=conCat.createStatement();
        ResultSet r=st.executeQuery(a);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','series category')";
        Statement statfl = conCat.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String b="DELETE FROM series_actor WHERE series_id=" + sidForPop + ";"; 
        Statement stb=conCat.createStatement();
        ResultSet rb=stb.executeQuery(b);
                String fls="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','series actor')";
        Statement statfls = conCat.createStatement();
        ResultSet rfls = statfls.executeQuery(fls);
        String c="DELETE FROM series_inventory WHERE series_id=" + sidForPop + ";"; 
        Statement stc=conCat.createStatement();
        ResultSet rc=stc.executeQuery(c);
                String fli="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','series inventory')";
        Statement statfli = conCat.createStatement();
        ResultSet rfli = statfli.executeQuery(fli);
        String d="DELETE FROM series WHERE series_id=" + sidForPop + ";"; 
        Statement std=conCat.createStatement();
        ResultSet rd=std.executeQuery(d);
                String fld="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','series')";
        Statement statfld = conCat.createStatement();
        ResultSet rfld = statfld.executeQuery(fld);
         Parent troot = FXMLLoader.load(getClass().getResource("ESeriesPopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
        }
    }

    @FXML
    private void AddSeries(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ESeriesAddPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        ESeriesAddPopUpController EPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        if(ESeriesAddPopUpController.finsAddA==1){
        Parent troot = FXMLLoader.load(getClass().getResource("ESeriesPopUp.fxml"));
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
    
    private ObservableList<EST> getESeriesInfo() throws SQLException{
        
        
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<EST> listC = FXCollections.observableArrayList();
                
        try{
             
             String c = "SELECT s.series_id,s.title,s.description,s.release_year,s.seasons,s.rating,s.special_features,s.totalnumofepisodes,s.completed,l.name,ol.name FROM series AS s INNER JOIN language AS l ON l.language_id=s.language_id LEFT JOIN language AS ol ON ol.language_id=s.original_language_id;";
             
             Statement statCat = conCat.createStatement();
            
             ResultSet rC = statCat.executeQuery(c);
             
             while(rC.next()){
             listC.add(new EST(rC.getInt("s.series_id"),rC.getString("s.title"),rC.getString("s.description"),rC.getInt("s.release_year"),rC.getString("l.name"),rC.getString("ol.name"),rC.getInt("s.seasons"),rC.getString("s.rating"),rC.getString("s.special_features"),rC.getInt("s.totalnumofepisodes"),rC.getString("s.completed")));
             }
             
        }
        catch (Exception e){}
        return listC;
        }
    
    @FXML
    private void UpdateDialog(ActionEvent event) throws IOException {
        if(a==1){
            
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ESeriesUpdatePopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        ESeriesUpdatePopUpController EPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        
        a=0;
    }
        if(ESeriesUpdatePopUpController.supfin==1){
        Parent troot = FXMLLoader.load(getClass().getResource("ESeriesPopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
        }
    }

    @FXML
    private void onClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         a=1;
         
        if (SeriesTable.getSelectionModel().getSelectedItem() != null) {
        EST s = SeriesTable.getSelectionModel().getSelectedItem();
        sidForPop = s.getSeriesid();   
    }   
        }
    }

    
}
