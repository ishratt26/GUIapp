/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author tunazzinaIshrat
 */

import java.sql.*;

public class testDB {
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:../SE21/Plookify/src/resources/plookifyDB.sqlite");//connection to db, (db should be located in src)
      c.setAutoCommit(false);
      System.out.println("Here: " + c.getCatalog());
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "select * from track;" );//(as if it's typing into terminal)
      while ( rs.next() ) { //resultset displays each entry from selected table row by row
         int id = rs.getInt("trackID"); //stores db column values into 
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
}