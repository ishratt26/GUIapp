/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.gui;

import common.Database;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/**
 * FXML Controller class
 *
 * @author tunazzinaIshrat
 */
public class SearchController implements Initializable {

    @FXML
    private ListView searchList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showtracks();
        } catch (IOException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void showtracks() throws IOException
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
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
}
