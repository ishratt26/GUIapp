package account;

import account.gui.LoginScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        LoginScene scene = new LoginScene();
        stage.setTitle("Plookify - Login");

        stage.setScene(scene.getScene());
        stage.show();
    }
}
