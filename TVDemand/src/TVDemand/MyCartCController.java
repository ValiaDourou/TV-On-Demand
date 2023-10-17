
package TVDemand;

import static TVDemand.ALogController.username;
import static TVDemand.CatalogueCController.idForPop;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nonths
 */
public class MyCartCController implements Initializable {

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
    private TableView<MyCartList> CatalogueTable;
    @FXML
    private TableColumn<MyCartList, Integer> inventoryID;
    @FXML
    private TableColumn<MyCartList, String> title;
    @FXML
    private TableColumn<MyCartList, String> type;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    private int custcartid;
    private int idforcPop;
    private String forC,tforp;
    private int a=0;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int i;
        inventoryID.setCellValueFactory(new PropertyValueFactory<MyCartList,Integer>("inventoryID"));
        title.setCellValueFactory(new PropertyValueFactory<MyCartList,String>("title"));
        type.setCellValueFactory(new PropertyValueFactory<MyCartList,String>("type"));
        ObservableList<MyCartList> data = FXCollections.observableArrayList();
        for(i=0;i<(CatalogueCController.cart).size();i++)
        {
        data.add((CatalogueCController.cart).get(i));
        }
        CatalogueTable.setItems(data);
    }    
    
    @FXML
    private void MyAccountSceneTransitionFromMyCart(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("MyAccountC.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
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
        Parent troot = FXMLLoader.load(getClass().getResource("CustomerScene.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void MyRentalsSceneTransitionFromMyCart(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("MyRentalsC.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void CatalogueSceneTransitionFromMyCart(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("CatalogueC.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void confirmCart(ActionEvent event) throws SQLException {
        String aa = null;

        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        String c = "SELECT customer_id FROM customer WHERE email='"+ Scene1Controller.Email+"';";
        Statement sb = conCat.createStatement();
        ResultSet rb = sb.executeQuery(c);
        while(rb.next()){
            custcartid=rb.getInt("customer_id");
        }
        if(a==1){
        if(forC.equals("film")){
            aa = "f";
        }
        
        if(forC.equals("series")){
            aa = "s";
        }
        
        
        String a = "INSERT INTO rental VALUES(NULL,NOW(),"+idforcPop+","+custcartid+",'"+aa+"');";
        Statement statA = conCat.createStatement();
        ResultSet rA = statA.executeQuery(a);

        
        String gr = "SELECT rental_id FROM rental WHERE rental_id>= all(SELECT rental_id FROM rental);";
        Statement sgr = conCat.createStatement();
        ResultSet rG = sgr.executeQuery(gr);
        
        while(rG.next()){
        String p = "INSERT INTO payment VALUES(NULL,"+custcartid+","+rG.getInt("rental_id")+",5,NOW());";
        Statement sp = conCat.createStatement();
        ResultSet rP = sp.executeQuery(p);

        }
        
         Iterator<MyCartList> cart=(CatalogueCController.cart).iterator();
         while(cart.hasNext()){
             if(cart.next().inventoryID==idforcPop){
                 cart.remove();
             }
         }
                
        }
        a=0;
        update();
        
    }

    @FXML
    private void cancelCart(ActionEvent event) {
       (CatalogueCController.cart).clear();
       int i;
        inventoryID.setCellValueFactory(new PropertyValueFactory<MyCartList,Integer>("inventoryID"));
        title.setCellValueFactory(new PropertyValueFactory<MyCartList,String>("title"));
        type.setCellValueFactory(new PropertyValueFactory<MyCartList,String>("type"));
        ObservableList<MyCartList> data = FXCollections.observableArrayList();
        for(i=0;i<(CatalogueCController.cart).size();i++)
        {
        data.add((CatalogueCController.cart).get(i));
        }
        CatalogueTable.setItems(data);
    }

    @FXML
    private void onClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         a=1;
         
        if (CatalogueTable.getSelectionModel().getSelectedItem() != null) {
        MyCartList s = CatalogueTable.getSelectionModel().getSelectedItem();

        forC=s.getType();
        idforcPop = s.getInventoryID();   
        tforp=s.getTitle();
    }
        }
    }
    private void update(){
        int i;
        inventoryID.setCellValueFactory(new PropertyValueFactory<MyCartList,Integer>("inventoryID"));
        title.setCellValueFactory(new PropertyValueFactory<MyCartList,String>("title"));
        type.setCellValueFactory(new PropertyValueFactory<MyCartList,String>("type"));
        ObservableList<MyCartList> data = FXCollections.observableArrayList();
        for(i=0;i<(CatalogueCController.cart).size();i++)
        {
        data.add((CatalogueCController.cart).get(i));
        }
        CatalogueTable.setItems(data);
    }
    
}
