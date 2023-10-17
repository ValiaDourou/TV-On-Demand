
package TVDemand;

import static TVDemand.EFilmAddPopUpController.finfAddA;
import static TVDemand.EFilmAddPopUpController.flForPop;
import static TVDemand.EFilmAddPopUpController.folForPop;
import static TVDemand.EFilmAddPopUpController.ncForPop;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class ESeriesAddPopUpController implements Initializable {

    @FXML
    private TableView<EC> CategoriesTable;
    @FXML
    private TableColumn<EC, String> Categories;
    @FXML
    private Button addSeasons;
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
    private Button cancelButton;
    @FXML
    private TableView<ELname> lanT;
    @FXML
    private TableColumn<ELname, String> LTable;
    @FXML
    private TableView<ELname> olTable;
    @FXML
    private TableColumn<ELname, String> OLTable;
    @FXML
    private Button addL;
    @FXML
    private Button addol;
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
    ObservableList<ELname> el;
    ObservableList<ELname> eol;
    ObservableList<EC> ec;
    private int selr=0;
    private int selsf=0;
    private String addsr,sar;
    private String addssf,saor;
    private int sal,sol,ssadd;
    private ArrayList<String> helps=new ArrayList<String>();
    public static int finsAddA=0;
    private int al=0;
    private int aol=0;
    public static String ncsForPop;
    public static String slForPop;
    public static String solForPop;

    private int ase=0;
    private int added=0,addls=0,addols=0,addedsr=0,addedssf=0,alreadyaddse=0,alreadyaddc=0;
    @FXML
    private TextField nosTF;
    @FXML
    private TextField tnoeTF;

    @FXML
    private CheckBox yesBox;
    private int yb=0,nb=0;
    
    
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
            Logger.getLogger(ESeriesAddPopUpController.class.getName()).log(Level.SEVERE, null, ex);
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
         ase=1;
         
         
        if (CategoriesTable.getSelectionModel().getSelectedItem() != null) {
        EC s = CategoriesTable.getSelectionModel().getSelectedItem();
        ncsForPop = s.getTitle();
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
        String nos = nosTF.getText();
        String tnoe = tnoeTF.getText();

        if(yesBox.isSelected())
        {
            nb=0;
            yb=1;
        }
        if(!(yesBox.isSelected()))
        {
            yb=0;
            nb=1;
        }

        
        if(addls==0)
        {
            sal=1;
        }
        if(addols==0)
        {
            sol=1;
        }
        if(addedsr==0)
        {
            sar="G";
        }
        
        if(addedssf==0 && nb==1){
        String ac= "INSERT INTO series VALUES(NULL,'"+ti+"','"+de+"',"+String.valueOf(ry)+","+sal+","+sol+","+String.valueOf(nos)+",'"+sar+"',NULL,'Ongoing',"+tnoe+");";
        Statement statc = con.createStatement();
        ResultSet rac=statc.executeQuery(ac);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','series')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        if(addedssf==0 && yb==1){
        String ac= "INSERT INTO series VALUES(NULL,'"+ti+"','"+de+"',"+String.valueOf(ry)+","+sal+","+sol+","+String.valueOf(nos)+",'"+sar+"',NULL,'Completed',"+tnoe+");";
        Statement statc = con.createStatement();
        ResultSet rac=statc.executeQuery(ac);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','series')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        if(addedssf==1 && nb==1){
        String ac= "INSERT INTO series VALUES(NULL,'"+ti+"','"+de+"',"+String.valueOf(ry)+","+sal+","+sol+","+String.valueOf(nos)+",'"+sar+"','"+saor+"','Ongoing',"+tnoe+");";
        Statement statc = con.createStatement();
        ResultSet rac=statc.executeQuery(ac);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','series')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        if(addedssf==1 && yb==1){
        String ac= "INSERT INTO series VALUES(NULL,'"+ti+"','"+de+"',"+String.valueOf(ry)+","+sal+","+sol+","+String.valueOf(nos)+",'"+sar+"','"+saor+"','Completed',"+tnoe+");";
        Statement statc = con.createStatement();
        ResultSet rac=statc.executeQuery(ac);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','series')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        
        String ff = "SELECT series_id FROM series WHERE series_id>=all(SELECT series_id FROM series);";
        Statement stff = con.createStatement();
        ResultSet rff = stff.executeQuery(ff);
        while(rff.next()){
            ssadd=rff.getInt("series_id");
        }
        for(int i=0;i<helps.size();i++){
        String aa = "SELECT category_id FROM category WHERE name='"+helps.get(i)+"';";
        Statement staa = con.createStatement();
        ResultSet raa = staa.executeQuery(aa);
        
        while(raa.next()){
       String afa = "SELECT category_id FROM series_category WHERE series_id="+ssadd+";";
        Statement staaf = con.createStatement();
        ResultSet raaf = staaf.executeQuery(afa);
        while(raaf.next()){
            if(raaf.getInt("category_id")==raa.getInt("category_id")){
                alreadyaddc=1;
            }
        }
        if(alreadyaddc==0){
        String deez = "INSERT INTO series_category VALUES("+ssadd+","+raa.getInt("category_id")+");";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','series category')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }alreadyaddc=0;}}
        for(int j=0;j<ESeriesAddSeasonsPopUpController.helpseas.size();j++){
        String cs = "SELECT season_number FROM seasons WHERE series_id= "+ssadd+";";
        Statement stcs = con.createStatement();
        ResultSet rcs=stcs.executeQuery(cs);
        while(rcs.next())
        {
            if(rcs.getInt("season_number")==ESeriesAddSeasonsPopUpController.helpseas.get(j).getNos())
            {
                alreadyaddse=1;
            }
        }
        if(alreadyaddse==0){
        String seas = "INSERT INTO seasons VALUES("+ssadd+","+ESeriesAddSeasonsPopUpController.helpseas.get(j).getNos()+","+ESeriesAddSeasonsPopUpController.helpseas.get(j).getRy()+","+ESeriesAddSeasonsPopUpController.helpseas.get(j).getNoe()+");";
        Statement stse = con.createStatement();
        ResultSet rse = stse.executeQuery(seas);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','seasons')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        alreadyaddse=0;
        }
        helps.clear();
        ESeriesAddSeasonsPopUpController.helpseas.clear();
        finsAddA=1;
    }

    @FXML
    private void addToList(ActionEvent event) throws SQLException {
         DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        String ti = tiTF.getText();
        String de = deTF.getText();
        String ry = ryTF.getText();
        String nos = nosTF.getText();
        String tnoe = tnoeTF.getText();
       
        if(ase==1){
        helps.add(ncsForPop);

        ase=0;
        
    }
    }

    @FXML
    private void Cancel(ActionEvent event) {
        helps.clear();
        ESeriesAddSeasonsPopUpController.helpseas.clear();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void OnClickL(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         al=1;
         
        if (lanT.getSelectionModel().getSelectedItem() != null) {
        ELname s = lanT.getSelectionModel().getSelectedItem();
        slForPop = s.getName();
        }   
        }
    }

    @FXML
    private void OnClickOL(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         aol=1;
         
        if (olTable.getSelectionModel().getSelectedItem() != null) {
        ELname s = olTable.getSelectionModel().getSelectedItem();
        solForPop = s.getName();
        }   
        }
    }

    @FXML
    private void AddL(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
       
        if(al==1){
        String ac= "SELECT language_id FROM language WHERE name='"+slForPop+"';";
        Statement statc = con.createStatement();
        ResultSet rac=statc.executeQuery(ac);
        while(rac.next()){
            sal=rac.getInt("language_id");
        }
        }
        addls=1;
    }

    @FXML
    private void AddOL(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
       
        if(aol==1){
        String ac= "SELECT language_id FROM language WHERE name='"+solForPop+"';";
        Statement statc = con.createStatement();
        ResultSet rac=statc.executeQuery(ac);
        while(rac.next()){
            sol=rac.getInt("language_id");
        }
        }
        addols=1;
    }

    @FXML
    private void OnClickR(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         selr=1;
         
        if (RC.getSelectionModel().getSelectedItem() != null) {
        EC s = RC.getSelectionModel().getSelectedItem();
        addsr = s.getTitle();
        }   
        }
    }

    @FXML
    private void AddR(ActionEvent event) {
        if(selr==1){
        sar=addsr;
        }
        addedsr=1;
    }

    @FXML
    private void OnClickSF(MouseEvent event) {
         if(event.getButton().equals(MouseButton.PRIMARY)){
         selsf=1;
         
        if (SFC.getSelectionModel().getSelectedItem() != null) {
        EC s = SFC.getSelectionModel().getSelectedItem();
        addssf = s.getTitle();
        }   
        }
    }

    @FXML
    private void AddSF(ActionEvent event) {
        if(selsf==1){
        saor=addssf;
        }
        addedssf=1;
    
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
    private void addSeason(ActionEvent event) throws IOException {
       
            
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ESeriesAddSeasonsPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
        ESeriesAddSeasonsPopUpController EAddPopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        
        
    
    }

    
}
