/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.gui;

import common.Database;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import player.logic.FindTrackPath;
import player.logic.Track;

/**
 * FXML Controller class
 *
 * @author tunazzinaIshrat
 */
public class TrackListController implements Initializable {

    
    @FXML 
    ListView listview ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showtracks();
        } catch (IOException ex) {
            Logger.getLogger(TrackListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    public void showtracks() throws IOException
   {
        ObservableList<String> tracks = FXCollections.observableArrayList();
        ListView<String> selected = new ListView<>();

        listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               /* FindTrackPath f = new FindTrackPath(newValue);
                Track track = f.getTrack();
                String path = track.getTrackPath();
                Media media = new Media(new File(path).toURI().toString());
                MediaPlayer player = new MediaPlayer(media);*/
                
            }
        });

        //DATABASE CONNECTION
        Database db = new Database();
        Connection c = null;
        Statement stmt = null;
        try {
            //Class.forName("org.sqlite.JDBC");
            c = db.getConnection();//connection to db, (db should be located in src)
            c.setAutoCommit(false);
            stmt = c.createStatement();
            //ADD TRACKS
            ResultSet q1 = stmt.executeQuery("select trackName from track;");//(as if it's typing into terminal)
            while (q1.next()) {
                String track = q1.getString("trackName");
                tracks.addAll(track);
                listview.setItems(tracks);
            }
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
}
