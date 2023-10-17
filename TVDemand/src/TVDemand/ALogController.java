package TVDemand;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ALogController implements Initializable {

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
    private Button deleteButton;
    @FXML
    private Button logButton;
    @FXML
    private TableView<AUT> usernameTable;
    @FXML
    private TableView<AUT> tableTable;
    @FXML
    private TableColumn<AUT, String> tableN;
    @FXML
    private TableColumn<AUT, String> emailC;
    @FXML
    private DatePicker dateP;
    
    public static String date;
    public static String username;
    public static String tablename;
    public static LocalDate datepp;
    public static int us=0,ta=0;

    ObservableList<AUT> user;
    ObservableList<AUT> table;
    @FXML
    private Label noTF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    dateP.getEditor().setDisable(true);
        tableN.setCellValueFactory(new PropertyValueFactory<AUT,String>("name"));
        emailC.setCellValueFactory(new PropertyValueFactory<AUT,String>("name"));
        
        try {       
            user = getUser();

            table = getTable();
        } catch (SQLException ex) {
            Logger.getLogger(ALogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        usernameTable.setItems(user);
        tableTable.setItems(table);
        
    }    
    
    private ObservableList<AUT> getUser() throws SQLException{
        
        
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<AUT> listCat = FXCollections.observableArrayList();
        
        String a = "SELECT email FROM customer;";
        Statement statA = conCat.createStatement();
        ResultSet rA = statA.executeQuery(a);
        String b = "SELECT email FROM employee;";
        Statement statB = conCat.createStatement();
        ResultSet rB = statB.executeQuery(b);
        String c = "SELECT email FROM administrator;";
        Statement statC = conCat.createStatement();
        ResultSet rC = statC.executeQuery(c);
        
        while(rA.next()){
        listCat.add(new AUT(rA.getString("email")));
        }
        while(rB.next()){
        listCat.add(new AUT(rB.getString("email")));
        }
        while(rC.next()){
        listCat.add(new AUT(rC.getString("email")));
        }
        
        return listCat;
        }
    
    private ObservableList<AUT> getTable() throws SQLException{
        
        ObservableList<AUT> listCat = FXCollections.observableArrayList();
        listCat.add(new AUT("actor"));
        listCat.add(new AUT("address"));
        listCat.add(new AUT("administrator"));
        listCat.add(new AUT("category"));
        listCat.add(new AUT("city"));
        listCat.add(new AUT("country"));
        listCat.add(new AUT("customer"));
        listCat.add(new AUT("employee"));
        listCat.add(new AUT("film"));
        listCat.add(new AUT("film actor"));
        listCat.add(new AUT("film category"));
        listCat.add(new AUT("film inventory"));
        listCat.add(new AUT("inventory"));
        listCat.add(new AUT("language"));
        listCat.add(new AUT("payment"));
        listCat.add(new AUT("prices"));
        listCat.add(new AUT("rental"));
        listCat.add(new AUT("seasons"));
        listCat.add(new AUT("series"));
        listCat.add(new AUT("series actor"));
        listCat.add(new AUT("series category"));
        listCat.add(new AUT("series inventory"));
        
        return listCat;
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
    private void daSceneTransition(ActionEvent event) throws IOException{
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
    private void logSceneTransition(ActionEvent event) {
    }

    @FXML
    private void onClickU(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         us=1;
         
        if (usernameTable.getSelectionModel().getSelectedItem() != null) {
       AUT s = usernameTable.getSelectionModel().getSelectedItem();
        username = s.getName();

    }   
        }
    }

    @FXML
    private void OnClickT(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         ta=1;
         
        if (tableTable.getSelectionModel().getSelectedItem() != null) {
       AUT s = tableTable.getSelectionModel().getSelectedItem();
        tablename = s.getName();
    }   
        }
    }

    @FXML
    private void buttonPressed(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ALogPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        ALogPopUpController APopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        dateP.setValue(null);
        
    }

    @FXML
    private void getDate(ActionEvent event) {
        LocalDate datepp = dateP.getValue();
        if(datepp != null){
        date= datepp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        } 
        else{date=null;}
    }
    
    
}
