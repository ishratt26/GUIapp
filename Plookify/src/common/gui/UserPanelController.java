package common.gui;

import common.CurrentUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class UserPanelController implements Initializable {

    @FXML
    private Text customerUsername;
    @FXML
    private Text customerID;
    @FXML
    private Text usernameFullName;
    @FXML
    private Text usernameIsPremium;
    
    public UserPanelController() {
        Platform.runLater(() -> {
            customerUsername.setText("Username: " + CurrentUser.username);
            customerID.setText("Customer ID: " + CurrentUser.customerID);
            usernameFullName.setText("Full Name: " + CurrentUser.fullName);
            if (CurrentUser.isPremium)
                usernameIsPremium.setText("Account Type: Premium");
            else
                usernameIsPremium.setText("Account Type: Free");
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void manageDevicesButton(ActionEvent event) {
    }
    
}
