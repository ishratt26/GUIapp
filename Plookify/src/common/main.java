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
        //radio();
//social();
    }
    private void common() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("gui/GUI.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }
    private void playlist() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(playlist.main.class.getResource("gui/PlaylistScene.fxml"));
        BorderPane playlist = loader.load();
        mainLayout.setLeft(playlist);
        
    }
   
    public void player() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(player.Main.class.getResource("gui/TrackScene.fxml"));
        Pane radio = loader.load();
        mainLayout.setBottom(radio);
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