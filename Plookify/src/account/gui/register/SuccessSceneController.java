package account.gui.register;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SuccessSceneController implements Initializable {
    
    @FXML private Text messageText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML protected void continueButton() {
        try {
            Stage stage = (Stage) messageText.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../login/LoginScene.fxml"));
            Scene scene = new Scene(root, 400, 600);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(RegisterSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
