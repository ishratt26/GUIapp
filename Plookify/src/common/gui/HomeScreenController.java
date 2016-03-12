/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static player.logic.SearchController.stage;
import player.logic.Track;

/**
 * FXML Controller class
 *
 * 
 */
public class HomeScreenController implements Initializable {
    
    @FXML TableView<Track> nowPlaying;
    @FXML
    private ContextMenu options;
    @FXML
    private MenuItem option1;
    @FXML
    private MenuItem option2;
    @FXML private TableColumn<Track, String>  NameColumn;
    @FXML private TableColumn<Track, String> ArtistColumn;
    @FXML private TableColumn<Track, String> GenreColumn;
    @FXML private TableColumn<Track, Integer> LengthColumn;
    
    public static TableView<Track> nowPlayingList;
    public ObservableList<Track> nowPlayinglist, removeList;
    public static ArrayList<Track> list1 = new ArrayList<Track>();
    public static ArrayList<Track> totList = new ArrayList<Track>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("trackName"));
        ArtistColumn.setCellValueFactory(new PropertyValueFactory<>("trackArtist"));
        LengthColumn.setCellValueFactory(new PropertyValueFactory<>("trackLength"));
        GenreColumn.setCellValueFactory(new PropertyValueFactory<>("trackGenre"));

        nowPlayingList = nowPlaying;
        nowPlayingList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        if(!totList.isEmpty())
            {
                ObservableList<Track> oldList = FXCollections.observableArrayList(totList);
                nowPlayingList.setItems(oldList);
                nowPlaying = nowPlayingList;
            }
    }    
    
    @FXML
    public void playFrom() throws IOException{
        stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        Parent root = FXMLLoader.load(player.Main.class.getResource("gui/PlaySongAt.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        
    }
    
    @FXML
    public void removeSong(){
       removeList= nowPlayingList.getSelectionModel().getSelectedItems();
       for(int i=0; i<removeList.size(); i++){
           Track song = removeList.get(i);
            if(totList.contains(song)){
                totList.remove(song);  
            }
        }
       ObservableList<Track> tracks = FXCollections.observableArrayList(totList);
       nowPlayingList.setItems(tracks);  
    }
    
}
