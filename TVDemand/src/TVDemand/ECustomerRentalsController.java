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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ECustomerRentalsController implements Initializable {

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
    private TableView<ECustomerR> RentalTable;
    @FXML
    private TableColumn<ECustomerR, Integer> customerID;
    @FXML
    private TableColumn<ECustomerR, Integer> rentalID;
    @FXML
    private TableColumn<ECustomerR, String> date;
    @FXML
    private TableColumn<ECustomerR, Integer> inventoryID;
    @FXML
    private TableColumn<ECustomerR, String> type;
    @FXML
    private TableView<ECustomerP> PaymentTable;
    @FXML
    private TableColumn<ECustomerP, Integer> pcustomerID;
    @FXML
    private TableColumn<ECustomerP, Integer> paymentID;
    @FXML
    private TableColumn<ECustomerP, Integer> prentalID;
    @FXML
    private TableColumn<ECustomerP, Float> amount;
    @FXML
    private TableColumn<ECustomerP, String> pDate;
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
    
    ObservableList<ECustomerR> cR;
    ObservableList<ECustomerP> cP;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerID.setCellValueFactory(new PropertyValueFactory<ECustomerR,Integer>("customerID"));
        rentalID.setCellValueFactory(new PropertyValueFactory<ECustomerR,Integer>("rentalID"));
        date.setCellValueFactory(new PropertyValueFactory<ECustomerR,String>("date"));
        inventoryID.setCellValueFactory(new PropertyValueFactory<ECustomerR,Integer>("inventoryID"));
        type.setCellValueFactory(new PropertyValueFactory<ECustomerR,String>("type"));
        
        pcustomerID.setCellValueFactory(new PropertyValueFactory<ECustomerP,Integer>("customerID"));
        paymentID.setCellValueFactory(new PropertyValueFactory<ECustomerP,Integer>("paymentID"));
        prentalID.setCellValueFactory(new PropertyValueFactory<ECustomerP,Integer>("prentalID"));
        amount.setCellValueFactory(new PropertyValueFactory<ECustomerP,Float>("amount"));
        pDate.setCellValueFactory(new PropertyValueFactory<ECustomerP,String>("pdate"));
        
        try {
            cR = getDataR();
            cP = getDataP();
        } catch (SQLException ex) {
            Logger.getLogger(ECustomerRentalsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        RentalTable.setItems(cR);
        PaymentTable.setItems(cP);
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
    private void CustomerAccountSceneTransition(ActionEvent event) throws IOException {
        (CatalogueCController.cart).clear();       
        Parent troot = FXMLLoader.load(getClass().getResource("ECustomerAccount.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    private ObservableList<ECustomerR> getDataR() throws SQLException{
        
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        
        ObservableList<ECustomerR> list = FXCollections.observableArrayList();
        
        try{
             String r = "SELECT customer.customer_id,rental.rental_id,rental.rental_date,rental.inventory_id,rental.rental_type FROM rental INNER JOIN customer ON customer.customer_id=rental.customer_id;";
            
             Statement statR = con.createStatement();
            
             ResultSet rR = statR.executeQuery(r);
             
             while(rR.next()){
             list.add(new ECustomerR(rR.getInt("customer.customer_id"),rR.getInt("rental.rental_id"),rR.getString("rental.rental_date"),rR.getInt("rental.inventory_id"),rR.getString("rental.rental_type")));
             }
             
        }
        catch (Exception e){}
        
        return list;
        }   
    
    
    private ObservableList<ECustomerP> getDataP() throws SQLException{
        
        DBConnection connectNow = new DBConnection();
        Connection conP = connectNow.createConnection();
        
        ObservableList<ECustomerP> listP = FXCollections.observableArrayList();
        
        try{
             String p = "SELECT customer.customer_id,payment.payment_id,payment.rental_id,payment.amount,payment.payment_date FROM payment INNER JOIN rental ON rental.rental_id=payment.rental_id INNER JOIN customer ON customer.customer_id=rental.customer_id;";
            
             Statement statP = conP.createStatement();
            
             ResultSet rP = statP.executeQuery(p);
             
             while(rP.next()){
             listP.add(new ECustomerP(rP.getInt("customer.customer_id"),rP.getInt("payment.payment_id"),rP.getInt("payment.rental_id"),rP.getFloat("payment.amount"),rP.getString("payment.payment_date")));
             }
             
        }
        catch (Exception e){}
        
        return listP;
        }   
    
    
    @FXML
    private void CustomerRentalSceneTransition(ActionEvent event) {
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
