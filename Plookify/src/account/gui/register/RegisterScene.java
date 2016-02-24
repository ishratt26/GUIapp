package account.gui.register;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class RegisterScene {

    private Scene scene;

    public RegisterScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("RegisterScene.fxml"));
            scene = new Scene(root, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene() {
        return scene;
    }
}
