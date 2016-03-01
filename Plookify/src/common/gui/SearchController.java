/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.gui;

import common.Database;
import static common.gui.AddSongsController.totList;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/**
 * FXML Controller class
 *
 * @author tunazzinaIshrat
 */
public class SearchController implements Initializable {

    @FXML
    public ListView searchList;
    public ListView trackQueue;
    @FXML
    private Label result;
    
    public static ListView selected;
    public static ListView tracksList;
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
            
            selected = searchList;
            tracksList = trackQueue;
            if(!AddSongsController.totList.isEmpty())
            {
                ObservableList<String> oldList = FXCollections.observableArrayList(totList);
                tracksList.setItems(oldList);
                trackQueue = tracksList;
            }
           tracksList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        } catch (IOException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void showtracksByName() throws IOException
   {
        
        ObservableList<String> tracks = FXCollections.observableArrayList();


        searchList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        searchList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                /*FindTrackPath f = new FindTrackPath(newValue);
                Track track = f.getTrack();
                String path = track.getTrackPath();
                Media media = new Media(new File(path).toURI().toString());
                MediaPlayer player = new MediaPlayer(media);*/
                
            }
        });
        
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
            stmt = c.prepareStatement("select trackName from track where trackName LIKE ?");
            String trackName = "%"+text+"%";
            stmt.setString(1, trackName);
            //ADD TRACKS
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String track = rs.getString("trackName");
                tracks.addAll(track);
                searchList.setItems(tracks);
            }
            result.setText("Results for \""+text+"\"");
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void showtracksByArtist() throws IOException
   {
       int artistID = 0;
        ObservableList<String> tracks = FXCollections.observableArrayList();
        

        searchList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        searchList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                /*FindTrackPath f = new FindTrackPath(newValue);
                Track track = f.getTrack();
                String path = track.getTrackPath();
                Media media = new Media(new File(path).toURI().toString());
                MediaPlayer player = new MediaPlayer(media);*/
                
            }
        });
        
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
            stmt = c.prepareStatement("select artistID from artist where artistName LIKE ?");
            String artistName = text;
            stmt.setString(1, artistName);
            //ADD TRACKS
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                artistID = rs.getInt("artistID");
                
            }
            
            stmt = c.prepareStatement("select trackName from track where trackArtist LIKE ?");
            stmt.setInt(1, artistID);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                String track = rs.getString("trackName");
                tracks.addAll(track);
                searchList.setItems(tracks);
            }
            result.setText("Results for \""+text+"\"");
           
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
       int genreID = 0;
        ObservableList<String> tracks = FXCollections.observableArrayList();
        

        searchList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        searchList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                /*FindTrackPath f = new FindTrackPath(newValue);
                Track track = f.getTrack();
                String path = track.getTrackPath();
                Media media = new Media(new File(path).toURI().toString());
                MediaPlayer player = new MediaPlayer(media);*/
                
            }
        });
        
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
            stmt = c.prepareStatement("select genreID from genre where genreName LIKE ?");
            String genreName = text;
            stmt.setString(1, genreName);
            //ADD TRACKS
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                genreID = rs.getInt("genreID");
                
            }
            
            stmt = c.prepareStatement("select trackName from track where trackGenre LIKE ?");
            stmt.setInt(1, genreID);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                String track = rs.getString("trackName");
                tracks.addAll(track);
                searchList.setItems(tracks);
            }
            result.setText("Results for \""+text+"\"");
           
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
}
