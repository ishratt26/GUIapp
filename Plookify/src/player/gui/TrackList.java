/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.gui;

/**
 *
 * @author tunazzinaIshrat
 */
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class TrackList {

    private Scene scene;

    public TrackList() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TrackList.fxml"));
            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene() {
        return scene;
    }
}
