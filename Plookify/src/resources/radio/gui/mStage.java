/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gabrieluliano
 */
public class mStage {
    

    private static final Stage stage = new Stage();

    public static void loadScene(Scene scene, String title) {
        stage.setTitle(title);
        stage.setScene(scene);
    }

    public static Stage getStage() {
        return stage;
    }
}