package radio;

import radio.gui.mStage;
import radio.gui.loadScene;
//import account.gui.MainStage;
//import account.gui.login.LoginScene;
import javafx.application.Application;
import javafx.stage.Stage;


public class main extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
        
    @Override
    public void start(Stage stage) {
        stage = mStage.getStage();
        loadScene scene = new loadScene();

        mStage.loadScene(scene.getScene(), "Radio");
        stage.show();
    }
}