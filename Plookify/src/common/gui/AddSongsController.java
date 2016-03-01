/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.gui;

import java.net.URL;
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
       for(String song : removeList){
            if(list1.contains(song)){
                list1.remove(song);
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
        for(String song : list1){
            if(!totList.contains(song)){
                totList.add(song);
            }
        }
    }
    
    
    
}
