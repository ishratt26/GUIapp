package account;

import account.gui.MainStage;
import account.gui.login.LoginScene;
import account.gui.register.RegisterScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage = MainStage.getStage();
        LoginScene scene = new LoginScene();

        MainStage.loadScene(scene.getScene(), "Login");
        stage.show();
    }
}
