
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AIncomesController implements Initializable {

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
    private TableView<AIncomeTable> incomeTable;
    @FXML
    private TableColumn<AIncomeTable, String> year;
    @FXML
    private TableColumn<AIncomeTable, String> month;
    @FXML
    private TableColumn<AIncomeTable, Float> tincome;
    @FXML
    private TableColumn<AIncomeTable, String> type;
    @FXML
    private Button Xbutton;
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
    private Button logButton;
    
    ObservableList<AIncomeTable> it;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        year.setCellValueFactory(new PropertyValueFactory<AIncomeTable,String>("year"));
        month.setCellValueFactory(new PropertyValueFactory<AIncomeTable,String>("month"));
        tincome.setCellValueFactory(new PropertyValueFactory<AIncomeTable,Float>("tamount"));
        type.setCellValueFactory(new PropertyValueFactory<AIncomeTable,String>("type"));

        
        try {
            it = getIncomes();
        } catch (SQLException ex) {
            Logger.getLogger(AIncomesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        incomeTable.setItems(it);
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
    private void cancelExit(ActionEvent event) {
        Stage stage = (Stage) Xbutton.getScene().getWindow();
        stage.close();
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
    private void incomeSceneTransition(ActionEvent event) {
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
    private void logSceneTransition(ActionEvent event) throws IOException {
        Parent troot = FXMLLoader.load(getClass().getResource("ALog.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }
    
    private ObservableList<AIncomeTable> getIncomes() throws SQLException{
        
        DBConnection connectNow = new DBConnection();
        Connection conP = connectNow.createConnection();
        
        ObservableList<AIncomeTable> listC = FXCollections.observableArrayList();
        
        try{
             String p ="SELECT SUM(p.amount),MONTHNAME(p.payment_date),YEAR(p.payment_date),r.rental_type FROM payment AS p INNER JOIN rental AS r ON r.rental_id=p.rental_id GROUP BY EXTRACT(YEAR_MONTH FROM p.payment_date),r.rental_type;";
            
             Statement statP = conP.createStatement();
            
             ResultSet rP = statP.executeQuery(p);
             
             while(rP.next()){
                 if(rP.getString("r.rental_type").equals("s")){
             listC.add(new AIncomeTable(rP.getString("YEAR(p.payment_date)"),rP.getString("MONTHNAME(p.payment_date)"),rP.getFloat("SUM(p.amount)"),"Series"));
                 }
                 if(rP.getString("r.rental_type").equals("f")){
             listC.add(new AIncomeTable(rP.getString("YEAR(p.payment_date)"),rP.getString("MONTHNAME(p.payment_date)"),rP.getFloat("SUM(p.amount)"),"Films"));
                 }    
             
        }
        }
        catch (Exception e){}
        
        return listC;
        }
    
}
