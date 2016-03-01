/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.gui;

import common.Database;
import static common.gui.SearchController.trackIDs;
import static common.gui.SearchController.trackNames;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author tunazzinaIshrat
 */
public class AddSongsController implements Initializable {

    @FXML private Button addSong, removeSong, addAll;
  
    
    public static ObservableList<String> nowPlayingList, removeList, oldList;
    public static ArrayList<String> list1 = new ArrayList<String>();
    public static ArrayList<String> totList = new ArrayList<String>();
    public static ArrayList<String> pathToList = new ArrayList<String>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //list1 = new ArrayList<String>();
    }    
    
    @FXML
    public void addSong(){
        nowPlayingList = SearchController.selected.getSelectionModel().getSelectedItems();
        for(String song : nowPlayingList){
            if(!list1.contains(song)){
                list1.add(song);
            }
        }
        if(!list1.isEmpty()){
            updateListAdding();
        }
        
        ObservableList<String> tracks = FXCollections.observableArrayList(totList);
        SearchController.tracksList.setItems(tracks);
         
        
    }
    
    @FXML
    public void removeSong(){
       removeList= SearchController.tracksList.getSelectionModel().getSelectedItems();
       for(int i=0; i<removeList.size(); i++){
           String song = removeList.get(i);
            if(list1.contains(song)){
                list1.remove(song);
                if(!pathToList.isEmpty()){
                   if(pathToList.contains(song)){
                    pathToList.remove(i);
                   }
                }
            }
        }
       totList = list1;
       ObservableList<String> tracks = FXCollections.observableArrayList(totList);
       SearchController.tracksList.setItems(tracks);
       
       
    }
    
    @FXML
    public void addAll(){
        ObservableList<String> items = SearchController.selected.getItems();
        for(String song : items){
            if(!list1.contains(song)){
                list1.add(song);
            }
        }
        if(!list1.isEmpty()){
            updateListAdding();
        }
        
        ObservableList<String> tracks = FXCollections.observableArrayList(totList);
        SearchController.tracksList.setItems(tracks);
        
       
        
    }
    
    public void updateListAdding(){
        for(int i=0; i<list1.size(); i++){
            String song = list1.get(i);
            if(!totList.contains(song)){
                totList.add(song);
                setPathFromDatabase(song);
            }
        }
    }
    
    public void setPathFromDatabase(String text){

        //DATABASE CONNECTION
        Database db = new Database();
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            //Class.forName("org.sqlite.JDBC");
            c = db.getConnection();//connection to db, (db should be located in src)
            c.setAutoCommit(false);
            stmt = c.prepareStatement("select trackPath from track where trackName LIKE ?");
            String trackName = text;
            stmt.setString(1, trackName);
            //ADD TRACKS
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               // String track = rs.getString("trackName");
                String path = rs.getString("trackPath");              
                pathToList.add(path);
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
