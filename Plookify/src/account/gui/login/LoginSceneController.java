package account.gui.login;

import account.logic.Login;
import common.CurrentUser;
import common.Database;
import common.Plookify;
import common.main;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginSceneController {

    private Login loginHandler;
    
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private Text errorText;

    public LoginSceneController() {
        loginHandler = new Login();
    }

    @FXML protected void submitButton() throws Exception {
        String errors = errorCheck(usernameTextField.getText(), passwordTextField.getText());
        if (errors.equals("")) {
            usernameTextField.setStyle("");
            passwordTextField.setStyle("");
            errorText.setText("");
            //TODO: GO TO THE MAIN WINDOW
            CurrentUser.username = usernameTextField.getText();
            CurrentUser.fullName = new Database().getUserInfo(CurrentUser.username, "fullName");
            CurrentUser.customerID = new Database().getUserInfo(CurrentUser.username, "ID");
            CurrentUser.isPremium = new Database().isPremium(CurrentUser.username);
            Stage stage = (Stage) usernameTextField.getScene().getWindow();
            Plookify.createMainWindow();
            stage.setScene(Plookify.getScene());
            stage.centerOnScreen();
        } else {
            errorText.setText("");
            usernameTextField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            passwordTextField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            errorText.setText(errors);
        }
    }

    @FXML protected void clearButton() {
        usernameTextField.setText("");
        passwordTextField.setText("");
        usernameTextField.setStyle("");
        passwordTextField.setStyle("");
        errorText.setText("");
        usernameTextField.requestFocus();
    }

    @FXML protected void registerScene() {
        try {
            Stage stage = (Stage) errorText.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../register/RegisterScene.fxml"));
            Scene scene = new Scene(root, 600, 700);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String errorCheck(String username, String password) {
        String errors = "";

        if (username.equals(""))
            errors = errors + " \u2022 Username cannot be empty.";
        if (password.equals(""))
            errors = errors + "\n \u2022 Password cannot be empty.";
        if (!loginHandler.loginValidation(username, password))
            errors = errors + "\n \u2022 Username or/and password are incorrect.";

        return errors;
    }
}
