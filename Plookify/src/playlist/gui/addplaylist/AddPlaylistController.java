/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlist.gui.addplaylist;

import account.gui.MainStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import playlist.gui.PlaylistScene;
import playlist.playlist;

/**
 * FXML Controller class
 *
 * @author monicadzhaleva
 */
public class AddPlaylistController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
        @FXML private TextField playlistfield;
        @FXML private Button addbutton;
        @FXML private ListView listview;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   public void addplaylist() throws IOException
   {
       
     String input=playlistfield.getText(); ///Get playlist name input
     playlist playl=new playlist(input);
     System.out.println(playl.getName()); 
     ObservableList<String> tracks=FXCollections.observableArrayList();
    tracks.add(input);
          
    listview.setItems(tracks);
          }
    
    public void backMain() throws IOException
   {
        MainStage.loadScene(new PlaylistScene().getScene(), "Playlist");
   }
}
