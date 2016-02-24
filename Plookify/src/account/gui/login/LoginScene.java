package account.gui.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class LoginScene {

    private Scene scene;

    public LoginScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
            scene = new Scene(root, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene() {
        return scene;
    }
}
