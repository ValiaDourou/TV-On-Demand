
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


public class AChangeModesController implements Initializable {

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
    private TableView<AManager> adminTable;
    @FXML
    private TableColumn<AManager, Integer> adminID;
    @FXML
    private TableColumn<AManager, String> fna;
    @FXML
    private TableColumn<AManager, String> lna;
    @FXML
    private TableColumn<AManager, String> emailA;
    @FXML
    private TableColumn<AManager, Integer> adda;
    @FXML
    private TableColumn<AManager, String> admindate;
    @FXML
    private Button changeButton;
    @FXML
    private TableView<AManager> customerTable1;
    @FXML
    private TableColumn<AManager, Integer> employeeID;
    @FXML
    private TableColumn<AManager, String> fne;
    @FXML
    private TableColumn<AManager, String> lne;
    @FXML
    private TableColumn<AManager, String> emailE;
    @FXML
    private TableColumn<AManager, Integer> adde;
    @FXML
    private TableColumn<AManager, String> employeed;
    
    ObservableList<AManager> admin;
    ObservableList<AManager> employee;
    private int ab=0,eb=0;
    private int admintoemployee,employeetoadmin;
    @FXML
    private Button logButton;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       adminID.setCellValueFactory(new PropertyValueFactory<AManager, Integer>("id"));
       fna.setCellValueFactory(new PropertyValueFactory<AManager, String>("fname"));
       lna.setCellValueFactory(new PropertyValueFactory<AManager, String>("lname"));
       emailA.setCellValueFactory(new PropertyValueFactory<AManager, String>("email"));
       adda.setCellValueFactory(new PropertyValueFactory<AManager, Integer>("aid"));
       admindate.setCellValueFactory(new PropertyValueFactory<AManager, String>("date"));
       
