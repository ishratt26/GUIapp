package account.gui;

import account.logic.Login;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginSceneActionController {

    private Login loginHandler;

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private Text errorText;

    public LoginSceneActionController() {
        loginHandler = new Login();
    }

    @FXML protected void submitButton() {
        String errors = errorCheck(usernameTextField.getText(), passwordTextField.getText());
        if (errors.equals("")) {
            usernameTextField.setStyle("");
            passwordTextField.setStyle("");
            errorText.setText("");
            //TODO: GO TO THE MAIN WINDOW
            loginHandler.login(usernameTextField.getText(), passwordTextField.getText());
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
