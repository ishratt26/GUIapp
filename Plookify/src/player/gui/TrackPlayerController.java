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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import player.logic.SearchController;
import player.logic.Track;
import player.logic.storedController;

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
    public Slider slider;
    public Label elapsed;
    public String path;
    public Media media;
    
    final MediaView view = new MediaView();
    MediaPlayer player;
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slider = timeSlider;
        elapsed = timeElapsed; 
        storedController.getInstance().setController(this);
    }  
    
    
    @FXML
    public void play(){
       try{
           
           if(player==null){
                Track track = SearchController.nowPlayingList.getSelectionModel().getSelectedItem();
                this.path = track.getTrackPath();
                media = new Media(new File(path).toURI().toString());
                this.player = new MediaPlayer(media);
                player.setOnReady(new Runnable() {
                    @Override
                    public void run() {

                    setTimer(player);
                    player.play();
                    updateValues();
                    updateSlider(player);
                    }
                
                });
                player.setOnEndOfMedia(new Runnable() {
                @Override 
                public void run() {
                  player.stop(); 
              }
             });
            }
            else if(player.getStatus().equals(Status.PAUSED)){
               Track track = SearchController.nowPlayingList.getSelectionModel().getSelectedItem();
               String path1 = track.getTrackPath();
               if(path.equals(path1)){
                    player.play();
                    System.out.println(path1);
                }
               else{
                    player.stop();
                    media = new Media(new File(path1).toURI().toString());
                    this.player = new MediaPlayer(media);
                    this.path = path1;
                    player.setOnReady(new Runnable() {
                    @Override
                    public void run() {

                    setTimer(player);
                    player.play();
                    updateValues();
                    updateSlider(player);
                    }
                
                });
               }
            }
            else if(player.getStatus().equals(Status.PLAYING)){
                player.stop();
                Track track = SearchController.nowPlayingList.getSelectionModel().getSelectedItem();
                String path = track.getTrackPath();
                media = new Media(new File(path).toURI().toString());
                this.player = new MediaPlayer(media);
                player.setOnReady(new Runnable() {
                    @Override
                    public void run() {

                    setTimer(player);
                    player.play();
                    updateValues();
                    updateSlider(player);
                    }
                
                });
            }
            updateSlider(player);
       }
       catch(Exception e){
           System.out.println(e.getMessage());
       }
       
    }
    
    @FXML
    public void pause(){
        player.pause();
    }
    
    @FXML
    public void reload(){
        player.seek(player.getStartTime());
    }
    
    @FXML
    public void fastForward(){
        player.seek(player.getCurrentTime().multiply(1.5));
    }
    
    @FXML
    public void rewind(){
        player.seek(player.getCurrentTime().divide(1.5));
    }
    
    public void playAt(Duration d){
                Track track = SearchController.nowPlayingList.getSelectionModel().getSelectedItem();
                if(player != null && player.getStatus().equals(MediaPlayer.Status.PLAYING)){
                    player.stop();
                }
                path = track.getTrackPath();
                media = new Media(new File(path).toURI().toString());
                player = new MediaPlayer(media);
                player.setStartTime(d);
                player.play();
                System.out.println(d);
                player.setOnReady(new Runnable() {
                    @Override
                    public void run() {
                    //player.seek(d);
                    //player.setStartTime(d);
                    setTimer(player);
                    //player.play();
                    //updateValues();
                    updateSlider(player);
                    }
                
                });
    }
    
    
    @FXML
    public void skip(MediaPlayer curPlayer){
        
        /*MediaPlayer nextPlayer = players.get((players.indexOf(curPlayer) + 1) % players.size());
        setPlayer(nextPlayer);
        nextPlayer.play();
        setTimer(nextPlayer);
        updateSlider(nextPlayer);*/
    
    }
    
    @FXML
    public void skipBack(MediaPlayer curPlayer){
       /* MediaPlayer current = curPlayer;
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
        updateSlider(current);*/
    
    }
    
    protected void updateValues() {
        if (elapsed != null&&slider != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    Duration currentTime = player.getCurrentTime();
                    elapsed.setText(formatTime(currentTime, duration));
                    slider.setDisable(duration.isUnknown());
                    if (!slider.isDisabled() && duration.greaterThan(Duration.ZERO) && !slider.isValueChanging()) {
                        slider.setValue(currentTime.divide(duration).toMillis() * 100.0);
                    }
                    
                }
            });
        }
    }
    
    private String formatTime(Duration elapsed, Duration duration) {
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

    private void setTimer(MediaPlayer player){
        player.currentTimeProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                updateValues();
            }
        });
        player.seek(duration);
    }
    
    private void updateSlider(MediaPlayer player){
        duration = player.getMedia().getDuration();
            System.out.println(duration);
                slider.valueProperty().addListener((Observable ov) -> {
                    if (slider.isValueChanging()) {
                        // multiply duration by percentage calculated by slider position
                        player.seek(duration.multiply(slider.getValue() / 100.0));
                        //System.out.println(duration);
                    }
                    updateValues();
                });
    }

}
