package account.gui.login;

import account.gui.MainStage;
import account.gui.register.RegisterScene;
import account.logic.Login;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import player.gui.trackPlayer;

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
            loginHandler.login(usernameTextField.getText(), passwordTextField.getText());
            
            //MainStage.loadScene(new trackPlayer().getScene(), "Plookify");
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
        MainStage.loadScene(new RegisterScene().getScene(), "Register");
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
