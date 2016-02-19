/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import playlist.gui.MainStage;
import playlist.gui.addplaylist.AddPlaylistScene;

/**
 * FXML Controller class
 *
 * @author monicadzhaleva
 */
public class GUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        @FXML protected void AddPlaylistScene() {
        MainStage.loadScene(new AddPlaylistScene().getScene(), "AddPlaylist");
    }
    
}
