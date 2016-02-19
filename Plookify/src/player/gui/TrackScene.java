package player.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class TrackScene {

    private Scene scene;

    public TrackScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TrackScene.fxml"));
            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene() {
        return scene;
    }
}
