package player;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import player.gui.MainStage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import player.gui.TrackScene;

public class Main extends Application {
    
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage = MainStage.getStage();
        //TrackScene scene = new TrackScene();
        TrackList list = new TrackList();
        MainStage.loadScene(list.getScene(), "Track Player");
        stage.show();
        
    }
}


