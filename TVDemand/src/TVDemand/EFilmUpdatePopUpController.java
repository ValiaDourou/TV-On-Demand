package TVDemand;

import static TVDemand.EActorUpdatePopUpController.fin;
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


public class EFilmUpdatePopUpController implements Initializable {

    @FXML
    private TableView<EC> acTable;
    @FXML
    private TableColumn<EC, String> Categories;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button addElement;
    @FXML
    private TextField tiTF;
    @FXML
    private TextField deTF;
    @FXML
    private TextField ryTF;
    @FXML
    private TextField leTF;
    @FXML
    private TextField raTF;
    @FXML
    private TextField sfTF;
    @FXML
    private Button cancelButton;
    @FXML
    private TableView<ELname> alTable;
    @FXML
    private TableColumn<ELname, String> LTable;
    @FXML
    private TableView<ELname> aolTable;
    @FXML
    private TableColumn<ELname, String> OLTable;
    @FXML
    private Button addL;
    @FXML
    private Button addol;
    @FXML
    private TableView<EC> ccTable;
    @FXML
    private TableColumn<EC, String> Categories1;
    
    @FXML
    private Button deleteElement;
    
    ObservableList<EC> availableCat;
    ObservableList<EC> currentCat;
    ObservableList<ELname> availableL;
    ObservableList<EC> arat;
    ObservableList<ELname> availableOL;
    ObservableList<EC> aspf;
    private int faddl=0,faddol=0,faddc=0,fdell=0,fdelol=0,fdelc=0;
    public static String cforaddf;
    public static String cfordelf;
    public static String lforaddf;
    public static String lfordelf;
    public static String olforaddf;
    public static String olfordelf;
    @FXML
    private TextField lTF;
    @FXML
    private TextField olTF;
    @FXML
    private Button upSF;
    @FXML
    private TableView<EC> alTable1;
    @FXML
    private TableView<EC> alTable2;
    @FXML
    private TableColumn<EC, String> LTable2;
    @FXML
    private TableColumn<EC, String> LTable1;
    @FXML
    private Button updateRatings;
    public static int fupfin=0;
    private int alreadyinit=0;
    private int idofcatf;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lTF.setEditable(false);
        olTF.setEditable(false);
        raTF.setEditable(false);
        sfTF.setEditable(false);
        
        LTable.setCellValueFactory(new PropertyValueFactory<ELname,String>("name"));
        OLTable.setCellValueFactory(new PropertyValueFactory<ELname,String>("name"));
        LTable1.setCellValueFactory(new PropertyValueFactory<EC,String>("title"));
        LTable2.setCellValueFactory(new PropertyValueFactory<EC,String>("title"));
        Categories.setCellValueFactory(new PropertyValueFactory<EC,String>("title"));
        Categories1.setCellValueFactory(new PropertyValueFactory<EC,String>("title"));
        
