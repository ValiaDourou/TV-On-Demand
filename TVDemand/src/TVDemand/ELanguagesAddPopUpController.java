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

public class ELanguagesAddPopUpController implements Initializable {

    @FXML
    private Button addL;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField lTF;
    
    private int alreadyaddl=0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void AddDialog(ActionEvent event) throws SQLException {
        DBConnection connectNow = new DBConnection();
        Connection con = connectNow.createConnection();
        
        String l = lTF.getText();
        String aa = "SELECT name FROM language;";
        Statement staa = con.createStatement();
        ResultSet raa = staa.executeQuery(aa);
        
        while(raa.next())
        {
        if(raa.getString("name").equals(l))
            {
             alreadyaddl=1;
            }
        }
        
        if(alreadyaddl==0)
        {
        String deez = "INSERT INTO language VALUES(NULL,'"+l+"');";
        Statement std = con.createStatement();
        ResultSet rd = std.executeQuery(deez);
                String fl="INSERT INTO log VALUES(NULL,'"+Scene1Controller.Email+"',NOW(),'Successful','Insert','language')";
        Statement statfl = con.createStatement();
        ResultSet rfl = statfl.executeQuery(fl);
        }
        alreadyaddl=0;
    } 


    @FXML
    private void Cancel(ActionEvent event) 
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
}
