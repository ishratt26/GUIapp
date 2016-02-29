package playlist.gui.addplaylist;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class AddPlaylistScene {

    private Scene scene;

    public AddPlaylistScene() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addPlaylist.fxml"));
            scene = new Scene(root, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    public Scene getScene() {
        return scene;
    }
}
