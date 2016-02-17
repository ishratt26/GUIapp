/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.gui;

import java.io.File;
import static java.lang.Math.floor;
import static java.lang.String.format;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author tunazzinaIshrat
 */
public class TrackPlayerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button play;
    @FXML
    private Button pause;
    @FXML
    private Button rewind;
    @FXML
    private Button forward;
    @FXML
    private Button reload;
    @FXML
    private Slider timeSlider;
    @FXML
    private Label timeElapsed;
    
    private Duration duration;
    
    
    String path = "/Users/Ishrat/Desktop/Software Engineering Project/NetBeansProjects/SE21/Plookify/src/resources/SeeYouAgain.mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer player = new MediaPlayer(media);
    Status status = player.getStatus();
    
    
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player.currentTimeProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                updateValues();
            }
        });
    }  
    
    
    

    
    @FXML
    public void handleButtonAction(ActionEvent e) {
        if (e.getSource() == play) {
                player.play();
                duration = player.getMedia().getDuration();
                timeSlider.valueProperty().addListener((Observable ov) -> {
                    if (timeSlider.isValueChanging()) {
                        // multiply duration by percentage calculated by slider position
                        player.seek(duration.multiply(timeSlider.getValue() / 100.0));
                    }
                    updateValues();
                });
           

        }
        else if(e.getSource() == pause){
                player.pause();
        }    
        else if(e.getSource() == rewind){
            player.seek(player.getCurrentTime().divide(1.5));
        }
        else if(e.getSource() == forward){
            player.seek(player.getCurrentTime().multiply(1.5));
        }
        else if(e.getSource() == reload){
            player.seek(player.getStartTime());
        }
        
        
    }
    
    protected void updateValues() {
        if (timeElapsed != null&&timeSlider != null) {
            Platform.runLater(new Runnable() {

                public void run() {
                    Duration currentTime = player.getCurrentTime();
                    timeElapsed.setText(formatTime(currentTime, duration));
                    timeSlider.setDisable(duration.isUnknown());
                    if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
                        timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
                    }
                    
                }
            });
        }
    }
    
    private static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int) floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60
                    - durationMinutes * 60;
            if (durationHours > 0) {
                return format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds, durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return format("%02d:%02d", elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }
    
}
