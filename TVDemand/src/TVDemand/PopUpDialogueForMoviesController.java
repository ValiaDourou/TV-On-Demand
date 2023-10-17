package TVDemand;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class PopUpDialogueForMoviesController implements Initializable {

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
    private Button addButton;
    @FXML
    private TextField oglanguageTF;
    @FXML
    private TextField lengthTF;
    @FXML
    private TextField ratingTF;
    @FXML
    private TextField specialfeaturesTF;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            filmDialog();
            inventoryIDTF.setEditable(false);
            titleTF.setEditable(false);
            releaseyearTF.setEditable(false);
            languageTF.setEditable(false);
            oglanguageTF.setEditable(false);
            lengthTF.setEditable(false);
            ratingTF.setEditable(false);
            specialfeaturesTF.setEditable(false);
            descriptionTF.setEditable(false);
        } catch (SQLException ex) {
            Logger.getLogger(PopUpDialogueForMoviesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void addDialog(ActionEvent event) {
        MyCartList cartc=new MyCartList(CatalogueCController.idForPop,titleTF.getText(),"film");
        (CatalogueCController.cart).add(cartc);
    }
    
    private void filmDialog() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conc = connectNow.createConnection();
        
        String a = "SELECT f.title,f.description,f.release_year,l.name,ol.name,f.length,f.rating,f.special_features FROM inventory AS i INNER JOIN film_inventory AS fi ON fi.inventory_id=i.inventory_id INNER JOIN film AS f ON f.film_id=fi.film_id INNER JOIN language AS l ON f.language_id=l.language_id LEFT JOIN language AS ol ON f.original_language_id=ol.language_id WHERE i.inventory_id="+CatalogueCController.idForPop+ ";";
        
        Statement statCat = conc.createStatement();
            
        ResultSet r = statCat.executeQuery(a); 
        
        while(r.next()){
        inventoryIDTF.setText(String.valueOf(CatalogueCController.idForPop)); 
        titleTF.setText(r.getString("f.title")); 
        descriptionTF.setText(r.getString("f.description")); 
        releaseyearTF.setText(String.valueOf(r.getInt("f.release_year"))); 
        languageTF.setText(r.getString("l.name")); 
        oglanguageTF.setText(r.getString("ol.name")); 
        lengthTF.setText(String.valueOf(r.getInt("f.length")));
        ratingTF.setText(r.getString("f.rating"));
        specialfeaturesTF.setText(r.getString("f.special_features"));
        }
    }
}
