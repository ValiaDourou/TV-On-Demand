package TVDemand;

import static TVDemand.EFilmUpdatePopUpController.cforaddf;
import static TVDemand.EFilmUpdatePopUpController.cfordelf;
import static TVDemand.EFilmUpdatePopUpController.fupfin;
import static TVDemand.EFilmUpdatePopUpController.lforaddf;
import static TVDemand.EFilmUpdatePopUpController.lfordelf;
import static TVDemand.EFilmUpdatePopUpController.olforaddf;
import static TVDemand.EFilmUpdatePopUpController.olfordelf;
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


public class ESeriesUpdatePopUpController implements Initializable {

    @FXML
    private TableView<EC> acTable;
    @FXML
    private TableColumn<EC, String> Categories;
    @FXML
    private Button UpdateSeriesButton;
    @FXML
    private Button addElement;
    @FXML
    private Button upSF;
    @FXML
    private TextField tiTF;
    @FXML
    private TextField deTF;
    @FXML
    private TextField ryTF;
    @FXML
    private TextField nosTF;
    @FXML
    private TextField raTF;
    @FXML
    private TextField sfTF;
    @FXML
    private TextField lTF;
    @FXML
    private TextField olTF;
    @FXML
    private TextField tnoeTF;
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
    @FXML
    private TableView<EC> alTable1;
    @FXML
    private TableColumn<EC, String> LTable1;
    @FXML
    private TableView<EC> alTable2;
    @FXML
    private TableColumn<EC, String> LTable2;
    @FXML
    private Button updateRatings;
    @FXML
    private CheckBox yesBox;

    @FXML
    private TableView<ESeasonFSeries> asTable;
    @FXML
    private TableColumn<ESeasonFSeries, Integer> ASeasons;
    @FXML
    private Button deleteSeason;
    @FXML
    private Button updateSeason;
    @FXML
    private Button addSeasons;
    @FXML
    private TableColumn<ESeasonFSeries, Integer> RYSeasons;
    @FXML
    private TableColumn<ESeasonFSeries, Integer> NOSSeasons;

    private int saddl=0,saddol=0,saddc=0,sdell=0,sdelol=0,sdelc=0,saddse=0,saddry,saddnoe;
    public static int saddnos;
    public static String cforadds;
    public static String cfordels;
    public static String lforadds;
    public static String lfordels;
    public static String olforadds;
    public static String olfordels;
    public static int supfin=0;
    private int alreadyinit=0;
    private int idofcats;
    private int yb=0,nb=0;
    private int alreadyaddedse=0;
    public static int tryingtoupdatese=0;
    ObservableList<EC> availableCat;
    ObservableList<EC> currentCat;
    ObservableList<ELname> availableL;
    ObservableList<EC> arat;
    ObservableList<ELname> availableOL;
    ObservableList<EC> aspf;
    ObservableList<ESeasonFSeries> ase;
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
        ASeasons.setCellValueFactory(new PropertyValueFactory<ESeasonFSeries,Integer>("nos"));
        RYSeasons.setCellValueFactory(new PropertyValueFactory<ESeasonFSeries,Integer>("ry"));
        NOSSeasons.setCellValueFactory(new PropertyValueFactory<ESeasonFSeries,Integer>("noe"));
        
