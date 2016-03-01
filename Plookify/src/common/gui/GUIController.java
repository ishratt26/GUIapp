/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.gui;


import common.main;
import static common.main.AddSong;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import playlist.gui.MainStage;
import playlist.gui.addplaylist.AddPlaylistScene;

/**
 * FXML Controller class
 *
 * 
 */
public class GUIController implements Initializable {

    @FXML
    public TextField searchField;
    public RadioButton byName,byArtist,byGenre ;
    /**
     * Initializes the controller class.
     */
    public static String text;
    public static String searchBy;
    public static ObservableList<String> list;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       searchBy = "name";
        
    }    
        @FXML protected void AddPlaylistScene() throws IOException {
        MainStage.loadScene(new AddPlaylistScene().getScene(), "AddPlaylist");
    }
    @FXML    
    public void handleKeyPress(KeyEvent keyEvent) throws IOException{
        
        if(keyEvent.getCode() == KeyCode.ENTER)
        {   
          
            String text = searchField.getText();
            this.text = text;
            main.SearchScene();
                
        }
    
    }
    
    @FXML
    public void handleRadioButtons(){
        if(byName.isSelected()){
            searchBy = "name";
        }
        else if(byArtist.isSelected()){
            searchBy = "artist";
        }
        else if(byGenre.isSelected()){
            searchBy = "genre";
        }
    
    
    }
    
    
    
}
    

