package playlist.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 *
 * 
 */

public class main extends Application {
    
    
    
    @FXML Button NewPlaylist;
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        
        Parent root = FXMLLoader.load(getClass().getResource("playlist.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Playlist");
        stage.setScene(scene);
        stage.show();
    }

    
    public static void main(String[] args) 
    {
        launch(args);
    }
  
}