        try {
            showInfo();
        
            availableCat = getACTable();
            currentCat = getCCTable();
            availableL = getAlTable();
            arat = getCRTable();
            availableOL = getAlTable();
            aspf = getCSFTable();
            ase = getASTable();
        } catch (SQLException ex) {
            Logger.getLogger(ESeriesUpdatePopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        acTable.setItems(availableCat);
        alTable.setItems(availableL);
        aolTable.setItems(availableOL);
        ccTable.setItems(currentCat);
        alTable1.setItems(arat);
        alTable2.setItems(aspf); 
        asTable.setItems(ase);
    }    
    
    private void showInfo() throws SQLException{
    DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        String a= "SELECT title,description,release_year,seasons,rating,special_features,language_id,original_language_id,totalnumofepisodes,completed FROM series WHERE series_id="+ESeriesPopUpController.sidForPop+";";
        
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
        nosTF.setText(String.valueOf(ra.getString("seasons")));
        raTF.setText(ra.getString("rating"));
        sfTF.setText(ra.getString("special_features"));
        tnoeTF.setText(String.valueOf(ra.getString("totalnumofepisodes")));
        if(ra.getString("completed").equals("Completed")){
            yesBox.setSelected(true);
        }
        if(ra.getString("completed").equals("Ongoing")){
            yesBox.setSelected(false);
        }
        while(rb.next()){
        lTF.setText(rb.getString("name"));}
        while(rc.next()){
        olTF.setText(rc.getString("name"));}
        }
        
    } 

