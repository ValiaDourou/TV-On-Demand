package TVDemand;

import static TVDemand.EActorAddPopUpController.finAddA;
import static TVDemand.EActorAddPopUpController.tiForPop;
import static TVDemand.EActorAddPopUpController.tyForPop;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class EFilmAddPopUpController implements Initializable {

    @FXML
    private TableView<EC> CategoriesTable;
    @FXML
    private TableColumn<EC, String> Categories;
    @FXML
    private Button addButton;
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
    private TextField raTF;
    private TextField sfTF;
    @FXML
    private Button cancelButton;

    public static String ncForPop;
     public static String flForPop;
      public static String folForPop;
    private int af=0;
    private int added=0,addlf=0,addolf=0,addedfr=0,addedfsf=0;
    @FXML
    private TableColumn<ELname, String> LTable;
    @FXML
    private TableColumn<ELname, String> OLTable;
    @FXML
    private Button addL;
    @FXML
    private Button addol;
    ObservableList<ELname> el;
    ObservableList<ELname> eol;
    ObservableList<EC> ec;
    @FXML
    private TableView<ELname> lanT;
    @FXML
    private TableView<ELname> olTable;
    public static int finfAddA=0;
    private int al=0;
    private int aol=0;
    @FXML
    private TableView<EC> RC;
    @FXML
    private TableColumn<EC, String> RTable;
    @FXML
    private Button addR;
    @FXML
    private TableView<EC> SFC;
    @FXML
    private TableColumn<EC, String> SFTable;
    @FXML
    private Button addSF;
    
    ObservableList<EC> rc;
    ObservableList<EC> sfc;
    private int selr=0;
    private int selsf=0;
    private String addfr,far;
    private String addfsf,faor;
    private int fal,fol,ffadd;
    private ArrayList<String> help=new ArrayList<String>();
    private int alreadyaddf=0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RTable.setCellValueFactory(new PropertyValueFactory<EC,String>("title"));
        SFTable.setCellValueFactory(new PropertyValueFactory<EC,String>("title"));
        LTable.setCellValueFactory(new PropertyValueFactory<ELname,String>("name"));
        OLTable.setCellValueFactory(new PropertyValueFactory<ELname,String>("name"));
        Categories.setCellValueFactory(new PropertyValueFactory<EC,String>("title"));
        
        try {
            rc = getrTable();
            sfc = getSFTable();
            el = getlTable();
            eol = getlTable();
            ec = getCTable();
        } catch (SQLException ex) {
            Logger.getLogger(EFilmAddPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RC.setItems(rc);
        SFC.setItems(sfc);
        lanT.setItems(el);
        olTable.setItems(eol);
        CategoriesTable.setItems(ec);

    }    

    @FXML
    private void onClick(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         af=1;
         
         
        if (CategoriesTable.getSelectionModel().getSelectedItem() != null) {
        EC s = CategoriesTable.getSelectionModel().getSelectedItem();
        ncForPop = s.getTitle();
        }   
        }
    }

    @FXML
    private void AddDialog(ActionEvent event) throws SQLException {
        
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        String ti = tiTF.getText();
        String de = deTF.getText();
        String ry = ryTF.getText();
        String le = leTF.getText();

        
        if(addlf==0)
        {
            fal=1;
        }
        if(addolf==0)
        {
            fol=1;
        }
        if(addedfr==0)
        {
            far="G";
        }
        
        if(addedfsf==0){
        String ac= "INSERT INTO film VALUES(NULL,'"+ti+"','"+de+"',"+String.valueOf(ry)+","+fal+","+fol+","+String.valueOf(le)+",'"+far+"',NULL);";
        Statement statc = con.createStatement();
        ResultSet rac=statc.executeQuery(ac);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','film')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        else{
        String ac= "INSERT INTO film VALUES(NULL,'"+ti+"','"+de+"',"+String.valueOf(ry)+","+fal+","+fol+","+String.valueOf(le)+",'"+far+"','"+faor+"');";
        Statement statc = con.createStatement();
        ResultSet rac=statc.executeQuery(ac);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','film')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        
         String ff = "SELECT film_id FROM film WHERE film_id>=all(SELECT film_id FROM film);";
        Statement stff = con.createStatement();
        ResultSet rff = stff.executeQuery(ff);
        while(rff.next()){
            ffadd=rff.getInt("film_id");
        }
        for(int i=0;i<help.size();i++){
        String aa = "SELECT category_id FROM category WHERE name='"+help.get(i)+"';";
        Statement staa = con.createStatement();
        ResultSet raa = staa.executeQuery(aa);
        
        while(raa.next()){
       String afa = "SELECT category_id FROM film_category WHERE film_id="+ffadd+";";
        Statement staaf = con.createStatement();
        ResultSet raaf = staaf.executeQuery(afa);
        while(raaf.next()){
            if(raaf.getInt("category_id")==raa.getInt("category_id")){
                alreadyaddf=1;
            }
        }
        if(alreadyaddf==0){
        
        String deez = "INSERT INTO film_category VALUES("+ffadd+","+raa.getInt("category_id")+");";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
        String flc="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','film category')";
        Statement statflc = con.createStatement();
        ResultSet rflc = statflc.executeQuery(flc);
        }
        alreadyaddf=0;}}
        help.clear();
        
        finfAddA=1;
        
    }

    @FXML
    private void addToList(ActionEvent event) throws SQLException {

        
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        String ti = tiTF.getText();
        String de = deTF.getText();
        String ry = ryTF.getText();
        String le = leTF.getText();
       
        if(af==1){
        help.add(ncForPop);

        af=0;
        
    }
    }

    @FXML
    private void Cancel(ActionEvent event) {
        help.clear();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    private ObservableList<EC> getCTable() throws SQLException{
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
    
    private ObservableList<ELname> getlTable() throws SQLException{
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
    private ObservableList<EC> getrTable() throws SQLException{
     ObservableList<EC> listC = FXCollections.observableArrayList();
        listC.add(new EC("G"));
            listC.add(new EC("PG"));
            listC.add(new EC("PG-13"));
            listC.add(new EC("R"));
       listC.add(new EC("NC-17"));
        
        return listC;    
    }
    private ObservableList<EC> getSFTable() throws SQLException{
    ObservableList<EC> listC = FXCollections.observableArrayList();
        listC.add(new EC("Trailers"));
            listC.add(new EC("Commentaries"));
            listC.add(new EC("Deleted Scenes"));
            listC.add(new EC("Behind the Scenes"));

        
        return listC;   
    }



    @FXML
    private void AddL(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
       
        if(al==1){
        String ac= "SELECT language_id FROM language WHERE name='"+flForPop+"';";
        Statement statc = con.createStatement();
        ResultSet rac=statc.executeQuery(ac);
        while(rac.next()){
            fal=rac.getInt("language_id");
        }
        }
        addlf=1;
        
    
    }

    @FXML
    private void AddOL(ActionEvent event) throws SQLException {
       DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
       
        if(aol==1){
        String ac= "SELECT language_id FROM language WHERE name='"+folForPop+"';";
        Statement statc = con.createStatement();
        ResultSet rac=statc.executeQuery(ac);
        while(rac.next()){
            fol=rac.getInt("language_id");
        }
        }
        addolf=1;
        
    }

    @FXML
    private void OnClickL(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         al=1;
         
        if (lanT.getSelectionModel().getSelectedItem() != null) {
        ELname s = lanT.getSelectionModel().getSelectedItem();
        flForPop = s.getName();
        }   
        }
    }

    @FXML
    private void OnClickOL(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         aol=1;
         
        if (olTable.getSelectionModel().getSelectedItem() != null) {
        ELname s = olTable.getSelectionModel().getSelectedItem();
        folForPop = s.getName();
        }   
        }
    }

    @FXML
    private void OnClickR(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         selr=1;
         
        if (RC.getSelectionModel().getSelectedItem() != null) {
        EC s = RC.getSelectionModel().getSelectedItem();
        addfr = s.getTitle();
        }   
        }
        
    }

    @FXML
    private void AddR(ActionEvent event){
        
        if(selr==1){
        far=addfr;
        }
        addedfr=1;
        
    }

    @FXML
    private void OnClickSF(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         selsf=1;
         
        if (SFC.getSelectionModel().getSelectedItem() != null) {
        EC s = SFC.getSelectionModel().getSelectedItem();
        addfsf = s.getTitle();
        }   
        }
    }

    @FXML
    private void AddSF(ActionEvent event){
       
        if(selsf==1){
        faor=addfsf;
        }
        addedfsf=1;
    }
}
