/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 
 */
public class trackPlayer extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("player.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Track Player");
        stage.setScene(scene);
        stage.show();
    }
    
    public Scene getScene() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("player.fxml"));
        
        Scene scene = new Scene(root);
        return scene;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
