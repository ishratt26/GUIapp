/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.*;

/**
 * FXML Controller class
 *
 * @author gabrieluliano
 */
public class RadioGUIController implements Initializable {

    public static void main( String args[] )
    {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:plookifyDB.sqlite");//connection to db, (db should be located in src)
      c.setAutoCommit(false);
      System.out.println("Here: " + c.getCatalog());
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String a = "Adele";
      ResultSet rs = stmt.executeQuery( "select artistID from artist where artistName ='Adele';");//(as if it's typing into terminal)
      while ( rs.next() ) {
      
            //resultset displays each entry from selected table row by row
        /*  int aID = rs.getInt("artistID");
          ResultSet rd = stmt.executeQuery("select trackGenre from track where trackArtist = aID;");
            while(rd.next()){
                */
            
            //}
                  
        /* int id = rs.getInt("trackID"); //stores db column values into 
         String  name = rs.getString("trackName");
         int album  = rs.getInt("trackLength");
         int  artist = rs.getInt("trackArtist");
         int genre = rs.getInt("trackGenre");
         System.out.println( "ID = " + id );
         System.out.println( "NAME = " + name );
         System.out.println( "ALBUM = " + album );
         System.out.println( "ARTIST = " + artist );
         System.out.println( "GENRE = " + genre );
         System.out.println();
          */
      }
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Operation done successfully");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
