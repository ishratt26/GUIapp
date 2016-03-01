package account.gui.register;

import account.logic.Register;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterSceneController {
    
    private boolean promptPayment = false;
    Register register;
    
    @FXML private ScrollPane content;
    @FXML private TextField usernameTextField, firstNameTextField, lastNameTextField, addressTextField, numberTextField, emailTextField;
    @FXML private PasswordField passwordField, passwordConfirmField;
    @FXML private RadioButton freeRadioButton, premiumRadioButton;
    @FXML private Button submitButton;
    @FXML private Text errorText;
    
    public RegisterSceneController() {
        Platform.runLater(new Runnable() {
            public void run() {
                register = new Register();
                content.requestFocus();
            }
        });
    }
    
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
        String errors = errorCheck();
        if (errors.equals("")) {
            usernameTextField.setStyle("");
            firstNameTextField.setStyle("");
            lastNameTextField.setStyle("");
            addressTextField.setStyle("");
            numberTextField.setStyle("");
            emailTextField.setStyle("");
            passwordField.setStyle("");
            passwordConfirmField.setStyle("");
            if (promptPayment) {
                try {
                    Stage stage = (Stage) submitButton.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("payment/gui/PaymentScene.fxml"));
                    Scene scene = new Scene(root, 600, 700);
                    stage.setScene(scene);
                } catch (IOException ex) {
                    Logger.getLogger(RegisterSceneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                register.createUser(usernameTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), passwordField.getText(), emailTextField.getText(), addressTextField.getText(), Integer.parseInt(numberTextField.getText()), "");
            }
        } else {
            errorText.setText("");
            usernameTextField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            firstNameTextField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            lastNameTextField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            addressTextField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            numberTextField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            emailTextField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            passwordField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            passwordConfirmField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            errorText.setText(errors);
        }
    }
    
    public String errorCheck() {
        String errors = "";
        
        if (usernameTextField.getText().equals(""))
            errors = errors + " \u2022 Username cannot be empty.";
        if (firstNameTextField.getText().equals(""))
            errors = errors + " \u2022 First Name cannot be empty.";
        if (lastNameTextField.getText().equals(""))
            errors = errors + " \u2022 Last Name cannot be empty.";
        if (addressTextField.getText().equals(""))
            errors = errors + " \u2022 Contact Address cannot be empty.";
        if (numberTextField.getText().equals(""))
            errors = errors + " \u2022 Phone Number cannot be empty.";
        if (emailTextField.getText().equals(""))
            errors = errors + " \u2022 Email Address cannot be empty.";
        if (passwordField.getText().equals(""))
            errors = errors + " \u2022 Password cannot be empty.";
        
        //TODO: USERNAME ALREADY EXISTS
        
        //TODO: PASSWORD ALREADY EXISTS
        
        return errors;
    }
    
}
