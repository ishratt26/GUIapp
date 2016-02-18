/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabrieluliano
 */


public class RadioGUIController extends Application implements Initializable {
    
    @FXML
    TextField radioSearch;
    
    @FXML
    public void importSearch(ActionEvent e){
    String a = radioSearch.getText();
    rSearch(a);
    //return a;
    }
//    
    public void rSearch(String name){
  
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:plookifyDB.sqlite");//connection to db, (db should be located in src)
      c.setAutoCommit(false);
      System.out.println("Here: " + c.getCatalog());
      System.out.println("Opened database successfully");
      
      stmt = c.createStatement();
      String a ="A";
      ResultSet q1 = stmt.executeQuery( "select artistID from artist where artistName ='"+name+"';");//(as if it's typing into terminal)
      while ( q1.next() ) {
          int id = q1.getInt("artistID");
          ResultSet q2 = stmt.executeQuery( "select trackGenre from track where trackArtist ="+id+";");
          int gen = q2.getInt("trackGenre");
          
            ResultSet q3 = stmt.executeQuery( "select trackName from track where trackGenre ="+gen+";");
            while(q3.next()){
                String traks = q3.getString("trackName");
                System.out.println(traks);
            }
      }
      q1.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    
    }
    
    
    
    public static void main( String args[] ) throws Exception
    {
        launch(args);
        Application.launch(RadioGUIController.class, (java.lang.String[])null);
        //new RadioGUIController().launch();
        
    System.out.println("Operation done successfully");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("radioGUI.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
    
}
