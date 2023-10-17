package TVDemand;

import static TVDemand.EActorsPopUpController.aidForPop;
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


public class ACreateAccController implements Initializable {

    @FXML
    private AnchorPane HomeScene;
    @FXML
    private Label NameLabel;
    @FXML
    private ImageView HomeButton;
    @FXML
    private ImageView XButton;
    @FXML
    private Button homeButton;
    @FXML
    private TableView<ACuList> CustomerTable;
    @FXML
    private TableColumn<ACuList, String> fname;
    @FXML
    private TableColumn<ACuList, String> lname;
    @FXML
    private TableColumn<ACuList, String> email;
    @FXML
    private TableColumn<ACuList, String> address;
    @FXML
    private TableColumn<ACuList, String> district;
    @FXML
    private TableColumn<ACuList, String> postalcode;
    @FXML
    private TableColumn<ACuList, String> phone;
    @FXML
    private TableColumn<ACuList, String> city;
    @FXML
    private TableColumn<ACuList, String> country;
    @FXML
    private TableColumn<ACuList, String> type;
    @FXML
    private Button addEmployee;
    @FXML
    private Button Xbutton;
    @FXML
    private Button AddCustomer;
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

    ObservableList<ACuList> ola;
    private String cidtbForPop,ciidtbForPop,couidtbForPop,aidtbForPop,didtbForPop,pcidtbForPop,pidtbForPop,fnidtbForPop,lnidtbForPop,tidtbForPop;
    private int a = 0,alreadyc=0,alreadyci=0,alreadya=0;
    private int coufci,cifora,aforc;
    @FXML
    private Button logButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       fname.setCellValueFactory(new PropertyValueFactory<ACuList,String>("fname"));
       lname.setCellValueFactory(new PropertyValueFactory<ACuList,String>("lname"));
       email.setCellValueFactory(new PropertyValueFactory<ACuList,String>("email"));
       address.setCellValueFactory(new PropertyValueFactory<ACuList,String>("address"));
       district.setCellValueFactory(new PropertyValueFactory<ACuList,String>("district"));
       postalcode.setCellValueFactory(new PropertyValueFactory<ACuList,String>("postalcode"));
       phone.setCellValueFactory(new PropertyValueFactory<ACuList,String>("phone"));
       city.setCellValueFactory(new PropertyValueFactory<ACuList,String>("city"));
       country.setCellValueFactory(new PropertyValueFactory<ACuList,String>("country"));
       type.setCellValueFactory(new PropertyValueFactory<ACuList,String>("type"));
       