       employeeID.setCellValueFactory(new PropertyValueFactory<AManager, Integer>("id"));
       fne.setCellValueFactory(new PropertyValueFactory<AManager, String>("fname"));
       lne.setCellValueFactory(new PropertyValueFactory<AManager, String>("lname"));
       emailE.setCellValueFactory(new PropertyValueFactory<AManager, String>("email"));
       adde.setCellValueFactory(new PropertyValueFactory<AManager, Integer>("aid"));
       employeed.setCellValueFactory(new PropertyValueFactory<AManager, String>("date"));
       
       
        try {
            admin = getInfoA();
        
            employee = getInfoE();
        } catch (SQLException ex) {
            Logger.getLogger(AChangeModesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        adminTable.setItems(admin);
        customerTable1.setItems(employee);
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
        Parent troot = FXMLLoader.load(getClass().getResource("ACreateAcc.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void daSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("ADeleteCOE.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void meSceneTransition(ActionEvent event) {
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
    private void clickAdmin(MouseEvent event) {
         if(event.getButton().equals(MouseButton.PRIMARY)){
         ab=1;
         
        if (adminTable.getSelectionModel().getSelectedItem() != null) {
        AManager s = adminTable.getSelectionModel().getSelectedItem();
        admintoemployee=s.getId();
        }
       }
    }

    @FXML
    private void changePosition(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        if(ab==1){
            String a="SELECT first_name,last_name,email,address_id,admin_date FROM administrator WHERE admin_id="+admintoemployee+";";
            Statement stata = conCat.createStatement();
            ResultSet ra = stata.executeQuery(a);
            while(ra.next()){
                String ia="INSERT INTO employee VALUES(NULL,'"+ra.getString("first_name")+"','"+ra.getString("last_name")+"','"+ra.getString("email")+"',"+ra.getInt("address_id")+",'"+ra.getString("admin_date")+"');";
                Statement statia = conCat.createStatement();
                ResultSet rai = statia.executeQuery(ia);
                String da="DELETE FROM administrator WHERE admin_id="+admintoemployee+";";
                Statement statda = conCat.createStatement();
                ResultSet rad = statda.executeQuery(da);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','employee')";
                Statement statfl = conCat.createStatement();
                ResultSet rfl = statfl.executeQuery(fl);
            }
            ab=0;
        }
        if(eb==1)
        {
             String a="SELECT first_name,last_name,email,address_id,employment_date FROM employee WHERE employee_id="+employeetoadmin+";";
            Statement stata = conCat.createStatement();
            ResultSet ra = stata.executeQuery(a);
            while(ra.next()){
                String ia="INSERT INTO administrator VALUES(NULL,'"+ra.getString("first_name")+"','"+ra.getString("last_name")+"','"+ra.getString("email")+"',"+ra.getInt("address_id")+",'"+ra.getString("employment_date")+"');";
                Statement statia = conCat.createStatement();
                ResultSet rai = statia.executeQuery(ia);
                String da="DELETE FROM employee WHERE employee_id="+employeetoadmin+";";
                Statement statda = conCat.createStatement();
                ResultSet rad = statda.executeQuery(da);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','administrator')";
                Statement statfl = conCat.createStatement();
                ResultSet rfl = statfl.executeQuery(fl);
            }
            eb=0;
        }
        update();
    }

    @FXML
    private void clickEmployee(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         eb=1;
         
        if (customerTable1.getSelectionModel().getSelectedItem() != null) {
        AManager s = customerTable1.getSelectionModel().getSelectedItem();
        employeetoadmin=s.getId();
        }
       }
    }
    
    
    private ObservableList<AManager> getInfoA() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<AManager> listC = FXCollections.observableArrayList();
                
        try{
             
             String c = "SELECT admin_id,first_name,last_name,email,address_id,admin_date FROM administrator;";
             Statement statCat = conCat.createStatement();
             ResultSet rC = statCat.executeQuery(c);
             
             while(rC.next()){
             listC.add(new AManager(rC.getInt("admin_id"),rC.getString("first_name"),rC.getString("last_name"),rC.getString("email"),rC.getInt("address_id"),rC.getString("admin_date")));
             }
             

        }
        catch (Exception e){}
        return listC;
        }   
    
    private ObservableList<AManager> getInfoE() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<AManager> listC = FXCollections.observableArrayList();
                
        try{
             
             String c = "SELECT employee_id,first_name,last_name,email,address_id,employment_date FROM employee;";
             Statement statCat = conCat.createStatement();
             ResultSet rC = statCat.executeQuery(c);
             
             while(rC.next()){
             listC.add(new AManager(rC.getInt("employee_id"),rC.getString("first_name"),rC.getString("last_name"),rC.getString("email"),rC.getInt("address_id"),rC.getString("employment_date")));
             }
             

        }
        catch (Exception e){}
        return listC;
        }
    
        private void update() throws SQLException{
         DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        
        ObservableList<AManager> lista = FXCollections.observableArrayList();
        
        try{
             String c = "SELECT admin_id,first_name,last_name,email,address_id,admin_date FROM administrator;";
             Statement statCat = con.createStatement();
             ResultSet rC = statCat.executeQuery(c);
             
             while(rC.next()){
             lista.add(new AManager(rC.getInt("admin_id"),rC.getString("first_name"),rC.getString("last_name"),rC.getString("email"),rC.getInt("address_id"),rC.getString("admin_date")));
             }
             
        }
        catch (Exception e){}
        ObservableList<AManager> liste = FXCollections.observableArrayList();
        
        try{
             String c = "SELECT employee_id,first_name,last_name,email,address_id,employment_date FROM employee;";
             Statement statCat = con.createStatement();
             ResultSet rC = statCat.executeQuery(c);
             
             while(rC.next()){
             liste.add(new AManager(rC.getInt("employee_id"),rC.getString("first_name"),rC.getString("last_name"),rC.getString("email"),rC.getInt("address_id"),rC.getString("employment_date")));
             }
             
        }
        catch (Exception e){}
         adminID.setCellValueFactory(new PropertyValueFactory<AManager, Integer>("id"));
       fna.setCellValueFactory(new PropertyValueFactory<AManager, String>("fname"));
       lna.setCellValueFactory(new PropertyValueFactory<AManager, String>("lname"));
       emailA.setCellValueFactory(new PropertyValueFactory<AManager, String>("email"));
       adda.setCellValueFactory(new PropertyValueFactory<AManager, Integer>("aid"));
       admindate.setCellValueFactory(new PropertyValueFactory<AManager, String>("date"));
       
       employeeID.setCellValueFactory(new PropertyValueFactory<AManager, Integer>("id"));
       fne.setCellValueFactory(new PropertyValueFactory<AManager, String>("fname"));
       lne.setCellValueFactory(new PropertyValueFactory<AManager, String>("lname"));
       emailE.setCellValueFactory(new PropertyValueFactory<AManager, String>("email"));
       adde.setCellValueFactory(new PropertyValueFactory<AManager, Integer>("aid"));
       employeed.setCellValueFactory(new PropertyValueFactory<AManager, String>("date"));
       
        
        adminTable.setItems(lista);
        customerTable1.setItems(liste);
        
        
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
