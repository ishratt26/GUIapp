/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import player.gui.TrackPlayerController;

/**
 *
 * @author tunazzinaIshrat
 */
public class ComponentLoader extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("trackPlayer.fxml"));
        stage.setTitle("Track Player");
        stage.setScene(new Scene(root, 400, 400));
        stage.show();
        
      
    }
    

    
    
    
}
