/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author gabrieluliano
 */
public class RadioController {

   @FXML private TextField textField;

    @FXML protected void searchButton() {
        String artist = textField.getText();
         
        Connection c = null;
        Statement stmt = null;
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("/Users/gabrieluliano/Desktop/SE/NBProjects/SE21/Plookify/src/resources/plookifyDB.sqlite");
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
          
                ResultSet q3 = stmt.executeQuery( "select trackName from track where trackGenre ="+gen+";");
                while(q3.next())
                {
                    String traks = q3.getString("trackName");
                    System.out.println(traks);
                }
            } 
            
        }
        
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0); 
        }
    }
}

