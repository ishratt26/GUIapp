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
import java.util.HashSet;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.collections.FXCollections;
/**
 * FXML Controller class
 *
 * @author gabrieluliano
 */
public class RadioController {

   @FXML private TextField textField;
   @FXML ListView<String> listView;
  // @FXML ListView<String> artistView;
 //  @FXML ListView<String> artistList;
 //  @FXML ListView<String> genreList;
   
   
   
   
   public RadioController(){
   
   }
   
    @FXML protected void searchButton() {
        String artist = textField.getText();
         
        Connection c = null;
        Statement stmt = null;    
        
        
        
      //  ObservableList<String> nameData = FXCollections.observableArrayList();
          ObservableList<String> artistData = FXCollections.observableArrayList();
     //   ObservableList<String> genreData = FXCollections.observableArrayList();
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:plookifyDB.sqlite");
            c.setAutoCommit(false);
            System.out.println("Here: " + c.getCatalog());
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            
            ResultSet q1 = stmt.executeQuery( "select artistID from artist where artistName ='"+artist+"';");//(as if it's typing into terminal)
            while ( q1.next() ) 
            {
                int id = q1.getInt("artistID");
                ResultSet q2 = stmt.executeQuery( "select trackGenre from track where trackArtist ="+id+";"); 
                int gen = q2.getInt("trackGenre");
                ResultSet q2_1 = stmt.executeQuery( "select genreName from genre where genreID ="+gen+";");
                while(q2.next())
                {
                    String genre = q2_1.getString("genreName");
                 //   for(int i =0; i <10;i++){
                //    genreData.addAll(genre);}
                }
          
                ResultSet q3 = stmt.executeQuery( "select trackArtist from track where trackGenre ="+gen+";");
                ArrayList<Integer> artistID = new ArrayList<Integer>();
               
                while(q3.next())
                {
                 int artistNumber = q3.getInt("trackArtist");
                 if(!artistID.contains(artistNumber)){
                     artistID.add(artistNumber);
                     
                 }
                // artistID.add(artistNumber);
                
                 //System.out.println(artistid);
                    /*
                    int artists = q3.getInt("trackArtist");
                    ResultSet q4 = stmt.executeQuery("select artistName from artist where artistID= "+artists+";");
                    while(q4.next()){
                    String nam = q4.getString("artistName");
                    System.out.println(nam);
                    }
                    //nameData.addAll(traks);    */
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
               /* ResultSet q3_1 = stmt.executeQuery( "select trackArtist from track where trackGenre ="+gen+";");
                while(q3_1.next()){
                    int artistID = q3_1.getInt("trackArtist");
                    ResultSet q3_2 = stmt.executeQuery( "select artistName from artist where artistID ="+artistID+";");
                    String aName = q3_2.getString("artistName");
                    artistData.addAll(aName);
                }*/
                
            } 
            listView.setItems(artistData);
           // artistView.setItems(artistData);
           // genreList.setItems(genreData); 
            
        }
      
        
        
        
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0); 
        }
    }
}

