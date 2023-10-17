
package TVDemand;

import static TVDemand.EFilmInventoryAddPopUpController.didForPop;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ESeriesInventoryPopUpController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;
    @FXML
    private TableView<EST> SeriesTable;
    @FXML
    private TableColumn<EST, Integer> sid;
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
    private TableColumn<EST, Integer> nos;
    @FXML
    private TableColumn<EST, String> ra;
    @FXML
    private TableColumn<EST, String> sf;
    @FXML
    private TableColumn<EST, Integer> noe;
    @FXML
    private TableColumn<EST, String> co;
    ObservableList<EST> eS;
    private int a=0;
    public static int  sisForPop;
    private int givesid;
    private int already=0;


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
            Logger.getLogger(ESeriesInventoryPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
       SeriesTable.setItems(eS);
    }    

    @FXML
    private void Cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void AddSeries(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        
        if(a==1){
        String a = "INSERT INTO inventory VALUES(NULL);";
        Statement statCat = conCat.createStatement();
        ResultSet rC = statCat.executeQuery(a);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','inventory')";
        Statement statfl = conCat.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        
        String b = "SELECT inventory_id FROM inventory WHERE inventory_id>=all(SELECT inventory_id FROM inventory);";
        Statement statCot = conCat.createStatement();
        ResultSet rCot = statCot.executeQuery(b);
        
        while(rCot.next()){  
        givesid=rCot.getInt("inventory_id");
        }
        String d = "SELECT series_id FROM series_inventory;";
        Statement statCotd = conCat.createStatement();
        ResultSet rCotd = statCotd.executeQuery(d);
        while(rCotd.next())
        {
            if(rCotd.getInt("series_id")==sisForPop){
                already=1;
            }
        }
        if(already==0){
        String c = "INSERT INTO series_inventory VALUES("+givesid+","+sisForPop+");";
        Statement aaa = conCat.createStatement();
        ResultSet raa = aaa.executeQuery(c);
                String fls="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','series inventory')";
        Statement statfls = conCat.createStatement();
        ResultSet rfls = statfls.executeQuery(fls);
        }
        }
        a=0;
        already=0;
    }

    @FXML
    private void onClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         a=1;
         
        if (SeriesTable.getSelectionModel().getSelectedItem() != null) {
        EST s = SeriesTable.getSelectionModel().getSelectedItem();
        sisForPop = s.getSeriesid();   
    }   
        }
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
    
}