        try {
            showInfo();
            availableCat = getACTable();
            currentCat = getCCTable();
            availableL = getAlTable();
            arat = getCRTable();
            availableOL = getAlTable();
            aspf = getCSFTable();
        } catch (SQLException ex) {
            Logger.getLogger(EFilmUpdatePopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        acTable.setItems(availableCat);
        alTable.setItems(availableL);
        aolTable.setItems(availableOL);
        ccTable.setItems(currentCat);
        alTable1.setItems(arat);
        alTable2.setItems(aspf); 
    }    
    private void showInfo() throws SQLException{
    DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        String a= "SELECT title,description,release_year,length,rating,special_features,language_id,original_language_id FROM film WHERE film_id="+EFilmsPopUpController.fidForPop+";";
        
        Statement stat = con.createStatement();
        ResultSet ra=stat.executeQuery(a);
        
        while(ra.next()){
        String b="SELECT name FROM language WHERE language_id="+ra.getInt("language_id")+";";
        String c="SELECT name FROM language WHERE language_id="+ra.getInt("original_language_id")+";";
        Statement statb = con.createStatement();
        ResultSet rb=statb.executeQuery(b);
        Statement statc = con.createStatement();
        ResultSet rc=statc.executeQuery(c);
            
        tiTF.setText(ra.getString("title"));
        deTF.setText(ra.getString("description"));
        ryTF.setText((ra.getString("release_year")).substring(0,4));
        leTF.setText(String.valueOf(ra.getString("length")));
        raTF.setText(ra.getString("rating"));
        sfTF.setText(ra.getString("special_features"));
        while(rb.next()){
        lTF.setText(rb.getString("name"));}
        while(rc.next()){
        olTF.setText(rc.getString("name"));}
        }
        
    } 

    @FXML
    private void onClickAC(MouseEvent event) {
         if(event.getButton().equals(MouseButton.PRIMARY)){
         faddc=1;
         
        if (acTable.getSelectionModel().getSelectedItem() != null) {
        EC s = acTable.getSelectionModel().getSelectedItem();
        cforaddf = s.getTitle();
        }   
        }
    }

    @FXML
    private void UpdateFilm(ActionEvent event) throws SQLException {
         DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        String t = tiTF.getText();
        String d = deTF.getText();
        String r = String.valueOf(ryTF.getText());
        String l = String.valueOf(leTF.getText());
        
        
        String a= "UPDATE film SET title='"+t+"',description='"+d+"',release_year="+r+",length="+l+" WHERE film_id="+EFilmsPopUpController.fidForPop+";";
          
        Statement stat = con.createStatement();
        ResultSet ra=stat.executeQuery(a);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','film')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        fupfin=1;
        
    }

    @FXML
    private void addToList(ActionEvent event) throws SQLException, IOException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
      if(faddc==1)
      {
        String f = "SELECT category_id FROM category WHERE name='"+cforaddf +"';";
        Statement stf = con.createStatement();
        ResultSet rf = stf.executeQuery(f);
        while(rf.next()){
            idofcatf=rf.getInt("category_id");
        }
            String ah = "SELECT category_id FROM film_category WHERE film_id="+EFilmsPopUpController.fidForPop +";";
        Statement stah = con.createStatement();
        ResultSet rah = stah.executeQuery(ah);
        while(rah.next()){
        if(rah.getInt("category_id")==idofcatf)
        {
            alreadyinit=1;
        }}
       
        if(alreadyinit==0){
        String deez = "INSERT INTO film_category VALUES("+EFilmsPopUpController.fidForPop+","+idofcatf+");";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','film category')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
    
        }
      alreadyinit=0;
      
      }
      UpdateFilm(event);
     faddc=0;
    Parent troot = FXMLLoader.load(getClass().getResource("EFilmUpdatePopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void Cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void OnClickAL(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         faddl=1;
         
        if (alTable.getSelectionModel().getSelectedItem() != null) {
        ELname s = alTable.getSelectionModel().getSelectedItem();
        lforaddf = s.getName();
        }   
        }
    }

    @FXML
    private void OnClickAOL(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         faddol=1;
         
        if (aolTable.getSelectionModel().getSelectedItem() != null) {
        ELname s = aolTable.getSelectionModel().getSelectedItem();
        olforaddf = s.getName();
        }   
        }
    }

