package player;

import account.gui.MainStage;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import player.gui.TrackScene;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage = MainStage.getStage();
        TrackScene scene = new TrackScene();

        MainStage.loadScene(scene.getScene(), "Playlist");
        stage.show();
    }
}