    @FXML
    private void onClickAC(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         saddc=1;
         
        if (acTable.getSelectionModel().getSelectedItem() != null) {
        EC s = acTable.getSelectionModel().getSelectedItem();
        cforadds = s.getTitle();
        }   
        }
    }

    @FXML
    private void UpdateSeries(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        String t = tiTF.getText();
        String d = deTF.getText();
        String r = String.valueOf(ryTF.getText());
        String nos = String.valueOf(nosTF.getText());
        String noe = String.valueOf(tnoeTF.getText());
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
        

        if(yb==1){
        String a= "UPDATE series SET title='"+t+"',description='"+d+"',release_year="+r+",seasons="+nos+",completed='Completed',totalnumofepisodes="+noe+" WHERE series_id="+ESeriesPopUpController.sidForPop+";";
         Statement stat = con.createStatement();
        ResultSet ra=stat.executeQuery(a);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','series')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        if(nb==1){
             String a= "UPDATE series SET title='"+t+"',description='"+d+"',release_year="+r+",seasons="+nos+",completed='Ongoing',totalnumofepisodes="+noe+" WHERE series_id="+ESeriesPopUpController.sidForPop+";";
        Statement stat = con.createStatement();
        ResultSet ra=stat.executeQuery(a);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','series')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        ase=getASTable();
         asTable.setItems(ase);
        supfin=1;
    }

    @FXML
    private void addToList(ActionEvent event) throws SQLException, IOException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
      if(saddc==1)
      {
        String f = "SELECT category_id FROM category WHERE name='"+cforadds +"';";
        Statement stf = con.createStatement();
        ResultSet rf = stf.executeQuery(f);
        while(rf.next()){
            idofcats=rf.getInt("category_id");
        }
            String ah = "SELECT category_id FROM series_category WHERE series_id="+ESeriesPopUpController.sidForPop +";";
        Statement stah = con.createStatement();
        ResultSet rah = stah.executeQuery(ah);
        while(rah.next()){
        if(rah.getInt("category_id")==idofcats)
        {
            alreadyinit=1;
        }}
       
        if(alreadyinit==0){
        String deez = "INSERT INTO series_category VALUES("+ESeriesPopUpController.sidForPop+","+idofcats+");";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','series category')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
    
        }
      alreadyinit=0;
      
      }
      UpdateSeries(event);
     saddc=0;
        Parent troot = FXMLLoader.load(getClass().getResource("ESeriesUpdatePopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void UPsf(ActionEvent event) throws IOException, SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
      if(sdelol==1)
      {
        
        String deez = "UPDATE series SET special_features='"+olfordels+"' WHERE series_id="+ESeriesPopUpController.sidForPop+";";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','series')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        
      }
      sdelol=0;
      UpdateSeries(event);
    Parent troot = FXMLLoader.load(getClass().getResource("ESeriesUpdatePopUp.fxml"));
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
         saddl=1;
         
        if (alTable.getSelectionModel().getSelectedItem() != null) {
        ELname s = alTable.getSelectionModel().getSelectedItem();
        lforadds = s.getName();
        }   
        }
    }

    @FXML
    private void OnClickAOL(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         saddol=1;
         
        if (aolTable.getSelectionModel().getSelectedItem() != null) {
        ELname s = aolTable.getSelectionModel().getSelectedItem();
        olforadds = s.getName();
        }   
        }
    }

    @FXML
    private void AddL(ActionEvent event) throws IOException, SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
      if(saddl==1)
      {
        String f = "SELECT language_id FROM language WHERE name='"+lforadds +"';";
        Statement stf = con.createStatement();
        ResultSet rf = stf.executeQuery(f);
        while(rf.next()){
        String deez = "UPDATE series SET language_id="+rf.getInt("language_id")+" WHERE series_id="+ESeriesPopUpController.sidForPop+";";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','series')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
      }
      saddl=0;
      UpdateSeries(event);
    Parent troot = FXMLLoader.load(getClass().getResource("ESeriesUpdatePopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void AddOL(ActionEvent event) throws IOException, SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
      if(saddol==1)
      {
        String f = "SELECT language_id FROM language WHERE name='"+olforadds +"';";
        Statement stf = con.createStatement();
        ResultSet rf = stf.executeQuery(f);
        while(rf.next()){
        String deez = "UPDATE series SET original_language_id="+rf.getInt("language_id")+" WHERE series_id="+ESeriesPopUpController.sidForPop+";";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','series')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
      }
      saddol=0;
      UpdateSeries(event);
    Parent troot = FXMLLoader.load(getClass().getResource("ESeriesUpdatePopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void onClickCC(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         sdelc=1;
         
        if (ccTable.getSelectionModel().getSelectedItem() != null) {
        EC s = ccTable.getSelectionModel().getSelectedItem();
        cfordels = s.getTitle();
        }   
        }
    }

    @FXML
    private void DeleteFromList(ActionEvent event) throws IOException, SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        if(sdelc==1)
        {
            
           String f = "SELECT category_id FROM category WHERE name='"+cfordels +"';";
        Statement stf = con.createStatement();
        ResultSet rf = stf.executeQuery(f);
        while(rf.next()){
        String deez = "DELETE FROM series_category WHERE series_id="+ESeriesPopUpController.sidForPop+" AND category_id="+rf.getInt("category_id")+";";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','series category')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }}
        sdelc=0;
        UpdateSeries(event);
        Parent troot = FXMLLoader.load(getClass().getResource("ESeriesUpdatePopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void OnClickR(MouseEvent event) {
         if(event.getButton().equals(MouseButton.PRIMARY)){
         sdell=1;
         
        if (alTable1.getSelectionModel().getSelectedItem() != null) {
        EC s = alTable1.getSelectionModel().getSelectedItem();
        lfordels = s.getTitle();
        }   
        }
    }

    @FXML
    private void OnClickSF(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         sdelol=1;
         
        if (alTable2.getSelectionModel().getSelectedItem() != null) {
        EC s = alTable2.getSelectionModel().getSelectedItem();
        olfordels = s.getTitle();
        }   
        }
    
    }

    @FXML
    private void upR(ActionEvent event) throws SQLException, IOException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
      if(sdell==1)
      {
        
        String deez = "UPDATE series SET rating='"+lfordels+"' WHERE series_id="+ESeriesPopUpController.sidForPop+";";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Update','series')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        
      }
      sdell=0;
      UpdateSeries(event);
    Parent troot = FXMLLoader.load(getClass().getResource("ESeriesUpdatePopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
    }

    @FXML
    private void onClickAS(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
         saddse=1;
         
        if (asTable.getSelectionModel().getSelectedItem() != null) {
        ESeasonFSeries s = asTable.getSelectionModel().getSelectedItem();
        saddnos=s.getNos();
        saddry=s.getRy();
        saddnoe=s.getNoe();
        }   
        }
    }

    @FXML
    private void DeleteFromSeasons(ActionEvent event) throws SQLException, IOException {
         DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        if(saddse==1)
        {

        String deez = "DELETE FROM seasons WHERE series_id="+ESeriesPopUpController.sidForPop+" AND season_number="+saddnos+";";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                    String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Delete','seasons')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        saddse=0;
        UpdateSeries(event);
        Parent troot = FXMLLoader.load(getClass().getResource("ESeriesUpdatePopUp.fxml"));
        Scene Scene = new Scene(troot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.show();
        
    }

    @FXML
    private void UpdateSeasons(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ESeriesUpdateSeasonsPopUp.fxml"));
        DialogPane cSelected;
            
                cSelected = fxmlLoader.load();
            
       ESeriesUpdateSeasonsPopUpController EUpdatePopUpController = fxmlLoader.getController();
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(cSelected);
        dialog.setTitle("");
        
        Optional<ButtonType> cButton = dialog.showAndWait();
        UpdateSeries(event);
    }

    @FXML
    private void AddToSeasons(ActionEvent event) throws IOException, SQLException {
       FXMLLoader fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(getClass().getResource("ESeriesAddSeasonsPopUp.fxml"));
       DialogPane cSelected;
            
               cSelected = fxmlLoader.load();
            
       ESeriesAddSeasonsPopUpController EAddPopUpController = fxmlLoader.getController();
        
       Dialog<ButtonType> dialog = new Dialog<>();
       dialog.setDialogPane(cSelected);
       dialog.setTitle("");
        
       Optional<ButtonType> cButton = dialog.showAndWait();
        
       DBConnection connectNow = new DBConnection();
       Connection con = connectNow.createConnection();

         for(int j=0;j<ESeriesAddSeasonsPopUpController.helpseas.size();j++){

        String f = "SELECT season_number FROM seasons WHERE series_id="+ESeriesPopUpController.sidForPop+";";
        Statement stf = con.createStatement();
        ResultSet rf = stf.executeQuery(f);
        while(rf.next()){
       if(rf.getInt("season_number")==ESeriesAddSeasonsPopUpController.helpseas.get(j).getNos())
            {
                alreadyaddedse=1;
            }}
        if(alreadyaddedse==0){
        String deez = "INSERT INTO seasons VALUES("+ESeriesPopUpController.sidForPop+","+ESeriesAddSeasonsPopUpController.helpseas.get(j).getNos()+","+ESeriesAddSeasonsPopUpController.helpseas.get(j).getRy()+","+ESeriesAddSeasonsPopUpController.helpseas.get(j).getNoe()+");";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','seasons')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);

        }
        alreadyaddedse=0;
      
      }
     ESeriesAddSeasonsPopUpController.helpseas.clear();

      UpdateSeries(event);
        Parent root = FXMLLoader.load(getClass().getResource("ESeriesUpdatePopUp.fxml"));
        Scene scene = new Scene(root);
        Stage window1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        window1.setScene(scene);
        window1.show();
      
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
                
        
            String s = "SELECT c.name FROM category AS c INNER JOIN series_category AS sc ON sc.category_id=c.category_id INNER JOIN series AS s ON s.series_id=sc.series_id WHERE s.series_id="+ESeriesPopUpController.sidForPop+";";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new EC(rs.getString("c.name")));
        }
        
        return listC;   
    }
    
    private ObservableList<ESeasonFSeries> getASTable() throws SQLException{
     DBConnection connectNow = new DBConnection();
        Connection conCat = connectNow.createConnection();
        ObservableList<ESeasonFSeries> listC = FXCollections.observableArrayList();
                
        
            String s = "SELECT se.season_number,se.release_year,se.number_of_episodes FROM seasons AS se INNER JOIN series AS s ON s.series_id=se.series_id WHERE s.series_id="+ESeriesPopUpController.sidForPop+";";
            Statement st = conCat.createStatement();
        ResultSet rs = st.executeQuery(s);
        
        while(rs.next()){
            listC.add(new ESeasonFSeries(rs.getInt("se.season_number"),rs.getInt("se.number_of_episodes"),rs.getInt("se.release_year")));
        }
        
        return listC;   
    }

    
}
