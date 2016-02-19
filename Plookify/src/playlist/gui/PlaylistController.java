/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlist.gui;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import javafx.stage.Stage;
import playlist.gui.addplaylist.AddPlaylistScene;

/**
 * FXML Controller class
 *
 * @author monicadzhaleva
 */
public class PlaylistController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //MAIN METHOD
        @FXML private ListView<String> friendsList;
        @FXML private ListView privatelist;

    public void run() {
        
        ObservableList<String> data=FXCollections.observableArrayList();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/Users/monicadzhaleva/NetBeansProjects/SE21/Plookify/SE21/Plookify/src/resources/plookifyDB.sqlite");//connection to db, (db should be located in src)
            c.setAutoCommit(false);
            System.out.println("Here: " + c.getCatalog());
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
                
                 
              ///FIND TRACK 
            ResultSet q1 = stmt.executeQuery("select playlistName from playlist;");//(as if it's typing into terminal)

            while (q1.next()) {
                String playlists = q1.getString("playlistName");
                System.out.println(playlists);
                data.addAll(playlists);
            }
            friendsList.setItems(data);
            q1.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
    
    

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }



    /**
     * Search in player
     */
    public void searchPlayer() {
        System.out.println("Search works");

    }

    // MENU 
    public void Home() {

    }

    public void Playlist() {

    }

    public void Upgrade() {

    }
    //ADD PLAYLIST
        @FXML protected void AddPlaylistScene() {
        MainStage.loadScene(new AddPlaylistScene().getScene(), "AddPlaylist");
    }
    
  
}
