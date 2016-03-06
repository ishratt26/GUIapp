/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.logic;

import common.gui.GUIController;
import common.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.cell.PropertyValueFactory;
import static player.logic.AddSongsController.totList;

/**
 * FXML Controller class
 *
 * @author tunazzinaIshrat
 */
public class SearchController implements Initializable {

    
    @FXML TableView<Track> searchResult;
    @FXML TableView<Track> nowPlaying;
    
    @FXML private TableColumn<Track, String> nameColumn, NameColumn;
    @FXML private TableColumn<Track, String> artistColumn, ArtistColumn;
    @FXML private TableColumn<Track, String> genreColumn, GenreColumn;
    @FXML private TableColumn<Track, Integer> lengthColumn, LengthColumn;
    
    
    
    @FXML
    private Label result;
    
    public static TableView<Track> selectedList;
    public static TableView<Track> nowPlayingList;
    public static ArrayList<Integer> trackIDs = new ArrayList<Integer>();
    
    
    public ArrayList<Track> allTracks;
    public static ArrayList<String> trackNames;
    public static ArrayList<String> trackArtist;
    public static ArrayList<String> trackGenre;
    public static ArrayList<String> trackPaths;
    public static ArrayList<Integer> trackLength;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String searchBy = GUIController.searchBy;
        try {
            // TODO
            if(searchBy.equals("name")){
                showtracksByName();
            } 
            else if(searchBy.equals("artist")) {
                showtracksByArtist();
            }
            else if(searchBy.equals("genre")){
                showtracksByGenre();  
            }
            else{
                showtracksByName();
            }
        
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("trackName"));
            artistColumn.setCellValueFactory(new PropertyValueFactory<>("trackArtist"));
            lengthColumn.setCellValueFactory(new PropertyValueFactory<>("trackLength"));
            genreColumn.setCellValueFactory(new PropertyValueFactory<>("trackGenre"));
            
            selectedList = searchResult;
            
            NameColumn.setCellValueFactory(new PropertyValueFactory<>("trackName"));
            ArtistColumn.setCellValueFactory(new PropertyValueFactory<>("trackArtist"));
            LengthColumn.setCellValueFactory(new PropertyValueFactory<>("trackLength"));
            GenreColumn.setCellValueFactory(new PropertyValueFactory<>("trackGenre"));
           
            nowPlayingList = nowPlaying;
            
            if(!AddSongsController.totList.isEmpty())
            {
                ObservableList<Track> oldList = FXCollections.observableArrayList(totList);
                nowPlayingList.setItems(oldList);
                nowPlaying = nowPlayingList;
            }
        } catch (IOException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void showtracksByName() throws IOException
   {
        ArrayList<Track> tracks = new ArrayList<Track>();
        String text;
        text = GUIController.text;
        //DATABASE CONNECTION
        Database db = new Database();
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            //Class.forName("org.sqlite.JDBC");
            c = db.getConnection();//connection to db, (db should be located in src)
            c.setAutoCommit(false);
            stmt = c.prepareStatement("select * from track t1 left join artist a1 on t1.trackArtist = a1.artistID left join genre g1 on t1.trackGenre = g1.genreID where trackName LIKE ?");
            String trackName = text+"%";
            stmt.setString(1, trackName);
            //ADD TRACKS
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String track = rs.getString("trackName");
                String artist = rs.getString("artistName");
                String genre = rs.getString("genreName");
                int length = rs.getInt("trackLength");
                String path = rs.getString("trackPath");
                
                tracks.add(new Track(track,artist,genre,length,path));
               // searchList.setItems(tracks);
            }
            
            result.setText("Results for \""+text+"\"");
            searchResult.setItems(getTrack(tracks));
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public ObservableList<Track> getTrack(ArrayList<Track> songs) {
        ObservableList<Track> tracks = FXCollections.observableArrayList();
        for (int i = 0; i < songs.size(); i++) {
            tracks.add(songs.get(i));
        }
        return tracks;
    }
    
    public void showtracksByArtist() throws IOException
   {
        ArrayList<Track> tracks = new ArrayList<Track>();
        String text;
        text = GUIController.text;
        //DATABASE CONNECTION
        Database db = new Database();
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            //Class.forName("org.sqlite.JDBC");
            c = db.getConnection();//connection to db, (db should be located in src)
            c.setAutoCommit(false);
            stmt = c.prepareStatement("select * from track t1 left join artist a1 on t1.trackArtist = a1.artistID left join genre g1 on t1.trackGenre = g1.genreID where artistName LIKE ?");
            String trackName = text+"%";
            stmt.setString(1, trackName);
            //ADD TRACKS
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String track = rs.getString("trackName");
                String artist = rs.getString("artistName");
                String genre = rs.getString("genreName");
                int length = rs.getInt("trackLength");
                String path = rs.getString("trackPath");
                
                tracks.add(new Track(track,artist,genre,length,path));
               // searchList.setItems(tracks);
            }
            
            result.setText("Results for \""+text+"\"");
            searchResult.setItems(getTrack(tracks));
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void showtracksByGenre() throws IOException
   {
        ArrayList<Track> tracks = new ArrayList<Track>();
        String text;
        text = GUIController.text;
        //DATABASE CONNECTION
        Database db = new Database();
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            //Class.forName("org.sqlite.JDBC");
            c = db.getConnection();//connection to db, (db should be located in src)
            c.setAutoCommit(false);
            stmt = c.prepareStatement("select * from track t1 left join artist a1 on t1.trackArtist = a1.artistID left join genre g1 on t1.trackGenre = g1.genreID where genreName LIKE ?");
            String trackName = text+"%";
            stmt.setString(1, trackName);
            //ADD TRACKS
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String track = rs.getString("trackName");
                String artist = rs.getString("artistName");
                String genre = rs.getString("genreName");
                int length = rs.getInt("trackLength");
                String path = rs.getString("trackPath");
                
                tracks.add(new Track(track,artist,genre,length,path));
               // searchList.setItems(tracks);
            }
            
            result.setText("Results for \""+text+"\"");
            searchResult.setItems(getTrack(tracks));
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
   
   }
    
  
}
