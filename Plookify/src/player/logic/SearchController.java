/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.logic;

import common.CurrentUser;
import common.gui.GUIController;
import common.Database;
import common.gui.HomeScreenController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static common.gui.HomeScreenController.list1;
import static common.gui.HomeScreenController.totList;
import java.sql.Statement;

/**
 * FXML Controller class
 *
 * @author tunazzinaIshrat
 */
public class SearchController implements Initializable {

    
    @FXML TableView<Track> searchResult;
    @FXML TableView<Track> nowPlaying;
    
    @FXML private TableColumn<Track, String> nameColumn;
    @FXML private TableColumn<Track, String> artistColumn;
    @FXML private TableColumn<Track, String> genreColumn;
    @FXML private TableColumn<Track, Integer> lengthColumn;

    @FXML
    private Label result;
    @FXML
    private ContextMenu options;
    @FXML
    private MenuItem option1;
    @FXML
    private MenuItem option2;
    
    public static TableView<Track> selectedList;
    public ObservableList<Track> nowPlayingList;
    public static ArrayList<Integer> trackIDs = new ArrayList<Integer>();
    
    public static Stage stage;
    public ArrayList<Track> allTracks;
    public static ArrayList<String> trackNames;
    public static ArrayList<String> trackArtist;
    public static ArrayList<String> trackGenre;
    public static ArrayList<String> trackPaths;
    public static ArrayList<Integer> trackLength;
    
    public static int userID;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userID = 1;
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
            selectedList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
                int ID = rs.getInt("trackID");
                String track = rs.getString("trackName");
                String artist = rs.getString("artistName");
                String genre = rs.getString("genreName");
                String length = rs.getString("trackLength");
                String path = rs.getString("trackPath");
                
                tracks.add(new Track(ID,track,artist,genre,length,path));
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
                int ID = rs.getInt("trackID");
                String track = rs.getString("trackName");
                String artist = rs.getString("artistName");
                String genre = rs.getString("genreName");
                String length = rs.getString("trackLength");
                String path = rs.getString("trackPath");
                
                tracks.add(new Track(ID,track,artist,genre,length,path));
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
            c = db.getConnection();
            c.setAutoCommit(false);
            stmt = c.prepareStatement("select * from track t1 left join artist a1 on t1.trackArtist = a1.artistID left join genre g1 on t1.trackGenre = g1.genreID where genreName LIKE ?");
            String trackName = text+"%";
            stmt.setString(1, trackName);
            //ADD TRACKS
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("trackID");
                String track = rs.getString("trackName");
                String artist = rs.getString("artistName");
                String genre = rs.getString("genreName");
                String length = rs.getString("trackLength");
                String path = rs.getString("trackPath");
                
                tracks.add(new Track(ID,track,artist,genre,length,path));
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
    
   @FXML
    public void addSong(){
        int trackID = 0;
        nowPlayingList = selectedList.getSelectionModel().getSelectedItems();
        for(Track track : nowPlayingList){
            if(!totList.contains(track)){
                totList.add(track);
                trackID = track.getTrackID();
               // addToDb(trackID);
            }
        }
        
        
        ObservableList<Track> tracks = FXCollections.observableArrayList(totList);
        HomeScreenController.nowPlayingList.setItems(tracks);
        selectedList.getSelectionModel().clearSelection();
         
        
    }
    
    @FXML
    public void addAll(){
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ObservableList<Track> items = selectedList.getItems();
        for(Track song : items){
            if(!totList.contains(song)){
                totList.add(song);
                //ids.add(song.getTrackID());
            }
        }
        
       /* for(int i=0; i<ids.size(); i++){
            addToDb(ids.get(i));
        }*/
        
        ObservableList<Track> tracks = FXCollections.observableArrayList(totList);
        HomeScreenController.nowPlayingList.setItems(tracks);
        selectedList.getSelectionModel().clearSelection();
       
        
    }
    
    public void updateListAdding(){
        for(int i=0; i<list1.size(); i++){
            Track song = list1.get(i);
            if(!totList.contains(song)){
                totList.add(song);
            }
        }
    }
    
    public static void addToDb(int trackID){
        Database db = new Database();
        Connection c = null;
        Statement stmt = null;
        try{
            c  = db.getConnection();
           // stmt.setQueryTimeout(10);
            //trying to see
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String query = "INSERT INTO nowPlayingPlaylist (accountID,trackID) VALUES ('" + CurrentUser.customerID + "', '" + trackID + "')"; 
            stmt.executeUpdate(query);
            stmt.close();
            c.commit();
            c.close();
        }
        catch(SQLException e ){
            System.out.println(e.getMessage());
        }
    
    }

}
