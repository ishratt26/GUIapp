/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JOptionPane;

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
    private Button rewind;
    @FXML
    private Button forward;
    
    String path = "/Users/Ishrat/Desktop/Software Engineering Project/NetBeansProjects/SE21/Plookify/src/resources/furElise.mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer player = new MediaPlayer(media);
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }  
    
    
    

    
    @FXML
    public void handleButtonAction(ActionEvent e) {
        if (e.getSource() == play) {
            System.out.println("Play!");
            player.play();
        }
        else if(e.getSource() == rewind){
            System.out.println("Rewind!");
        }
        else if(e.getSource() == forward){
            System.out.println("Forward!");
        }
    }
    
}
