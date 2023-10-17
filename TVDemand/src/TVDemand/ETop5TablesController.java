
package TVDemand;

import static TVDemand.EUpdateTablesController.nForPop;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.cell.PropertyValueFactory;


public class ETop5TablesController implements Initializable {

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
    private TableView<EFAST> filmTable;
    @FXML
    private TableColumn<EFAST, Integer> TotalRentalsf;
    @FXML
    private TableColumn<EFAST, Integer> filmID;
    @FXML
    private TableColumn<EFAST, String> filmTitle;
    @FXML
    private TableView<EFAST> seriesTable;
    @FXML
    private TableColumn<EFAST, Integer> TotalRentalss;
    @FXML
    private TableColumn<EFAST, Integer> seriesID;
    @FXML
    private TableColumn<EFAST, String> seriesTitle;
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
    private String datein,dateout;
    ObservableList<EFAST> F;
    ObservableList<EFAST> S;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            TotalRentalsf.setCellValueFactory(new PropertyValueFactory<EFAST,Integer>("totalr"));
            filmID.setCellValueFactory(new PropertyValueFactory<EFAST,Integer>("id"));
            filmTitle.setCellValueFactory(new PropertyValueFactory<EFAST,String>("title"));
            TotalRentalss.setCellValueFactory(new PropertyValueFactory<EFAST,Integer>("totalr"));
            seriesID.setCellValueFactory(new PropertyValueFactory<EFAST,Integer>("id"));
            seriesTitle.setCellValueFactory(new PropertyValueFactory<EFAST,String>("title"));
       
        try {
            F= getFTable();
            S= getSTable();
        } catch (SQLException ex) {
            Logger.getLogger(ETop5TablesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        filmTable.setItems(F);
        seriesTable.setItems(S);
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
    private void UpdateTablesSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("EUpdateTables.fxml"));
        Scene cuScene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cuScene);
        window.show();
    }

    @FXML
    private void Top5SceneTransition(ActionEvent event) {
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
    
    private ObservableList<EFAST> getFTable() throws SQLException{
        
        
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        ObservableList<EFAST> listC = FXCollections.observableArrayList();
        String a="SELECT CURDATE();";
        Statement ba=con.createStatement();
        ResultSet baa=ba.executeQuery(a);
        while(baa.next()){
            datein=baa.getString("CURDATE()");
        }
        String b="SELECT DATE_SUB(CURDATE(),INTERVAL 1 MONTH);";
        Statement bb=con.createStatement();
        ResultSet bbb=bb.executeQuery(b);
        while(bbb.next()){
            dateout=bbb.getString("DATE_SUB(CURDATE(),INTERVAL 1 MONTH)");
        }
         String s="SELECT COUNT(r.inventory_id),f.film_id,f.title FROM rental AS r INNER JOIN inventory AS i ON r.inventory_id=i.inventory_id INNER JOIN film_inventory AS fi ON fi.inventory_id=i.inventory_id INNER JOIN film AS f ON f.film_id=fi.film_id WHERE r.rental_type LIKE 'f' AND DATE(r.rental_date)>='"+dateout+"' AND DATE(r.rental_date)<='"+datein+"' GROUP BY r.inventory_id ORDER BY COUNT(r.inventory_id) DESC,f.film_id ASC LIMIT 0,5;";
        Statement ss=con.createStatement();
        ResultSet rs=ss.executeQuery(s);
        while(rs.next()){
          listC.add(new EFAST(rs.getInt("COUNT(r.inventory_id)"),rs.getInt("f.film_id"),rs.getString("f.title")));
        }

        return listC;
        }
     private ObservableList<EFAST> getSTable() throws SQLException{
        
        
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        ObservableList<EFAST> listC = FXCollections.observableArrayList();
        String a="SELECT CURDATE();";
        Statement ba=con.createStatement();
        ResultSet baa=ba.executeQuery(a);
        while(baa.next()){
            datein=baa.getString("CURDATE()");
        }
        String b="SELECT DATE_SUB(CURDATE(),INTERVAL 1 MONTH);";
        Statement bb=con.createStatement();
        ResultSet bbb=bb.executeQuery(b);
        while(bbb.next()){
            dateout=bbb.getString("DATE_SUB(CURDATE(),INTERVAL 1 MONTH)");
        }
         String s="SELECT COUNT(r.inventory_id),s.series_id,s.title FROM rental AS r INNER JOIN inventory AS i ON r.inventory_id=i.inventory_id INNER JOIN series_inventory AS si ON si.inventory_id=i.inventory_id INNER JOIN series AS s ON s.series_id=si.series_id WHERE r.rental_type LIKE 's' AND DATE(r.rental_date)>='"+dateout+"' AND DATE(r.rental_date)<='"+datein+"' GROUP BY r.inventory_id ORDER BY COUNT(r.inventory_id) DESC,s.series_id ASC LIMIT 0,5;";
        Statement ss=con.createStatement();
        ResultSet rs=ss.executeQuery(s);
        while(rs.next()){
          listC.add(new EFAST(rs.getInt("COUNT(r.inventory_id)"),rs.getInt("s.series_id"),rs.getString("s.title")));
        }

        return listC;
        }
    
}
