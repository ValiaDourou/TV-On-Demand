/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package TVDemand;

import static TVDemand.CatalogueCController.idForPop;
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

/**
 * FXML Controller class
 *
 * @author Nonths
 */
public class ECustomerAccountController implements Initializable {

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
    @FXML
    private TableView<ECustomerList> CustomerTable;
    @FXML
    private TableColumn<ECustomerList, Integer> customerID;
    
    public static int cidForPop;
    
    ObservableList<ECustomerList> eC;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerID.setCellValueFactory(new PropertyValueFactory<ECustomerList,Integer>("customerID"));

        try {
            eC = getDataCustomer();
        } catch (SQLException ex) {
            Logger.getLogger(ECustomerAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

        CustomerTable.setItems(eC);
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
         
        if (CustomerTable.getSelectionModel().getSelectedItem() != null) {
        ECustomerList s = CustomerTable.getSelectionModel().getSelectedItem();
        cidForPop = s.getCustomerID();   
    }   
        }
    }

    @FXML
    private void EpopDialogButton(ActionEvent event) {
    if(a==1){
            
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EPopUpCustomerInfo.fxml"));
        DialogPane cSelected;
            try {
                cSelected = fxmlLoader.load();
            
        EPopUpCustomerInfoController EPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        } catch (IOException ex) {
                Logger.getLogger(ECustomerAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
        a=0;
    }
    }
    
    private ObservableList<ECustomerList> getDataCustomer() throws SQLException{
        
        
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ECustomerList> listC = FXCollections.observableArrayList();
                
        try{
             
             String c = "SELECT customer_id FROM customer;";
             
             Statement statCat = conCat.createStatement();
            
             ResultSet rC = statCat.executeQuery(c);
             
             while(rC.next()){
             listC.add(new ECustomerList(rC.getInt("customer_id")));
             }
             

        }
        catch (Exception e){}
        return listC;
        }
    
    @FXML
    private void CustomerAccountSceneTransition(ActionEvent event) {
    }

    @FXML
    private void CustomerRentalSceneTransition(ActionEvent event) throws IOException {
        (CatalogueCController.cart).clear();       
        Parent troot = FXMLLoader.load(getClass().getResource("ECustomerRentals.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void UpdateTablesSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("EUpdateTables.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
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
