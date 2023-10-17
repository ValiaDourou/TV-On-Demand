package TVDemand;

import static TVDemand.ECustomerAccountController.cidForPop;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class EUpdateTablesController implements Initializable {

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
    private TableView<ETableName> UpdateTable;
    @FXML
    private TableColumn<ETableName, String> AvailableTables;
    @FXML
    private Button popUpButton;
    @FXML
    private Button CustomerAccountButton;
    @FXML
    private Button CustomerRentalsButton;
    @FXML
    private Button UpdateTablesButton;
    @FXML
    private Button Top5Button;
    @FXML
    private Button logoutButton1;
    
    private int a=0;
    public static String nForPop;
     ObservableList<ETableName> eT;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AvailableTables.setCellValueFactory(new PropertyValueFactory<ETableName,String>("name"));
        try {
            eT = getTable();
        } catch (SQLException ex) {
            Logger.getLogger(EUpdateTablesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        UpdateTable.setItems(eT);
    }    

    @FXML
    private void cancelExit(ActionEvent event) {
        Stage stage = (Stage) Xbutton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void homeScene(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("EmployeeScene.fxml"));
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
    private void clickOnList(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         a=1;
         
        if (UpdateTable.getSelectionModel().getSelectedItem() != null) {
        ETableName s = UpdateTable.getSelectionModel().getSelectedItem();
        nForPop = s.getName();   
    }   
        }
    }

    @FXML
    private void EpopDialogButton(ActionEvent event) throws IOException {
    if(a==1){
         if(nForPop.equals("Actors")){   
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EActorsPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        EActorsPopUpController EPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
       
        a=0;}
         if(nForPop.equals("Films")){   
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EFilmsPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        EFilmsPopUpController EPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
       
        a=0;}
         if(nForPop.equals("Series")){   
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ESeriesPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        ESeriesPopUpController EPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
       
        a=0;}
         
        if(nForPop.equals("Languages")){   
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ELanguagesPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        ELanguagesPopUpController ElPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
       
        a=0;}
        
        if(nForPop.equals("Categories")){   
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ECategoriesPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        ECategoriesPopUpController EcPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
       
        a=0;}
        
        if(nForPop.equals("Countries")){   
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ECountryPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        ECountryPopUpController EcouPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
       
        a=0;}
        
        if(nForPop.equals("Cities")){   
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ECityPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        ECityPopUpController EciPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
       
        a=0;}
        
        if(nForPop.equals("Addresses")){   
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EAddressesPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        EAddressesPopUpController EaddPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
       
        a=0;}
        if(nForPop.equals("Film Inventory")){   
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EFilmInventoryPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        EFilmInventoryPopUpController EfilminvPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
       
        a=0;}
        
        if(nForPop.equals("Series Inventory")){   
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EMovieInventoryPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        EMovieInventoryPopUpController EfilminvPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
       
        a=0;}
    }
    }
    
    private ObservableList<ETableName> getTable() throws SQLException{
        
        
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ETableName> listC = FXCollections.observableArrayList();
                
        try{
            listC.add(new ETableName("Actors"));
            listC.add(new ETableName("Films"));
            listC.add(new ETableName("Series"));
            listC.add(new ETableName("Languages"));
            listC.add(new ETableName("Categories")); 
            listC.add(new ETableName("Addresses")); 
            listC.add(new ETableName("Cities")); 
            listC.add(new ETableName("Countries"));
            listC.add(new ETableName("Series Inventory"));
            listC.add(new ETableName("Film Inventory"));
        }
        catch (Exception e){}
        return listC;
        }

    @FXML
    private void CustomerAccountSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("ECustomerAccount.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void CustomerRentalSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("ECustomerRentals.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void UpdateTablesSceneTransition(ActionEvent event) {
    }

    @FXML
    private void Top5SceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("ETop5Tables.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    
    
}
