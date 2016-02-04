package social;



/**
 *
 * @author Muhammad-Rayhaan
 */
import java.sql.*;

public class testDB {
     public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:songDB.sqlite");//connection to db, (db should be located in src)
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "select * from track;" );//(as if it's typing into terminal)
      while ( rs.next() ) { //resultset displays each entry from selected table row by row
         String id = rs.getString("songID"); //stores db column values into 
         String  name = rs.getString("trackName");
         String album  = rs.getString("albumName");
         String  artist = rs.getString("artistName");
         String genre = rs.getString("Genre");
         System.out.println( "ID = " + id );
         System.out.println( "NAME = " + name );
         System.out.println( "ALBUM = " + album );
         System.out.println( "ARTIST = " + artist );
         System.out.println( "SALARY = " + genre );
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