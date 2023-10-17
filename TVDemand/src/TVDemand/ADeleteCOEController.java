
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ADeleteCOEController implements Initializable {

    @FXML
    private Button changepriceButton;
    @FXML
    private Button createAccountButton;
    @FXML
    private Button DeleteAccountsButton;
    @FXML
    private Button memployeesButton;
    @FXML
    private Button IncomeButtons;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane HomeScene;
    @FXML
    private Button Xbutton;
    @FXML
    private ImageView HomeButton;
    @FXML
    private ImageView XButton;
    @FXML
    private Button homeButton;
    @FXML
    private TableView<APersonTable> customerTable;
    @FXML
    private TableColumn<APersonTable, Integer> customerID;
    @FXML
    private TableColumn<APersonTable, String> emailC;
    @FXML
    private TableView<APersonTable> employeeTable;
    @FXML
    private TableColumn<APersonTable, Integer> employeeID;
    @FXML
    private TableColumn<APersonTable, String> emailE;
    @FXML
    private Button deleteButton;
    ObservableList<APersonTable> cu;
    ObservableList<APersonTable> em;
    private int cb=0,eb=0;
    private int customertodelete,employeetodelete;
    private int addeddelc=0;
    @FXML
    private Button logButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerID.setCellValueFactory(new PropertyValueFactory<APersonTable,Integer>("id"));
        emailC.setCellValueFactory(new PropertyValueFactory<APersonTable,String>("email"));
        employeeID.setCellValueFactory(new PropertyValueFactory<APersonTable,Integer>("id"));
        emailE.setCellValueFactory(new PropertyValueFactory<APersonTable,String>("email"));

        try {
            cu = getCustomers();
        
            em = getEmployees();
            } catch (SQLException ex) {
            Logger.getLogger(ADeleteCOEController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        customerTable.setItems(cu);
        employeeTable.setItems(em);
    }    

    @FXML
    private void cpSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("AChangePrices.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void caSceneTransition(ActionEvent event) throws IOException {
        (CatalogueCController.cart).clear();       
        Parent troot = FXMLLoader.load(getClass().getResource("ACreateAcc.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void daSceneTransition(ActionEvent event) {
    }

    @FXML
    private void meSceneTransition(ActionEvent event) throws IOException {  
        Parent troot = FXMLLoader.load(getClass().getResource("AChangeModes.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void incomeSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("AIncomes.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
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
        Parent troot = FXMLLoader.load(getClass().getResource("AHomeScene.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void clickCustomer(MouseEvent event) {
       if(event.getButton().equals(MouseButton.PRIMARY)){
         cb=1;
         
        if (customerTable.getSelectionModel().getSelectedItem() != null) {
        APersonTable s = customerTable.getSelectionModel().getSelectedItem();
        customertodelete=s.getId();
        }
       }
    }

    @FXML
    private void clickEmployee(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         eb=1;
         
        if (employeeTable.getSelectionModel().getSelectedItem() != null) {
        APersonTable s = employeeTable.getSelectionModel().getSelectedItem();
        employeetodelete=s.getId();
        }
       }
    }

    @FXML
    private void deleteCOE(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection conP = connectNow.createConnection();
        if(cb==1)
        {
            String d="SELECT customer_id FROM customer WHERE email='Deleted Customer';";
            Statement statd = conP.createStatement();
            
            ResultSet rd = statd.executeQuery(d);
            while(rd.next()){
                if(rd.getInt("customer_id")==customertodelete){
                    addeddelc=1;
                }
            if(addeddelc==0){
                String e="UPDATE payment SET customer_id="+rd.getInt("customer_id")+" WHERE customer_id="+customertodelete+";";
                Statement state = conP.createStatement();
            
            ResultSet re = state.executeQuery(e);
            String r="UPDATE rental SET customer_id="+rd.getInt("customer_id")+" WHERE customer_id="+customertodelete+";";
                Statement statr = conP.createStatement();
            
            ResultSet rr = statr.executeQuery(r);
            String a="DELETE FROM customer WHERE customer_id="+customertodelete+";";
            Statement stata = conP.createStatement();
            
            ResultSet ra = stata.executeQuery(a);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','customer')";
        Statement statfl = conP.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);}}
            addeddelc=0;
            cb=0;
        }
         if(eb==1)
        {
            String e="DELETE FROM employee WHERE employee_id="+employeetodelete+";";
            Statement state = conP.createStatement();
            
            ResultSet re = state.executeQuery(e);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','employee')";
        Statement statfl = conP.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
            eb=0;
        }
         update();
    }
    
     private ObservableList<APersonTable> getCustomers() throws SQLException{
        
        DBConnection connectNow = new DBConnection();
        Connection conP = connectNow.createConnection();
        
        ObservableList<APersonTable> listC = FXCollections.observableArrayList();
        
        try{
             
             String p ="SELECT customer_id,email FROM customer;";
            
             Statement statP = conP.createStatement();
            
             ResultSet rP = statP.executeQuery(p);
             
             while(rP.next()){
             listC.add(new APersonTable(rP.getInt("customer_id"),rP.getString("email")));
             }
             
        }
        catch (Exception e){}
        
        return listC;
        }
     
     private ObservableList<APersonTable> getEmployees() throws SQLException{
        
        DBConnection connectNow = new DBConnection();
        Connection conP = connectNow.createConnection();
        
        ObservableList<APersonTable> listC = FXCollections.observableArrayList();
        
        try{
             String p ="SELECT employee_id,email FROM employee;";
            
             Statement statP = conP.createStatement();
            
             ResultSet rP = statP.executeQuery(p);
             
             while(rP.next()){
             listC.add(new APersonTable(rP.getInt("employee_id"),rP.getString("email")));
             }
             
        }
        catch (Exception e){}
        
        return listC;
        }   
     private void update() throws SQLException{
         DBConnection connectNow = new DBConnection();
        Connection conP = connectNow.createConnection();
        
        ObservableList<APersonTable> listC = FXCollections.observableArrayList();
        
        try{
             String p ="SELECT customer_id,email FROM customer;";
            
             Statement statP = conP.createStatement();
            
             ResultSet rP = statP.executeQuery(p);
             
             while(rP.next()){
             listC.add(new APersonTable(rP.getInt("customer_id"),rP.getString("email")));
             }
             
        }
        catch (Exception e){}
        ObservableList<APersonTable> liste = FXCollections.observableArrayList();
        
        try{
             String e ="SELECT employee_id,email FROM employee;";
            
             Statement state = conP.createStatement();
            
             ResultSet re = state.executeQuery(e);
             
             while(re.next()){
             liste.add(new APersonTable(re.getInt("employee_id"),re.getString("email")));
             }
             
        }
        catch (Exception e){}
        
        customerID.setCellValueFactory(new PropertyValueFactory<APersonTable,Integer>("id"));
        emailC.setCellValueFactory(new PropertyValueFactory<APersonTable,String>("email"));
        employeeID.setCellValueFactory(new PropertyValueFactory<APersonTable,Integer>("id"));
        emailE.setCellValueFactory(new PropertyValueFactory<APersonTable,String>("email"));
        customerTable.setItems(listC);
        employeeTable.setItems(liste);
     }

    @FXML
    private void logSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("ALog.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }
    
}
