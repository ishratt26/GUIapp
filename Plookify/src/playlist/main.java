package playlist;

import playlist.gui.MainStage;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import playlist.gui.PlaylistScene;

public class main extends Application {

    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage = MainStage.getStage();
        PlaylistScene scene = new PlaylistScene();

        MainStage.loadScene(scene.getScene(), "Playlist");
        stage.show();
    }
}


