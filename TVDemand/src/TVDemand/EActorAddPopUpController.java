
package TVDemand;

import static TVDemand.EUpdateTablesController.nForPop;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;




public class EActorAddPopUpController implements Initializable {

    @FXML
    private TextField firstnameTF;
    @FXML
    private TextField lastnameTF;
    @FXML
    private Button addButton;
    @FXML
    private TableView<EWTPT> WhereTheyPlayed;
    @FXML
    private TableColumn<EWTPT, String> title;
    @FXML
    private TableColumn<EWTPT, String> type;
    @FXML
    private Button addElement;

    private int af=0;
    public static int finAddA=0;
    public static String tiForPop;
    public static String tyForPop;
    private int added=0;
    private int alreadyin=0;
    ObservableList<EWTPT> ee;
    @FXML
    private Button cancelButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        title.setCellValueFactory(new PropertyValueFactory<EWTPT,String>("title"));
        type.setCellValueFactory(new PropertyValueFactory<EWTPT,String>("type"));
        
        
        try {
            ee = getETable();
        } catch (SQLException ex) {
            Logger.getLogger(EActorAddPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        WhereTheyPlayed.setItems(ee);
    }    

    @FXML
    private void AddDialog(ActionEvent event) throws SQLException {
        if(added==0){
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        String fn = firstnameTF.getText();
        String ln = lastnameTF.getText();
        
        String a= "INSERT INTO actor VALUES(NULL,'"+fn+"','"+ln+"');";
          
        Statement stat = con.createStatement();
        ResultSet ra=stat.executeQuery(a);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','actor')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        
         finAddA=1;
    }

    @FXML
    private void onClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         af++;
         
        if (WhereTheyPlayed.getSelectionModel().getSelectedItem() != null) {
        EWTPT s = WhereTheyPlayed.getSelectionModel().getSelectedItem();
        tiForPop = s.getTitle();
        tyForPop = s.getType();
        }   
        }
    }

    @FXML
    private void addToList(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        String fn = firstnameTF.getText();
        String ln = lastnameTF.getText();
        if(af==1){
        String ac= "INSERT INTO actor VALUES(NULL,'"+fn+"','"+ln+"');";
          
        Statement statc = con.createStatement();
        ResultSet rac=statc.executeQuery(ac);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','actor')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        added=1;
        }
        if(af>=1){
        String a = "SELECT actor_id FROM actor WHERE actor_id>= all(SELECT actor_id FROM actor);";
        Statement st = con.createStatement();
        ResultSet ra = st.executeQuery(a);
        
        while(ra.next()){
        if(tyForPop.equals("film")){
            String f = "SELECT film_id FROM film WHERE title='"+tiForPop +"';";
        Statement stf = con.createStatement();
        ResultSet rf = stf.executeQuery(f);
        while(rf.next()){
            String search="SELECT film_id FROM film_actor WHERE actor_id="+ra.getInt("actor_id")+";";
            Statement stdearch = con.createStatement();
        ResultSet rdsearch = stdearch.executeQuery(search);
        while(rdsearch.next()){
            if(rdsearch.getInt("film_id")==rf.getInt("film_id")){
                alreadyin=1;
            }
        }
            if(alreadyin==0){
        String deez = "INSERT INTO film_actor VALUES("+ra.getInt("actor_id")+","+rf.getInt("film_id")+");";
         Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
        String flf="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','film actor')";
        Statement statflf = con.createStatement();
        ResultSet rflf = statflf.executeQuery(flf);
        }
        }
        }
        if(tyForPop.equals("series")){
            String s = "SELECT series_id FROM series WHERE title='"+tiForPop +"';";
        Statement sts = con.createStatement();
        ResultSet rs = sts.executeQuery(s);
        while(rs.next()){
            String search="SELECT series_id FROM series_actor WHERE actor_id="+ra.getInt("actor_id")+";";
            Statement stdearch = con.createStatement();
        ResultSet rdsearch = stdearch.executeQuery(search);
        while(rdsearch.next()){
            if(rdsearch.getInt("series_id")==rs.getInt("series_id")){
                alreadyin=1;
        }
        }
            if(alreadyin==0){
        String deez = "INSERT INTO series_actor VALUES("+ra.getInt("actor_id")+","+rs.getInt("series_id")+");";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
        String fls="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','series actor')";
        Statement statfls = con.createStatement();
        ResultSet rfls = statfls.executeQuery(fls);
        }
        }
        }
        }
        alreadyin=0;
    }
    }
    
    private ObservableList<EWTPT> getETable() throws SQLException{
     DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<EWTPT> listC = FXCollections.observableArrayList();
                
        
            String s = "SELECT title FROM series;";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new EWTPT(rs.getString("title"),"series"));
        }
        
        String f = "SELECT title FROM film;";
            Statement stf = conCat.createStatement();
        ResultSet rf = stf.executeQuery(f);
        
        while(rf.next()){
            listC.add(new EWTPT(rf.getString("title"),"film"));
        }
        
        return listC;   
    }
    @FXML
    private void Cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