    @FXML
    private void AddL(ActionEvent event) throws IOException, SQLException {
         DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
      if(faddl==1)
      {
        String f = "SELECT language_id FROM language WHERE name='"+lforaddf +"';";
        Statement stf = con.createStatement();
        ResultSet rf = stf.executeQuery(f);
        while(rf.next()){
        String deez = "UPDATE film SET language_id="+rf.getInt("language_id")+" WHERE film_id="+EFilmsPopUpController.fidForPop+";";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','film')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
      }
      faddl=0;
      UpdateFilm(event);
    Parent troot = FXMLLoader.load(getClass().getResource("EFilmUpdatePopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
        
    }

    @FXML
    private void AddOL(ActionEvent event) throws SQLException, IOException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
      if(faddol==1)
      {
        String f = "SELECT language_id FROM language WHERE name='"+olforaddf +"';";
        Statement stf = con.createStatement();
        ResultSet rf = stf.executeQuery(f);
        while(rf.next()){
        String deez = "UPDATE film SET original_language_id="+rf.getInt("language_id")+" WHERE film_id="+EFilmsPopUpController.fidForPop+";";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','film')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
      }
      faddol=0;
      UpdateFilm(event);
    Parent troot = FXMLLoader.load(getClass().getResource("EFilmUpdatePopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void onClickCC(MouseEvent event) {
         if(event.getButton().equals(MouseButton.PRIMARY)){
         fdelc=1;
         
        if (ccTable.getSelectionModel().getSelectedItem() != null) {
        EC s = ccTable.getSelectionModel().getSelectedItem();
        cfordelf = s.getTitle();
        }   
        }
    }


    @FXML
    private void DeleteFromList(ActionEvent event) throws SQLException, IOException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        if(fdelc==1)
        {
            
           String f = "SELECT category_id FROM category WHERE name='"+cfordelf +"';";
        Statement stf = con.createStatement();
        ResultSet rf = stf.executeQuery(f);
        while(rf.next()){
        String deez = "DELETE FROM film_category WHERE film_id="+EFilmsPopUpController.fidForPop+" AND category_id="+rf.getInt("category_id")+";";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','film category')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }}
        fdelc=0;
        UpdateFilm(event);
        Parent troot = FXMLLoader.load(getClass().getResource("EFilmUpdatePopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    
    private ObservableList<ELname> getAlTable() throws SQLException{
     DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ELname> listC = FXCollections.observableArrayList();
                
        
            String s = "SELECT name FROM language;";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ELname(rs.getString("name")));
        }
        
        return listC;   
    }
    
    private ObservableList<EC> getCSFTable() throws SQLException{
    ObservableList<EC> listC = FXCollections.observableArrayList();
        listC.add(new EC("Trailers"));
            listC.add(new EC("Commentaries"));
            listC.add(new EC("Deleted Scenes"));
            listC.add(new EC("Behind the Scenes"));

        
        return listC;  
    }
    
    private ObservableList<EC> getCRTable() throws SQLException{

        ObservableList<EC> listC = FXCollections.observableArrayList();
        listC.add(new EC("G"));
            listC.add(new EC("PG"));
            listC.add(new EC("PG-13"));
            listC.add(new EC("R"));
       listC.add(new EC("NC-17"));
        
        return listC;   
    }
    
    private ObservableList<EC> getACTable() throws SQLException{
     DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<EC> listC = FXCollections.observableArrayList();
                
        
            String s = "SELECT name FROM category;";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new EC(rs.getString("name")));
        }
        
        return listC;   
    }
    
    private ObservableList<EC> getCCTable() throws SQLException{
     DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<EC> listC = FXCollections.observableArrayList();
                
        
            String s = "SELECT c.name FROM category AS c INNER JOIN film_category AS fc ON fc.category_id=c.category_id INNER JOIN film AS f ON f.film_id=fc.film_id WHERE f.film_id="+EFilmsPopUpController.fidForPop+";";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new EC(rs.getString("c.name")));
        }
        
        return listC;   
    }

    @FXML
    private void UPsf(ActionEvent event) throws SQLException, IOException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
      if(fdelol==1)
      {
        
        String deez = "UPDATE film SET special_features='"+olfordelf+"' WHERE film_id="+EFilmsPopUpController.fidForPop+";";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','film')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        
      }
      fdelol=0;
      UpdateFilm(event);
    Parent troot = FXMLLoader.load(getClass().getResource("EFilmUpdatePopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void upR(ActionEvent event) throws IOException, SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
      if(fdell==1)
      {
        
        String deez = "UPDATE film SET rating='"+lfordelf+"' WHERE film_id="+EFilmsPopUpController.fidForPop+";";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','film')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        
      }
      UpdateFilm(event);
      fdell=0;
    Parent troot = FXMLLoader.load(getClass().getResource("EFilmUpdatePopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    
    }

    @FXML
    private void OnClickR(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         fdell=1;
         
        if (alTable1.getSelectionModel().getSelectedItem() != null) {
        EC s = alTable1.getSelectionModel().getSelectedItem();
        lfordelf = s.getTitle();
        }   
        }
    }

    @FXML
    private void OnClickSF(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         fdelol=1;
         
        if (alTable2.getSelectionModel().getSelectedItem() != null) {
        EC s = alTable2.getSelectionModel().getSelectedItem();
        olfordelf = s.getTitle();
        }   
        }
    }
}
