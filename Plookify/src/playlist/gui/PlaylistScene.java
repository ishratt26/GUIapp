package playlist.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class PlaylistScene {

    private Scene scene;

    public PlaylistScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("PlaylistScene.fxml"));
            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene() {
        return scene;
    }
}
