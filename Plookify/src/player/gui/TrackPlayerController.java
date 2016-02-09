/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.gui;

import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
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
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("View is now loaded!");
    }
    
    @FXML
    public void handleButtonAction(ActionEvent e) {
        if (e.getSource() == play) {
            JOptionPane.showMessageDialog(null, "Play!");
        }
        else if(e.getSource() == rewind){
            JOptionPane.showMessageDialog(null, "Rewind!");
        }
        else if(e.getSource() == forward){
            JOptionPane.showMessageDialog(null, "Forward!");
        }
    }
}
