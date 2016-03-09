/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.logic;

import common.Database;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import player.gui.TrackPlayerController;

/**
 * FXML Controller class
 *
 * @author tunazzinaIshrat
 */
public class AddSongsController implements Initializable {

    @FXML private Button addSong, removeSong, addAll, playFrom;
    @FXML private TextField minutes, seconds;
  
    
    public ObservableList<Track> nowPlayingList, removeList, oldList;
    public static ArrayList<Track> list1 = new ArrayList<Track>();
    public static ArrayList<Track> totList = new ArrayList<Track>();
    
    public static long mins;
    public static long secs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void addSong(){
        nowPlayingList = SearchController.selectedList.getSelectionModel().getSelectedItems();
        for(Track track : nowPlayingList){
            if(!list1.contains(track)){
                list1.add(track);
            }
        }
        if(!list1.isEmpty()){
            updateListAdding();
        }
        //db connection and insert into database
        ObservableList<Track> tracks = FXCollections.observableArrayList(totList);
        SearchController.nowPlayingList.setItems(tracks);
         
        
    }
    
    @FXML
    public void removeSong(){
       removeList= SearchController.nowPlayingList.getSelectionModel().getSelectedItems();
       for(int i=0; i<removeList.size(); i++){
           Track song = removeList.get(i);
            if(list1.contains(song)){
                list1.remove(song);  
            }
        }
       totList = list1;
       ObservableList<Track> tracks = FXCollections.observableArrayList(totList);
       SearchController.nowPlayingList.setItems(tracks);
       /*SearchController.nowPlayingList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Boolean>() {
                
           @Override
           public void changed(ObservableValue ov,Boolean old_val, Boolean new_val) {
                        System.out.println(new_val);
                }
            });*/
       
       
    }
    
    @FXML
    public void addAll(){
        ObservableList<Track> items = SearchController.selectedList.getItems();
        for(Track song : items){
            if(!list1.contains(song)){
                list1.add(song);
            }
        }
        if(!list1.isEmpty()){
            updateListAdding();
        }
        
        ObservableList<Track> tracks = FXCollections.observableArrayList(totList);
        SearchController.nowPlayingList.setItems(tracks);
        
       
        
    }
    
    public void updateListAdding(){
        for(int i=0; i<list1.size(); i++){
            Track song = list1.get(i);
            if(!totList.contains(song)){
                totList.add(song);
            }
        }
    }
    
    @FXML
    public void playFrom(){
        mins = Long.parseLong(minutes.getText()); 
        secs = Long.parseLong(seconds.getText()); 
       long min = TimeUnit.MINUTES.toMillis(mins);
       long sec = TimeUnit.SECONDS.toMillis(secs);
       long tot = min + sec;
        System.out.println(mins + ":" + secs); 
        Duration dur = new Duration(tot);
        TrackPlayerController.playAt(dur);
        
    }
    
    public void addToDb(){
        Database db = new Database();
        Connection c = null;
        Statement stmt = null;
        try{
            c  = db.getConnection();
            stmt.setQueryTimeout(10);
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String query = "INSERT into nowPlaying('') VALUES ()";
            
        }
        catch(SQLException e ){
        
        }
    
    }
}
