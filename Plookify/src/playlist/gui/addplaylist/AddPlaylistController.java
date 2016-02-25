/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlist.gui.addplaylist;

import common.main;
import playlist.gui.MainStage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import playlist.gui.PlaylistScene;
import playlist.playlist;

/**
 * FXML Controller class
 *
 * @author monicadzhaleva
 */
public class AddPlaylistController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
        @FXML private TextField playlistfield;
        @FXML private Button addbutton;
        @FXML private ListView listview;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //ADD A PLAYLIST
   public void addplaylist() throws IOException
   {
       
     String input=playlistfield.getText(); ///Get playlist name input
     playlist playl=new playlist(input);
     System.out.println(playl.getName()); 
     ObservableList<String> tracks=FXCollections.observableArrayList();
     ObservableList<String> data=FXCollections.observableArrayList();

    
    //DATABASE CONNECTION

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:plookifyDB.sqlite");//connection to db, (db should be located in src)
            c.setAutoCommit(false);
            System.out.println("Here: " + c.getCatalog());
            System.out.println("Opened database successfully");
            stmt = c.createStatement();

     //INSERT INTO DATABASE
             String sql = "INSERT INTO playlist(playlistName) VALUES ('"+input+"');";
             stmt.executeUpdate(sql);
                            
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
                      refreshScreen();
                        
          }
   
   
   //SHOW TRACKS
    public void showtracks() throws IOException
   {
     ObservableList<String> tracks=FXCollections.observableArrayList();
             ListView<String> selected = new ListView<>();

            listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
           
            listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        System.out.println(newValue);
    }
});

            //DATABASE CONNECTION
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:plookifyDB.sqlite");//connection to db, (db should be located in src)
            c.setAutoCommit(false);
            System.out.println("Here: " + c.getCatalog());
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
    //ADD TRACKS
               ResultSet q1 = stmt.executeQuery("select trackName from track;");//(as if it's typing into terminal)
               while (q1.next()) {
                String track = q1.getString("trackName");
                System.out.println(track);
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
        System.out.println("Operation done successfully");                        
          }
   
   
    
   @FXML public void refreshScreen() throws IOException {
            main.playlist();
        }
    
}
