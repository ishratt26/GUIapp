package common;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import playlist.gui.addplaylist.AddPlaylistController;



public class main extends Application 
{
    private static Stage stage;
    private static BorderPane mainLayout;

 
    @Override
    public void start(Stage stage) throws Exception 
    {
        this.stage = stage;
        this.stage.setTitle("Plookify");
        common();
        playlist();
        player();
        homeScreen();
        //radio();
//social();
    }
    public static void common() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("gui/GUI.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    public static void playlist() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(playlist.main.class.getResource("gui/PlaylistScene.fxml"));
        BorderPane playlist = loader.load();
        mainLayout.setLeft(playlist);
        
    }
   
       public static void addingPlaylist() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(playlist.main.class.getResource("gui/addplaylist/addPlaylist.fxml"));
        AnchorPane playlist = loader.load();
        mainLayout.setCenter(playlist);
        
    }
       
       public static void homeScreen() throws IOException 
       {
             FXMLLoader loader = new FXMLLoader();
        loader.setLocation(common.main.class.getResource("gui/homeScreen.fxml"));
        AnchorPane playlist = loader.load();
        mainLayout.setCenter(playlist);
       }
       
    public void player() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(player.Main.class.getResource("gui/TrackScene.fxml"));
        Pane player = loader.load();
        mainLayout.setBottom(player);
    }
    
    public static  void TrackScreen() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(player.Main.class.getResource("gui/TrackList.fxml"));
        Pane player = loader.load();
        mainLayout.setCenter(player);
    }
    
        public static  void RadioScreen() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(radio.main.class.getResource("gui/radio.fxml"));
        Pane player = loader.load();
        mainLayout.setCenter(player);
    }
    
    public static void SearchScene() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(player.Main.class.getResource("gui/Search.fxml"));
        AnchorPane searchList = loader.load();
        mainLayout.setCenter(searchList);
        AddSong();

    }
    
    public static void AddSong() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(player.Main.class.getResource("gui/AddSongs.fxml"));
        BorderPane addSongs = loader.load();
        mainLayout.setRight(addSongs);
    }
    /*
    public static void radio() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(radio.Main.class.getResource("gui/RadioGUIMain.fxml"));
        AnchorPane radio = loader.load();
        mainLayout.setCenter(radio);
    }
    
    public void social() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(social.gui.SocialMain.class.getResource("SocialGui.fxml"));
        Pane social = loader.load();
        mainLayout.setRight(social);
    }
    
    public static void radioLOAD() throws IOException //Loads Radio into middle pannel -AS
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(radio.Main.class.getResource("gui/RadioFXML.fxml")); 
        AnchorPane radio = loader.load();
        mainLayout.setCenter(radio);
    }*/
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
}