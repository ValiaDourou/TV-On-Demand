/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package TVDemand;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nonths
 */
public class MyRentalsCController implements Initializable {

    @FXML
    private Button MyAccountButton;
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
    private TableView<CustomerR> RentalTable;
    @FXML
    private TableView<CustomerP> PaymentTable;
    @FXML
    private TableColumn<CustomerR, Integer>  rentalID;
    @FXML
    private TableColumn<CustomerR, String> date;
    @FXML
    private TableColumn<CustomerR, Integer> inventoryID;
    @FXML
    private TableColumn<CustomerR, String> type;
    @FXML
    private TableColumn<CustomerP, Integer> paymentID;
    @FXML
    private TableColumn<CustomerP, Integer> prentalID;
    @FXML
    private TableColumn<CustomerP, Float> amount;
    @FXML
    private TableColumn<CustomerP, String> pDate;
    
    ObservableList<CustomerR> cR;
    ObservableList<CustomerP> cP;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rentalID.setCellValueFactory(new PropertyValueFactory<CustomerR,Integer>("rentalID"));
        date.setCellValueFactory(new PropertyValueFactory<CustomerR,String>("date"));
        inventoryID.setCellValueFactory(new PropertyValueFactory<CustomerR,Integer>("inventoryID"));
        type.setCellValueFactory(new PropertyValueFactory<CustomerR,String>("type"));
        
        paymentID.setCellValueFactory(new PropertyValueFactory<CustomerP,Integer>("paymentID"));
        prentalID.setCellValueFactory(new PropertyValueFactory<CustomerP,Integer>("prentalID"));
        amount.setCellValueFactory(new PropertyValueFactory<CustomerP,Float>("amount"));
        pDate.setCellValueFactory(new PropertyValueFactory<CustomerP,String>("pdate"));
        try {
            cR = getDataR();
            cP = getDataP();
        } catch (SQLException ex) {
            Logger.getLogger(MyRentalsCController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RentalTable.setItems(cR);
        PaymentTable.setItems(cP);
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

    private ObservableList<CustomerR> getDataR() throws SQLException{
        
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        
        ObservableList<CustomerR> list = FXCollections.observableArrayList();
        
        try{
             String r = "SELECT rental.rental_id,rental.rental_date,rental.inventory_id,rental.rental_type FROM rental INNER JOIN customer ON customer.customer_id=rental.customer_id WHERE customer.email='" + Scene1Controller.Email + "';";
            
             Statement statR = con.createStatement();
            
             ResultSet rR = statR.executeQuery(r);
             
             while(rR.next()){
             list.add(new CustomerR(rR.getInt("rental.rental_id"),rR.getString("rental.rental_date"),rR.getInt("rental.inventory_id"),rR.getString("rental.rental_type")));
             }
             
        }
        catch (Exception e){}
        
        return list;
        }   
    
    
    private ObservableList<CustomerP> getDataP() throws SQLException{
        
        DBConnection connectNow = new DBConnection();
        Connection conP = connectNow.createConnection();
        
        ObservableList<CustomerP> listP = FXCollections.observableArrayList();
        
        try{
             String p = "SELECT payment.payment_id,payment.rental_id,payment.amount,payment.payment_date FROM payment INNER JOIN rental ON rental.rental_id=payment.rental_id INNER JOIN customer ON customer.customer_id=rental.customer_id WHERE customer.email='" + Scene1Controller.Email + "';";
            
             Statement statP = conP.createStatement();
            
             ResultSet rP = statP.executeQuery(p);
             
             while(rP.next()){
             listP.add(new CustomerP(rP.getInt("payment.payment_id"),rP.getInt("payment.rental_id"),rP.getFloat("payment.amount"),rP.getString("payment.payment_date")));
             }
             
        }
        catch (Exception e){}
        
        return listP;
        }   

    @FXML
    private void MyAccountSceneTransitionFromMyRentals(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("MyAccountC.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void CatalogueSceneTransitionFromMyRentals(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("CatalogueC.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void MyCartSceneTransitionFromMyRentals(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("MyCartC.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }
}
