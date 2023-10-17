 package TVDemand;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nonths
 */
public class CatalogueCController implements Initializable {

    @FXML
    private Button MyAccountButton;
    @FXML
    private Button MyRentalsButton;
    @FXML
    private Button CatalogueButton;
    @FXML
    private Button MyCartButton;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane HomeScene;
    @FXML
    private Button Xbutton;
    @FXML
    private Label NameLabel;
    @FXML
    private ImageView HomeButton;
    @FXML
    private ImageView XButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button popUpButton;
    @FXML
    private TableView<customerCatalogue> CatalogueTable;
    @FXML
    private TableColumn<customerCatalogue, Integer> inventoryID;
    @FXML
    private TableColumn<customerCatalogue, String> title;
    @FXML
    private TableColumn<customerCatalogue, String> type;
    
    public static ArrayList<MyCartList> cart = new ArrayList<MyCartList>();
    
    public static int idForPop;
            
    int a = 0;
    String t,fors;
    
    ObservableList<customerCatalogue> cCat;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inventoryID.setCellValueFactory(new PropertyValueFactory<customerCatalogue,Integer>("inventoryID"));
        title.setCellValueFactory(new PropertyValueFactory<customerCatalogue,String>("title"));
        type.setCellValueFactory(new PropertyValueFactory<customerCatalogue,String>("type"));
        
        
        try {
            cCat = getDataCatalogue();
        } catch (SQLException ex) {
            Logger.getLogger(CatalogueCController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        CatalogueTable.setItems(cCat);
        
        }
    

    @FXML
    private void MyAccountSceneTransitionFromCatalogue(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("MyAccountC.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void MyRentalsSceneTransitionFromCatalogue(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("MyRentalsC.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        (CatalogueCController.cart).clear();
        Parent troot = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void cancelExit(ActionEvent event) {
        Stage stage = (Stage) Xbutton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void homeScene(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("CustomerScene.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }
    
    private ObservableList<customerCatalogue> getDataCatalogue() throws SQLException{
        
        
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<customerCatalogue> listCat = FXCollections.observableArrayList();
        
        String a = "SELECT customer_type FROM customer WHERE email ='" + Scene1Controller.Email + "';";
        Statement statA = conCat.createStatement();
        ResultSet rA = statA.executeQuery(a);
        
        while(rA.next()){
        t= rA.getString("customer_type");
        }
        
        
        
        try{
             if(t.equals("s")){
             String c = "SELECT si.inventory_id,s.title FROM series_inventory AS si INNER JOIN series AS s ON s.series_id=si.series_id;";
             
             Statement statCat = conCat.createStatement();
            
             ResultSet rC = statCat.executeQuery(c);
             
             while(rC.next()){
             listCat.add(new customerCatalogue(rC.getInt("si.inventory_id"),rC.getString("s.title"),"series"));
             }
             }
             
             if(t.equals("m")){
             String c = "SELECT fi.inventory_id,f.title FROM film_inventory AS fi INNER JOIN film AS f ON f.film_id=fi.film_id;";
             
             Statement statCat = conCat.createStatement();
            
             ResultSet rC = statCat.executeQuery(c);
             
             while(rC.next()){
             listCat.add(new customerCatalogue(rC.getInt("fi.inventory_id"),rC.getString("f.title"),"film"));
             }
             }
             
             if(t.equals("ms")){
             String c = "SELECT si.inventory_id,s.title FROM series_inventory AS si INNER JOIN series AS s ON s.series_id=si.series_id;";
             String d = "SELECT fi.inventory_id,f.title FROM film_inventory AS fi INNER JOIN film AS f ON f.film_id=fi.film_id;";
             
             Statement statCatF = conCat.createStatement();
             Statement statCatS = conCat.createStatement();
             
             ResultSet rCF = statCatF.executeQuery(d);
             ResultSet rCS = statCatS.executeQuery(c);
             
             while(rCF.next()){
             listCat.add(new customerCatalogue(rCF.getInt("fi.inventory_id"),rCF.getString("f.title"),"film"));
             
             }
             
             while(rCS.next()){
             listCat.add(new customerCatalogue(rCS.getInt("si.inventory_id"),rCS.getString("s.title"),"series"));
             }
             }


        }
        catch (Exception e){}
        return listCat;
        }

    @FXML
    private void MyCartSceneTransitionFromCatalogue(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("MyCartC.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }



    @FXML
    private void clickOnList(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         a=1;
         
        if (CatalogueTable.getSelectionModel().getSelectedItem() != null) {
        customerCatalogue s = CatalogueTable.getSelectionModel().getSelectedItem();
        fors=s.getType();
        idForPop = s.getInventoryID();   
    }
        }
    }

    @FXML
    private void popDialogButton(ActionEvent event) {
        
        if(a==1){
            if(fors.equals("film")){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("PopUpDialogueForMovies.fxml"));
        DialogPane cSelected;
            try {
                cSelected = fxmlLoader.load();
            
        PopUpDialogueForMoviesController PopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        } catch (IOException ex) {
                Logger.getLogger(CatalogueCController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
            if(fors.equals("series")){
                FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("PopUpDialog.fxml"));
        DialogPane cSelected;
            try {
                cSelected = fxmlLoader.load();
            
        PopUpDialogController PopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        } catch (IOException ex) {
                Logger.getLogger(CatalogueCController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
                a=0;
    }
      
}
