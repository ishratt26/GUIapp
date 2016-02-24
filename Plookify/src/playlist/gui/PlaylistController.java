/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlist.gui;

import account.gui.MainStage;
import account.gui.register.RegisterScene;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;
import javafx.stage.Stage;
//import playlist.gui.addplaylist.AddPlaylistScene;

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
    @FXML
    TableView tableview;
    TableColumn tablesong;
     TableColumn tableartist;
    TableColumn tablegalbum;
    TableColumn tablegenre;

    public static void main(String args[]) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SE21/Plookify/SE21/Plookify/src/resources/plookifyDB.sqlite");//connection to db, (db should be located in src)
            c.setAutoCommit(false);
            System.out.println("Here: " + c.getCatalog());
            System.out.println("Opened database successfully");
            stmt = c.createStatement();

                 
              ///FIND TRACK 
            ResultSet rstrack = stmt.executeQuery("select trackName from track;");//(as if it's typing into terminal)

            while (rstrack.next()) {
                String trackinformation = rstrack.getString("trackName");
                System.out.println(trackinformation);
            }
            rstrack.close();
            
               ///FIND GENRE 
            ResultSet rsgenre = stmt.executeQuery("select genreName from genre;");//(as if it's typing into terminal)

            while (rsgenre.next()) {
                String genreinformation = rsgenre.getString("genreName");
                System.out.println(genreinformation);
            }
            rsgenre.close();
            
            
            
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
    
    
public void artistFind()
{
     Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SE21/Plookify/SE21/Plookify/src/resources/plookifyDB.sqlite");//connection to db, (db should be located in src)
            c.setAutoCommit(false);
            System.out.println("Here: " + c.getCatalog());
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
      ///FIND ARTISTS 
            ResultSet rsartist = stmt.executeQuery("select artistName from artist;");//(as if it's typing into terminal)

            while (rsartist.next()) {
                String artistinformation = rsartist.getString("artistName");
                System.out.println(artistinformation);
            }
                        rsartist.close();
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

    public void addPlaylists() {
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
