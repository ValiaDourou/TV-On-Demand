package TVDemand;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ECategoriesAddPopUpController implements Initializable {

    @FXML
    private Button addC;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField cTF;

     private int alreadyaddc=0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void AddDialog(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        
        String c = cTF.getText();
        String aa = "SELECT name FROM category;";
        Statement staa = con.createStatement();
        ResultSet raa = staa.executeQuery(aa);
        
        while(raa.next())
        {
        if(raa.getString("name").equals(c))
            {
             alreadyaddc=1;
            }
        }
        
        if(alreadyaddc==0)
        {
        String deez = "INSERT INTO category VALUES(NULL,'"+c+"');";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
        String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','category')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);}
        alreadyaddc=0;
    }

    @FXML
    private void Cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
}
