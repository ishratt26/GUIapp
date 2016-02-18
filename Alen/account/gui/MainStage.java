package account.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage {

    private static final Stage stage = new Stage();

    public static void loadScene(Scene scene, String title) {
        stage.setTitle(title);
        stage.setScene(scene);
    }

    public static Stage getStage() {
        return stage;
    }
}
