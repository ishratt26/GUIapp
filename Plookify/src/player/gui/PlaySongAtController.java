/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import player.logic.SearchController;
import player.logic.storedController;

/**
 * FXML Controller class
 *
 * @author tunazzinaIshrat
 */
public class PlaySongAtController implements Initializable {

    @FXML
    private TextField minutes;
     @FXML
    private TextField seconds;
    @FXML
    private Button playFrom;
    
    long mins;
    long secs;
    private TrackPlayerController controller = storedController.getInstance().getController();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    public void playFrom(){
        mins = Long.parseLong(minutes.getText());
        secs = Long.parseLong(seconds.getText());
        long min = TimeUnit.MINUTES.toMillis(mins);
        long sec = TimeUnit.SECONDS.toMillis(secs);
        long tot = min + sec;
        Duration dur = new Duration(tot);
        controller.playAt(dur);
        SearchController.stage.close();
    }
    
}
