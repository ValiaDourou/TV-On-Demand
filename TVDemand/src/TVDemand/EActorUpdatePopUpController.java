
package TVDemand;

import static TVDemand.EActorAddPopUpController.tiForPop;
import static TVDemand.EActorAddPopUpController.tyForPop;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class EActorUpdatePopUpController implements Initializable {

    @FXML
    private TextField firstnameTF;
    @FXML
    private TextField lastnameTF;
    @FXML
    private TableView<EWTPT> Available;
    @FXML
    private TableColumn<EWTPT, String> title;
    @FXML
    private TableColumn<EWTPT, String> type;
    @FXML
    private Button addElement;
    @FXML
    private TableView<EWTPT> WhereTheyPlayed;
    @FXML
    private TableColumn<EWTPT, String> title1;
    @FXML
    private TableColumn<EWTPT, String> type1;
    @FXML
    private Button delete;
    
    private int a=0;
    private int b=0;
    public static int fin=0;
    private String tiForAdd;
    private String tyForAdd;
    private String tiForD;
    private String tyForD;
    @FXML
    private Button updateButton;
    ObservableList<EWTPT> ea;
    ObservableList<EWTPT> es;
    private int alreadyin=0;
    @FXML
    private Button cancelButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showInfo();
        } catch (SQLException ex) {
            Logger.getLogger(EActorUpdatePopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
         title.setCellValueFactory(new PropertyValueFactory<EWTPT,String>("title"));
        type.setCellValueFactory(new PropertyValueFactory<EWTPT,String>("type"));
         title1.setCellValueFactory(new PropertyValueFactory<EWTPT,String>("title"));
        type1.setCellValueFactory(new PropertyValueFactory<EWTPT,String>("type"));
 
        try {
            ea = getETable();
            es = getSTable();
        } catch (SQLException ex) {
            Logger.getLogger(EActorUpdatePopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
     

        Available.setItems(ea);
        WhereTheyPlayed.setItems(es);
    }    

   
    
    
    private void showInfo() throws SQLException{
    DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        String a= "SELECT first_name,last_name FROM actor WHERE actor_id="+EActorsPopUpController.aidForPop+";";
          
        Statement stat = con.createStatement();
        ResultSet ra=stat.executeQuery(a);
        
        while(ra.next()){
        firstnameTF.setText(ra.getString("first_name"));
        lastnameTF.setText(ra.getString("last_name"));
        }
        
    } 

    @FXML
    private void onClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         a=1;
         
        if (Available.getSelectionModel().getSelectedItem() != null) {
        EWTPT s = Available.getSelectionModel().getSelectedItem();
        tiForAdd = s.getTitle();
        tyForAdd = s.getType();
        }   
        }
    }


    @FXML
    private void addToList(ActionEvent event) throws SQLException, IOException {
         DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
      if(a==1)
      {
        if(tyForAdd.equals("film")){
            String f = "SELECT film_id FROM film WHERE title='"+tiForAdd +"';";
        Statement stf = con.createStatement();
        ResultSet rf = stf.executeQuery(f);
        while(rf.next()){
            String search="SELECT film_id FROM film_actor WHERE actor_id="+EActorsPopUpController.aidForPop+";";
            Statement stdearch = con.createStatement();
        ResultSet rdsearch = stdearch.executeQuery(search);
        while(rdsearch.next()){
            if(rdsearch.getInt("film_id")==rf.getInt("film_id")){
                alreadyin=1;
            }
        }
        if(alreadyin==0){
        String deez = "INSERT INTO film_actor VALUES("+EActorsPopUpController.aidForPop+","+rf.getInt("film_id")+");";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','film actor')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        }
        }
        if(tyForAdd.equals("series")){
            String s = "SELECT series_id FROM series WHERE title='"+tiForAdd +"';";
        Statement sts = con.createStatement();
        ResultSet rs = sts.executeQuery(s);
        while(rs.next()){
            String search="SELECT series_id FROM series_actor WHERE actor_id="+EActorsPopUpController.aidForPop+";";
            Statement stdearch = con.createStatement();
        ResultSet rdsearch = stdearch.executeQuery(search);
        while(rdsearch.next()){
            if(rdsearch.getInt("series_id")==rs.getInt("series_id")){
                alreadyin=1;
        }
        }
        if(alreadyin==0){
         String deez = "INSERT INTO series_actor VALUES("+EActorsPopUpController.aidForPop+","+rs.getInt("series_id")+");";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','series actor')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        }
        }
      }
      alreadyin=0;
      a=0;
    Parent troot = FXMLLoader.load(getClass().getResource("EActorUpdatePopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void onClickS(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         b=1;
         
        if (WhereTheyPlayed.getSelectionModel().getSelectedItem() != null) {
        EWTPT s = WhereTheyPlayed.getSelectionModel().getSelectedItem();
        tiForD = s.getTitle();
        tyForD = s.getType();
        }   
        }
    }

    @FXML
    private void DeleteFromList(ActionEvent event) throws SQLException, IOException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        if(b==1)
        {
            if(tyForD.equals("film")){
           String f = "SELECT film_id FROM film WHERE title='"+tiForD +"';";
        Statement stf = con.createStatement();
        ResultSet rf = stf.executeQuery(f);
        while(rf.next()){
        String deez = "DELETE FROM film_actor WHERE actor_id="+EActorsPopUpController.aidForPop+" AND film_id="+rf.getInt("film_id")+";";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','film actor')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        }
        if(tyForD.equals("series")){
            String s = "SELECT series_id FROM series WHERE title='"+tiForD +"';";
        Statement sts = con.createStatement();
        ResultSet rs = sts.executeQuery(s);
        while(rs.next()){
        String deez = "DELETE FROM series_actor WHERE actor_id="+EActorsPopUpController.aidForPop+" AND series_id="+rs.getInt("series_id")+";";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','series actor')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        }
        }
        b=0;
        Parent troot = FXMLLoader.load(getClass().getResource("EActorUpdatePopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {
         DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        String fn = firstnameTF.getText();
        String ln = lastnameTF.getText();
        
        String a= "UPDATE actor SET first_name='"+fn+"',last_name='"+ln+"' WHERE actor_id="+EActorsPopUpController.aidForPop+";";
          
        Statement stat = con.createStatement();
        ResultSet ra=stat.executeQuery(a);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','actor')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        fin=1;
        
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
    private ObservableList<EWTPT> getSTable() throws SQLException{
     DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<EWTPT> listC = FXCollections.observableArrayList();
                
        
            String s = "SELECT series.title FROM series INNER JOIN series_actor ON series_actor.series_id=series.series_id WHERE series_actor.actor_id="+EActorsPopUpController.aidForPop+";";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new EWTPT(rs.getString("title"),"series"));
        }
        
        String f = "SELECT film.title FROM film INNER JOIN film_actor ON film_actor.film_id=film.film_id WHERE film_actor.actor_id="+EActorsPopUpController.aidForPop+";";
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
