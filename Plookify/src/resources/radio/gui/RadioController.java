/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
/**
 * FXML Controller class
 *
 * @author gabrieluliano
 */
public class RadioController {

   @FXML private TextField textField;
   @FXML ListView<String> listView;
   @FXML ListView<String> songView;
   ArrayList<String> PLAYLIST = new ArrayList<String>();
   public RadioController(){
   }
   
    @FXML protected void searchButton() {
        String artist = textField.getText(); //Search text field
        ObservableList<String> artistData = FXCollections.observableArrayList(); //List for storing artists names
        Connection c = null;
        Statement stmt = null;    
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:plookifyDB.sqlite"); //connecting to database
            c.setAutoCommit(false);
            System.out.println("Here: " + c.getCatalog());
            System.out.println("Opened database successfully"); //verify database opened succesfully 
            stmt = c.createStatement();
            
            ResultSet q1 = stmt.executeQuery( "select artistID from artist where artistName ='"+artist+"';");//get artistID from artist table
                while ( q1.next() ){
                    int id = q1.getInt("artistID");
                    ResultSet q2 = stmt.executeQuery( "select trackGenre from track where trackArtist ="+id+";"); 
                    int gen = q2.getInt("trackGenre");
                    ResultSet q2_1 = stmt.executeQuery( "select genreName from genre where genreID ="+gen+";");
                    while(q2.next()){
                        String genre = q2_1.getString("genreName");           
                    
                }
            ResultSet q3 = stmt.executeQuery( "select trackArtist from track where trackGenre ="+gen+";");
                ArrayList<Integer> artistID = new ArrayList<Integer>();
               
                while(q3.next()){
                    int artistNumber = q3.getInt("trackArtist");
                    if(!artistID.contains(artistNumber))
                        artistID.add(artistNumber);
                }
                
                System.out.println(artistID);
                
            int counter=0;
            while(counter<4){
                ResultSet q4 = stmt.executeQuery("select artistName from artist where artistID= "+artistID.get(counter)+";");
                    while(q4.next()){
                    String artistName = q4.getString("artistName");
                    System.out.println(artistName);
                    counter++;
                    artistData.addAll(artistName);
                    }
            }                  
            }
            listView.setItems(artistData);        
        }
     
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0); 
        }
    }
    
    @FXML protected void artistButton() {
        ObservableList<String> artistData;
        artistData = listView.getSelectionModel().getSelectedItems();
        
        ObservableList<String> songData = FXCollections.observableArrayList();
        
        
       
         
        Connection c = null;
        Statement stmt = null;    
        
       
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:plookifyDB.sqlite");
            c.setAutoCommit(false);
            System.out.println("Here: " + c.getCatalog());
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String aName = artistData.get(0);
            ResultSet q1 = stmt.executeQuery( "select artistID from artist where artistName ='"+aName+"';");//(as if it's typing into terminal)
            while ( q1.next() ) 
            {   
                int id = q1.getInt("artistID");
                System.out.println(id);
                ResultSet q2 = stmt.executeQuery( "select trackGenre from track where trackArtist ="+id+";"); 
                int gen = q2.getInt("trackGenre");
                ResultSet q2_1 = stmt.executeQuery( "select genreName from genre where genreID ="+gen+";");
                while(q2.next()){
                    String genre = q2_1.getString("genreName"); 
                }
          
                ResultSet q3 = stmt.executeQuery( "select trackName from track where trackGenre ="+gen+";");
                while(q3.next()){
                    String traks = q3.getString("trackName");
                    System.out.println(traks);
                    
                    songData.addAll(traks); 
                    PLAYLIST.add(traks);
                }       
            } 
            System.out.println(songData);
            songView.setItems(songData);
        }
        
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0); 
        }       
    }
    
    @FXML protected ArrayList<String> playlistButton() {
        return PLAYLIST;
    }
}

