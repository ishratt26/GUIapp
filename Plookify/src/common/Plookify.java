/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author tunazzinaIshrat
 */
public class Plookify {
    
    private static Scene scene;
    private static BorderPane mainLayout;
    
    public static void createMainWindow() {
        try {
        mainLayout = new BorderPane();
        mainLayout = FXMLLoader.load(main.class.getResource("gui/GUI.fxml"));
        UserPanel();
        playlist();
        player();
        homeScreen();
        
        scene = new Scene(mainLayout);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void playlist() throws IOException {
        BorderPane playlist = FXMLLoader.load(playlist.main.class.getResource("gui/PlaylistScene.fxml"));
        mainLayout.setLeft(playlist);
    }
    
    public static void homeScreen() throws IOException {
        AnchorPane home = FXMLLoader.load(common.main.class.getResource("gui/homeScreen.fxml"));
        mainLayout.setCenter(home);
    }
    
    public static void player() throws IOException {
        Pane player = FXMLLoader.load(player.Main.class.getResource("gui/TrackScene.fxml"));
        mainLayout.setBottom(player);
    }
    
    public static void addingPlaylist() throws IOException {
        AnchorPane playlist = FXMLLoader.load(playlist.main.class.getResource("gui/addplaylist/addPlaylist.fxml"));
        mainLayout.setCenter(playlist);
        UserPanel();

    }
    
    public static void SearchScene() throws IOException
    {
        AnchorPane searchList = FXMLLoader.load(player.Main.class.getResource("gui/Search.fxml"));
        mainLayout.setCenter(searchList);

    }
    
    
    public static Scene getScene() {
        return scene;
    }
    
    public static  void TrackScreen() throws IOException
    {
        Pane player = FXMLLoader.load(player.Main.class.getResource("gui/TrackList.fxml"));
        mainLayout.setCenter(player);
        UserPanel();
    }
    
    public static void UserPanel() throws IOException {
        BorderPane userPanel = FXMLLoader.load(common.main.class.getResource("gui/UserPanel.fxml"));
        mainLayout.setRight(userPanel);
    }
    
    public static void RadioScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(radio.main.class.getResource("gui/radio.fxml"));
        Pane player = FXMLLoader.load(radio.main.class.getResource("gui/radio.fxml"));
        mainLayout.setCenter(player);
        UserPanel();
    }
    
}
