/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.gui;

import common.Database;
import common.main;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
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
 * @author monicadzhaleva
 */
public class GUIController implements Initializable {

    @FXML
    public TextField searchField;
    public RadioButton byName,byArtist,byGenre ;
    /**
     * Initializes the controller class.
     */
    public static String text;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
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
            System.out.println("byName works");
        }
        else if(byArtist.isSelected()){
            System.out.println("byArtist works");
        }
        else if(byGenre.isSelected()){
            System.out.println("byGenre works");
        }
    
    
    }
    
    
    
}
    

