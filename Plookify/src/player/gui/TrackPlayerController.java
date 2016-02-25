/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.gui;

import java.io.File;
import java.io.FilenameFilter;
import static java.lang.Math.floor;
import static java.lang.String.format;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
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
    public Slider timeSlider;
    @FXML
    private Label timeElapsed;
    
    public Duration duration;
    
    private final String SONG_DIR = "/Users/Ishrat/Desktop/Software Engineering Project/NetBeansProjects/Plookify/build/classes/resources/songs/";
    final List<MediaPlayer> players = new ArrayList<>();
        
    final File dir =  new File(SONG_DIR);
    
    private MediaPlayer currentPlayer;

    
    /*String path = "build/classes/resources/songs/SeeYouAgain.mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer player = new MediaPlayer(media);
    Status status = player.getStatus();
    */
    
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Cannot find audio source directory: " + dir);
            Platform.exit();
            return;
        }
          
        
        for (String file : dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith(".mp3")) {
                    return true;
                }
                return false;
            }
        })) {
            players.add(createPlayer("file:///" + (dir + "\\" + file).replace("\\", "/").replaceAll(" ", "%20")));
        }
        if (players.isEmpty()) {
            System.out.println("No audio found in " + dir);
            Platform.exit();
            return;
        }

        
        
      
        
        for (int i = 0; i < players.size(); i++) {
        final MediaPlayer player     = players.get(i);
        setPlayer(player);
        final MediaPlayer nextPlayer = players.get((i + 1) % players.size());
        player.setOnEndOfMedia(new Runnable() {
        @Override public void run() {
          player.stop();
          nextPlayer.play();
          setPlayer(nextPlayer);
        }
      });
    }
        
        
        /*getPlayer().currentTimeProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                updateValues();
            }
        });*/
    }  
    
    
    

    
    @FXML
    public void handleButtonAction(ActionEvent e) {
        MediaPlayer player = getPlayer();
        setTimer(player);
        
        if (e.getSource() == play) {
                player.play();
                updateSlider(player);   
        }
        else if(e.getSource() == pause){
                player.pause();
        }    
        else if(e.getSource() == rewind){
            player.stop();
            skipBack(player);
        }
        else if(e.getSource() == forward){
            player.stop();
            skip(player);
            //player.seek(player.getCurrentTime().multiply(1.5));
        }
        else if(e.getSource() == reload){
            player.seek(player.getStartTime());
        }
        
        
    }
    
    private MediaPlayer createPlayer(String mediaSource) {
    final Media media = new Media(mediaSource);
    final MediaPlayer player = new MediaPlayer(media);
    player.setOnError(new Runnable() {
      @Override public void run() {
        System.out.println("Media error occurred: " + player.getError());
      }
    });
    return player;
  }
    
    public void skip(MediaPlayer curPlayer){
        
        MediaPlayer nextPlayer = players.get((players.indexOf(curPlayer) + 1) % players.size());
        setPlayer(nextPlayer);
        nextPlayer.play();
        setTimer(nextPlayer);
        updateSlider(nextPlayer);
    
    }
    
    public void skipBack(MediaPlayer curPlayer){
        MediaPlayer current = curPlayer;
        if(players.indexOf(current)==0)
        {
            setPlayer(current);
        }
        
        else{
        MediaPlayer prevPlayer = players.get((players.indexOf(current) - 1) % players.size());
        
        current = prevPlayer;
        setPlayer(current);
        }
        
        current.play();
        setTimer(current);
        updateSlider(current);
    
    }
    
    protected void updateValues() {
        if (timeElapsed != null&&timeSlider != null) {
            Platform.runLater(new Runnable() {

                public void run() {
                    Duration currentTime = getPlayer().getCurrentTime();
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

    private void setPlayer(MediaPlayer player) {
        currentPlayer = player;
    }

    private MediaPlayer getPlayer() {
        return currentPlayer;
    }
    
    private void setTimer(MediaPlayer player){
        player.currentTimeProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                updateValues();
            }
        });
    }
    
    private void updateSlider(MediaPlayer player){
        duration = player.getMedia().getDuration();
                timeSlider.valueProperty().addListener((Observable ov) -> {
                    if (timeSlider.isValueChanging()) {
                        // multiply duration by percentage calculated by slider position
                        player.seek(duration.multiply(timeSlider.getValue() / 100.0));
                    }
                    updateValues();
                });
    }
}
