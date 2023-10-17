/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package TVDemand;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nonths
 */
public class ALogPopUpController implements Initializable {

    @FXML
    private TableView<ALT> LogTable;
    @FXML
    private TableColumn<ALT, Integer> LogIDColumn;
    @FXML
    private TableColumn<ALT, String> UsernameColumn;
    @FXML
    private TableColumn<ALT, String> DateColumn;
    @FXML
    private TableColumn<ALT, String> StateColumn;
    @FXML
    private TableColumn<ALT, String> TypeColumn;
    @FXML
    private TableColumn<ALT, String> TableNameColumn;
    @FXML
    private Button cancelButton;

    ObservableList<ALT> ao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LogIDColumn.setCellValueFactory(new PropertyValueFactory<ALT,Integer>("lid"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<ALT,String>("use"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<ALT,String>("date"));
        StateColumn.setCellValueFactory(new PropertyValueFactory<ALT,String>("state"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<ALT,String>("type"));
        TableNameColumn.setCellValueFactory(new PropertyValueFactory<ALT,String>("table"));
        
        try {
            ao = getLTable();
        } catch (SQLException ex) {
            Logger.getLogger(ALogPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        LogTable.setItems(ao);
    }    

    private ObservableList<ALT> getLTable() throws SQLException{
     DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ALT> listC = FXCollections.observableArrayList();
                
        if(ALogController.us == 1 && ALogController.ta == 0){
            if(ALogController.date != null){
            String s = "SELECT log_id,username,date,state,type,table_name FROM log WHERE username='"+ALogController.username+"' AND DATE(date)='"+ALogController.date+"';";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ALT(rs.getInt("log_id"),rs.getString("username"),rs.getString("date"),rs.getString("state"),rs.getString("type"),rs.getString("table_name")));
        }}
        
        else{
            String s = "SELECT log_id,username,date,state,type,table_name FROM log WHERE username='"+ALogController.username+"';";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ALT(rs.getInt("log_id"),rs.getString("username"),rs.getString("date"),rs.getString("state"),rs.getString("type"),rs.getString("table_name")));
        }}
        }
        
        if(ALogController.us == 0 && ALogController.ta == 1){
            if(ALogController.date != null){
            String s = "SELECT log_id,username,date,state,type,table_name FROM log WHERE table_name='"+ALogController.tablename+"' AND DATE(date)='"+ALogController.date+"';";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ALT(rs.getInt("log_id"),rs.getString("username"),rs.getString("date"),rs.getString("state"),rs.getString("type"),rs.getString("table_name")));
        }}
        
        else{
            String s = "SELECT log_id,username,date,state,type,table_name FROM log WHERE table_name='"+ALogController.tablename+"';";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ALT(rs.getInt("log_id"),rs.getString("username"),rs.getString("date"),rs.getString("state"),rs.getString("type"),rs.getString("table_name")));
        }}
        }
        
        if(ALogController.us == 1 && ALogController.ta == 1){
            if(ALogController.date != null){
            String s = "SELECT log_id,username,date,state,type,table_name FROM log WHERE table_name='"+ALogController.tablename+"' AND DATE(date)='"+ALogController.date+"' AND username='"+ALogController.username+"';";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ALT(rs.getInt("log_id"),rs.getString("username"),rs.getString("date"),rs.getString("state"),rs.getString("type"),rs.getString("table_name")));
        }}
        
        else{
            String s = "SELECT log_id,username,date,state,type,table_name FROM log WHERE table_name='"+ALogController.tablename+"' AND username='"+ALogController.username+"';";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ALT(rs.getInt("log_id"),rs.getString("username"),rs.getString("date"),rs.getString("state"),rs.getString("type"),rs.getString("table_name")));
        }}
        }
        
        if(ALogController.us == 0 && ALogController.ta == 0){
            if(ALogController.date != null){
            String s = "SELECT log_id,username,date,state,type,table_name FROM log WHERE DATE(date)='"+ALogController.date+"';";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ALT(rs.getInt("log_id"),rs.getString("username"),rs.getString("date"),rs.getString("state"),rs.getString("type"),rs.getString("table_name")));
        }}
        
        else{
            String s = "SELECT log_id,username,date,state,type,table_name FROM log;";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ALT(rs.getInt("log_id"),rs.getString("username"),rs.getString("date"),rs.getString("state"),rs.getString("type"),rs.getString("table_name")));
        }}
        }
        
        return listC;   
    }
    
    @FXML
    private void Cancel(ActionEvent event) {
        ALogController.us=0;
        ALogController.ta=0;
        ALogController.date=null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
}
