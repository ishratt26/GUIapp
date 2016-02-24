package account.gui.register;

import account.gui.register.payment.gui.PaymentScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import player.gui.MainStage;

public class RegisterSceneController {
    
    private boolean promptPayment = false;
    
    @FXML private TextField usernameTextField, firstNameTextField, lastNameTextField, addressTextField, numberTextField, emailTextField;
    @FXML private PasswordField passwordField, passwordConfirmField;
    @FXML private RadioButton freeRadioButton, premiumRadioButton;
    @FXML private Button submitButton;
    
    @FXML protected void radioButtonControl() {
        if (freeRadioButton.isSelected()) {
            submitButton.setText("Register");
            promptPayment = false;
        }
        if (premiumRadioButton.isSelected()) {
            submitButton.setText("Continue");
            promptPayment = true;
        }
    }
    
    @FXML protected void submitButton() {
        if (promptPayment) {
            System.out.println("IAMHRERFE");
            MainStage.loadScene(new PaymentScene().getScene(), "Payment");
        } else {
            //TODO: GO TO THE PLOOKIFY MAIN WINDOW
        }
    }
    
}