        try {
            ola = getInfo();
        } catch (SQLException ex) {
            Logger.getLogger(ACreateAccController.class.getName()).log(Level.SEVERE, null, ex);
        }
        CustomerTable.setItems(ola);
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
    private void clickOnList(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         a=1;
         
        if (CustomerTable.getSelectionModel().getSelectedItem() != null) {
        ACuList s = CustomerTable.getSelectionModel().getSelectedItem();
        cidtbForPop = s.getEmail();
        aidtbForPop = s.getAddress();
        couidtbForPop = s.getCountry();
        ciidtbForPop = s.getCity();
        didtbForPop = s.getDistrict();
        pcidtbForPop = s.getPostalcode();
        pidtbForPop = s.getPhone();
        fnidtbForPop = s.getFname();
        lnidtbForPop=s.getLname();
        tidtbForPop=s.getType();
    }   
        }
    }

    @FXML
    private void AddE(ActionEvent event) throws SQLException, IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("ACreateEmPopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
        UpT();
    }

    @FXML
    private void cancelExit(ActionEvent event) {
        Stage stage = (Stage) Xbutton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void AddC(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        if(a==1){
        String b = "SELECT country,country_id FROM country;";
        Statement bb=con.createStatement();
        ResultSet ba=bb.executeQuery(b);
        while(ba.next())
        {
            if(ba.getString("country").equals(couidtbForPop))
            {
                alreadyc=1;
                coufci=ba.getInt("country_id");
            }
        }
         String c = "SELECT city,country_id,city_id FROM city;";
        Statement cc=con.createStatement();
        ResultSet ca=cc.executeQuery(c);
        while(ca.next())
        {
            if(ca.getString("city").equals(ciidtbForPop) && ca.getInt("country_id")==coufci)
            {
                alreadyci=1;
                cifora=ca.getInt("city_id");
            }
        }
        String d = "SELECT address_id,address,district,postal_code,phone,city_id FROM address;";
        Statement dd=con.createStatement();
        ResultSet da=dd.executeQuery(d);
        while(da.next())
        {
            if(da.getString("address").equals(aidtbForPop) && da.getString("district").equals(didtbForPop) && da.getString("postal_code").equals(pcidtbForPop) && da.getString("phone").equals(pidtbForPop) && da.getInt("city_id")==cifora)
            {
                alreadya=1;
                aforc=da.getInt("address_id");
            }
        }
        if(alreadyc==0){
        String e = "INSERT INTO country VALUES(NULL,'"+couidtbForPop+"');";
        Statement ee=con.createStatement();
        ResultSet ea=ee.executeQuery(e);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','country')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String eee = "SELECT country_id FROM country WHERE country='"+couidtbForPop+"';";
        Statement bee=con.createStatement();
        ResultSet eea=bee.executeQuery(eee);
        while(eea.next())
        {
                coufci=eea.getInt("country_id");
        }
        
        }
        if(alreadyci==0){
        String f = "INSERT INTO city VALUES(NULL,'"+ciidtbForPop+"',"+coufci+");";
        Statement ff=con.createStatement();
        ResultSet fa=ff.executeQuery(f);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','city')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String see = "SELECT city_id FROM city WHERE city='"+ciidtbForPop+"' AND country_id="+coufci+";";
        Statement sbee=con.createStatement();
        ResultSet sea=sbee.executeQuery(see);
        while(sea.next())
        {
                cifora=sea.getInt("city_id");
        }
        
        }
        if(alreadya==0){
        String g = "INSERT INTO address VALUES(NULL,'"+aidtbForPop+"','"+didtbForPop+"',"+cifora+",'"+pcidtbForPop+"','"+pidtbForPop+"');";
        Statement gg=con.createStatement();
        ResultSet ga=gg.executeQuery(g);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','address')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String h = "SELECT address_id FROM address WHERE address='"+aidtbForPop+"' AND district='"+didtbForPop+"' AND postal_code='"+pcidtbForPop+"' AND phone='"+pidtbForPop+"' AND city_id="+cifora+";";
        Statement hh=con.createStatement();
        ResultSet ha=hh.executeQuery(h);
        while(ha.next())
        {
                aforc=ha.getInt("address_id");
        }
        
        }
        String i = "INSERT INTO customer VALUES(NULL,'"+fnidtbForPop+"','"+lnidtbForPop+"','"+cidtbForPop+"',"+aforc+",1,CURDATE(),'"+tidtbForPop+"');";
        Statement ii=con.createStatement();
        ResultSet ia=ii.executeQuery(i);
        String flc="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','customer')";
        Statement statflc = con.createStatement();
        ResultSet rflc = statflc.executeQuery(flc);
        String w = "DELETE FROM toberegistered WHERE username='"+cidtbForPop+"';";
        Statement wi=con.createStatement();
        ResultSet wa=wi.executeQuery(w);
        }
        UpT();
     a=0;        
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
    private void caSceneTransition(ActionEvent event) {
    }

    @FXML
    private void daSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("ADeleteCOE.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
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
        Parent troot = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
        
    }
    
    private ObservableList<ACuList> getInfo() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ACuList> listC = FXCollections.observableArrayList();
                
        try{
             
             String c = "SELECT first_name,last_name,username,address,district,postal_code,phone,city,country,customer_type FROM toberegistered;";
             Statement statCat = conCat.createStatement();
             ResultSet rC = statCat.executeQuery(c);
             
             while(rC.next()){
             listC.add(new ACuList(rC.getString("first_name"),rC.getString("last_name"),rC.getString("username"),rC.getString("address"),rC.getString("district"),rC.getString("postal_code"),rC.getString("phone"),rC.getString("city"),rC.getString("country"),rC.getString("customer_type")));
             }
             

        }
        catch (Exception e){}
        return listC;
        }
    
    private void UpT() throws SQLException{
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ACuList> listC = FXCollections.observableArrayList();

             String c = "SELECT first_name,last_name,username,address,district,postal_code,phone,city,country,customer_type FROM toberegistered;";
             Statement statCat = conCat.createStatement();
             ResultSet rC = statCat.executeQuery(c);
             
             while(rC.next()){
             listC.add(new ACuList(rC.getString("first_name"),rC.getString("last_name"),rC.getString("username"),rC.getString("address"),rC.getString("district"),rC.getString("postal_code"),rC.getString("phone"),rC.getString("city"),rC.getString("country"),rC.getString("customer_type")));
             }
       fname.setCellValueFactory(new PropertyValueFactory<ACuList,String>("fname"));
       lname.setCellValueFactory(new PropertyValueFactory<ACuList,String>("lname"));
       email.setCellValueFactory(new PropertyValueFactory<ACuList,String>("email"));
       address.setCellValueFactory(new PropertyValueFactory<ACuList,String>("address"));
       district.setCellValueFactory(new PropertyValueFactory<ACuList,String>("district"));
       postalcode.setCellValueFactory(new PropertyValueFactory<ACuList,String>("postalcode"));
       phone.setCellValueFactory(new PropertyValueFactory<ACuList,String>("phone"));
       city.setCellValueFactory(new PropertyValueFactory<ACuList,String>("city"));
       country.setCellValueFactory(new PropertyValueFactory<ACuList,String>("country"));
       type.setCellValueFactory(new PropertyValueFactory<ACuList,String>("type"));

        
        CustomerTable.setItems(listC);
   
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
