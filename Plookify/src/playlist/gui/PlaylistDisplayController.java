/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlist.gui;

import common.Database;
import common.Plookify;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import player.gui.TrackListController;
import static player.gui.TrackListController.trackArtist;
import static player.gui.TrackListController.trackGenre;
import static player.gui.TrackListController.trackLength;
import static player.gui.TrackListController.trackNames;
import static player.gui.TrackListController.trackPaths;
import player.logic.Track;

/**
 * FXML Controller class
 *
 * @author monicadzhaleva
 */
public class PlaylistDisplayController implements Initializable {

   
    
 @FXML
    private TableView<Track> tableView;
    @FXML
    private TableColumn<Track, String> nameColumn;
    @FXML
    private TableColumn<Track, String> artistColumn;
    @FXML
    private TableColumn<Track, String> genreColumn;
    @FXML
    private TableColumn<Track, Integer> lengthColumn;
    ArrayList<Integer> trackIDs=new ArrayList<Integer>();


     @FXML
    public Label playlist;
    private static String name;
    int artistID;
    int genreID;
    String song;
    String length;
    String path;
    @FXML
    public TextField newname;
    
    public static ArrayList<Track> allTracks;
    public static ArrayList<String> trackNames;
    public static ArrayList<String> trackArtist;
    public static ArrayList<String> trackGenre;
    public static ArrayList<String> trackPaths;
    public static ArrayList<String> trackLength;
   
    public static void setPlaylistName(String newValue) {
    System.out.println(newValue);
    name=newValue;
    }
    
    
   
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
          
           allTracks = new ArrayList<Track>();
            trackNames = new ArrayList<String>();
            trackArtist = new ArrayList<String>();
            trackGenre = new ArrayList<String>();
            trackLength = new ArrayList<String>();
            trackPaths = new ArrayList<String>();

        
        playlist.setText(name);
            ObservableList<Track> data=FXCollections.observableArrayList();
            Connection c=null;
            Statement stmt=null;
            try
            {
                Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:plookifyDB.sqlite");//connection to db, (db should be located in src)
            c.setAutoCommit(false);
            stmt=c.createStatement();     
            ResultSet q1=stmt.executeQuery("SELECT playlistID from playlist WHERE playlistName = '" + name +"';");
            int playID=q1.getInt("playlistID");

             ResultSet q2= stmt.executeQuery("SELECT trackID from playlistTracks WHERE playlistID= " + playID +";");
             while(q2.next())
             {
                 int trackID=q2.getInt("trackID");
                 System.out.println(trackID);
                 trackIDs.add(trackID);
             }
             for(int i=0; i<trackIDs.size(); i++)
             {
                 ResultSet q3=stmt.executeQuery("SELECT * from track WHERE trackID="+trackIDs.get(i)+";");
                 while(q3.next())
                 {
                      song=q3.getString("trackName"); /// get the song name
                      length=q3.getString("trackLength"); //get the song length
                      path=q3.getString("trackPath"); //get the song path
                     String artist=null;
                     String genre=null;
                     artistID=q3.getInt("trackArtist");
                     genreID=q3.getInt("trackGenre");
                   
                     
                     
                 
                 }
                  ResultSet q4 =stmt.executeQuery("SELECT artistName from artist WHERE artistID="+artistID+";");
                 String artist=q4.getString("artistName");  //get the artist name
                    
                 ResultSet q5 =stmt.executeQuery("SELECT genreName from genre WHERE genreID="+genreID+";");
                 String genre=q4.getString("genreName"); // get the genre name
                 
                  data.add(new Track(song, artist, genre, length, path));

                    nameColumn.setCellValueFactory(new PropertyValueFactory<>("trackName"));
                    artistColumn.setCellValueFactory(new PropertyValueFactory<>("trackArtist"));
                    lengthColumn.setCellValueFactory(new PropertyValueFactory<>("trackLength"));
                    genreColumn.setCellValueFactory(new PropertyValueFactory<>("trackGenre"));
                     tableView.setItems(data);  

                 
                 
                 
                 
                 
                 q1.close();
                 stmt.close();
                 c.close();
                 
                 
                 
            }
                    
             System.out.println(trackIDs);
            }catch(Exception e){}
            
        
    
    }
    
    
    public void renamePlaylist()
    {
        
        String newName=newname.getText();
        System.out.println(name);
        System.out.println(newName);
        
            Connection c=null;
            Statement stmt=null;
            try
            {
                Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:plookifyDB.sqlite");//connection to db, (db should be located in src)
            c.setAutoCommit(false);
            stmt=c.createStatement();  
            ResultSet u1=stmt.executeQuery("SELECT playlistID from playlist WHERE playlistName = '" + name +"';");
            int playID=u1.getInt("playlistID");
                System.out.println(playID);
                
                
                
           String sql =("UPDATE playlist" + " SET playlistName = '"+ newName +"' WHERE playlistID = " + playID +";");
            stmt.executeUpdate(sql); 
            c.commit();
            playlist.setText(newName);
            name=newName;
           refreshScreen();
            }catch(Exception e)
            {
                System.exit(0);
            }

        
    }
    
     @FXML public void refreshScreen() throws IOException {
            Plookify.playlist();
        }
    
    
   
}


