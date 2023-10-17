package TVDemand;

import static TVDemand.EFilmUpdatePopUpController.cfordelf;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ESeriesAddSeasonsPopUpController implements Initializable {

    @FXML
    private Button addButton;
    @FXML
    private TextField nosTF;
    @FXML
    private TextField noeTF;
    @FXML
    private TextField ryTF;
    @FXML
    private Button cancelButton;
    public static ArrayList<ESeasonFSeries> helpseas= new ArrayList<ESeasonFSeries>(); 


  
    @Override
    public void initialize(URL url, ResourceBundle rb){
         
    }    

    @FXML
    private void AddDialog(ActionEvent event) {
        int snos = Integer.parseInt(nosTF.getText());
        int snoe = Integer.parseInt(noeTF.getText());
        int ry = Integer.parseInt(ryTF.getText());
        helpseas.add(new ESeasonFSeries(snos,snoe,ry));

        
    }

    @FXML
    private void Cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
}
