
package TVDemand;

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
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



public class PopUpDialogController implements Initializable {

    @FXML
    private TextField inventoryIDTF;
    @FXML
    private TextField titleTF;
    @FXML
    private TextField descriptionTF;
    @FXML
    private TextField releaseyearTF;
    @FXML
    private TextField languageTF;
    @FXML
    private TextField oglanguageTF;
    @FXML
    private TextField noofseasonsTF;
    @FXML
    private TextField ratingTF;
    @FXML
    private TextField specialfeaturesTF;
    @FXML
    private TextField completedTF;
    @FXML
    private TextField totalnoofepisodesTF;
    @FXML
    private TableView<SeriesCatalogueList> SeasonTable;
    @FXML
    private TableColumn<SeriesCatalogueList, Integer> seasonno;
    @FXML
    private TableColumn<SeriesCatalogueList, Integer> releaseyear;
    @FXML
    private TableColumn<SeriesCatalogueList, Integer> noofepisodes;
    @FXML
    private Button addButton;
    
    ObservableList<SeriesCatalogueList> clist;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inventoryIDTF.setEditable(false);
        titleTF.setEditable(false);
        releaseyearTF.setEditable(false);
        languageTF.setEditable(false);
        oglanguageTF.setEditable(false);
        noofseasonsTF.setEditable(false);
        ratingTF.setEditable(false);
        specialfeaturesTF.setEditable(false);
        descriptionTF.setEditable(false);
        totalnoofepisodesTF.setEditable(false);
        completedTF.setEditable(false);
        seasonno.setCellValueFactory(new PropertyValueFactory<SeriesCatalogueList,Integer>("noseason"));
        releaseyear.setCellValueFactory(new PropertyValueFactory<SeriesCatalogueList,Integer>("releaseyear"));
        noofepisodes.setCellValueFactory(new PropertyValueFactory<SeriesCatalogueList,Integer>("noofepisodes"));
        
        try {
            seriesDialog();
        } catch (SQLException ex) {
            Logger.getLogger(PopUpDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            clist = seriesSeasonT();
        } catch (SQLException ex) {
            Logger.getLogger(PopUpDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SeasonTable.setItems(clist);
    }    

    @FXML
    private void addDialog(ActionEvent event) {
        MyCartList cartc=new MyCartList(CatalogueCController.idForPop,titleTF.getText(),"series");
        (CatalogueCController.cart).add(cartc);
                
                
    }
    
    private void seriesDialog() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conc = connectNow.createConnection();
        
        String a = "SELECT s.title,s.description,s.release_year,l.name,ol.name,s.seasons,s.rating,s.special_features,s.completed,s.totalnumofepisodes FROM inventory AS i INNER JOIN series_inventory AS si ON si.inventory_id=i.inventory_id INNER JOIN series AS s ON s.series_id=si.series_id INNER JOIN language AS l ON s.language_id=l.language_id INNER JOIN language AS ol ON s.original_language_id=ol.language_id WHERE i.inventory_id="+CatalogueCController.idForPop+ ";";
        
        Statement statCat = conc.createStatement();
            
        ResultSet r = statCat.executeQuery(a); 
        
        while(r.next()){
        inventoryIDTF.setText(String.valueOf(CatalogueCController.idForPop)); 
        titleTF.setText(r.getString("s.title")); 
        descriptionTF.setText(r.getString("s.description")); 
        releaseyearTF.setText(String.valueOf(r.getInt("s.release_year"))); 
        languageTF.setText(r.getString("l.name")); 
        oglanguageTF.setText(r.getString("ol.name")); 
        noofseasonsTF.setText(String.valueOf(r.getInt("s.seasons")));
        ratingTF.setText(r.getString("s.rating"));
        specialfeaturesTF.setText(r.getString("s.special_features"));
        completedTF.setText(r.getString("s.completed"));
        totalnoofepisodesTF.setText(String.valueOf(r.getInt("s.totalnumofepisodes")));
        }
    }
        private ObservableList<SeriesCatalogueList> seriesSeasonT() throws SQLException{
        
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<SeriesCatalogueList> list = FXCollections.observableArrayList();
        
        String a = "SELECT se.season_number,se.release_year,se.number_of_episodes FROM inventory AS i INNER JOIN series_inventory AS si ON si.inventory_id=i.inventory_id INNER JOIN series AS s ON s.series_id=si.series_id INNER JOIN seasons AS se ON se.series_id=s.series_id WHERE i.inventory_id="+CatalogueCController.idForPop+";";
        Statement stat = conCat.createStatement();
        ResultSet r = stat.executeQuery(a);
        
        while(r.next()){
        list.add(new SeriesCatalogueList(r.getInt("se.season_number"),r.getInt("se.release_year"),r.getInt("number_of_episodes")));
        }
        return list;
        }

         


    
    
}
