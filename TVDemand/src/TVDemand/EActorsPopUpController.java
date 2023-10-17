package TVDemand;

import static TVDemand.ECustomerAccountController.cidForPop;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class EActorsPopUpController implements Initializable {

    @FXML
    private Button updateButton;
    @FXML
    private TableView<EActorInfo> ActorTable;
    @FXML
    private TableColumn<EActorInfo, Integer> ActorIDColumn;
    @FXML
    private TableColumn<EActorInfo, String> FirstNameColumn;
    @FXML
    private TableColumn<EActorInfo, String> LastNameColumn;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;

    private int a = 0;
    public static int aidForPop;
    ObservableList<EActorInfo> eA;
    @FXML
    private Button cancelButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ActorIDColumn.setCellValueFactory(new PropertyValueFactory<EActorInfo,Integer>("actorID"));
       FirstNameColumn.setCellValueFactory(new PropertyValueFactory<EActorInfo,String>("firstname"));
       LastNameColumn.setCellValueFactory(new PropertyValueFactory<EActorInfo,String>("lastname"));
       
        try {
            eA = getInfo();
        } catch (SQLException ex) {
            Logger.getLogger(EActorsPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        ActorTable.setItems(eA);
    }    

    @FXML
    private void UpdateDialog(ActionEvent event) throws IOException {
        if(a==1){
            
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EActorUpdatePopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        EActorUpdatePopUpController EPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        
        a=0;
    }
        if(EActorUpdatePopUpController.fin==1){
        Parent troot = FXMLLoader.load(getClass().getResource("EActorsPopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
        }
    }

    @FXML
    private void deleteActor(ActionEvent event) throws SQLException, IOException {
        if(a==1)
        {
              a=0;
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        String a="DELETE FROM film_actor WHERE actor_id=" + aidForPop + ";"; 
        Statement st=conCat.createStatement();
        ResultSet r=st.executeQuery(a);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','film actor')";
        Statement statfl = conCat.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        String b="DELETE FROM series_actor WHERE actor_id=" + aidForPop + ";"; 
        Statement stb=conCat.createStatement();
        ResultSet rb=stb.executeQuery(b);
        String fls="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','series actor')";
        Statement statfls = conCat.createStatement();
        ResultSet rfls = statfls.executeQuery(fls);
        String c="DELETE FROM actor WHERE actor_id=" + aidForPop + ";"; 
        Statement stc=conCat.createStatement();
        ResultSet rc=stc.executeQuery(c);
        String fla="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','actor')";
        Statement statfla = conCat.createStatement();
        ResultSet rfla = statfla.executeQuery(fla);
         Parent troot = FXMLLoader.load(getClass().getResource("EActorsPopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
        }
          
       
       
    }

    @FXML
    private void AddActor(ActionEvent event) throws IOException {
         
            
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EActorAddPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        EActorAddPopUpController EPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        if(EActorAddPopUpController.finAddA==1){
        Parent troot = FXMLLoader.load(getClass().getResource("EActorsPopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();}
    }
    
    @FXML
    private void clickOnList(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         a=1;
         
        if (ActorTable.getSelectionModel().getSelectedItem() != null) {
        EActorInfo s = ActorTable.getSelectionModel().getSelectedItem();
        aidForPop = s.getActorID();   
    }   
        }
    }

   
    
    private ObservableList<EActorInfo> getInfo() throws SQLException{
        
        
        DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<EActorInfo> listC = FXCollections.observableArrayList();
                
        try{
             
             String c = "SELECT actor_id,first_name,last_name FROM actor;";
             
             Statement statCat = conCat.createStatement();
            
             ResultSet rC = statCat.executeQuery(c);
             
             while(rC.next()){
             listC.add(new EActorInfo(rC.getInt("actor_id"),rC.getString("first_name"),rC.getString("last_name")));
             }
             

        }
        catch (Exception e){}
        return listC;
        }

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
