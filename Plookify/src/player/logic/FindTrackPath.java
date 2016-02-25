/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.logic;

import common.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author tunazzinaIshrat
 */
public class FindTrackPath {
    String trackName;
    String trackPath;
    
    public FindTrackPath(String trackName){
        this.trackName = trackName;
        trackPath = null;
        System.out.println(trackName);
        
    }
    
    public Track getTrack(){
        Track track = null;
        Database db = new Database();
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            //Class.forName("org.sqlite.JDBC");
            c = db.getConnection();//connection to db, (db should be located in src)
            c.setAutoCommit(false);
            stmt = c.prepareStatement("select * from track where trackName =?");
            stmt.setString(1, trackName);
            //ADD TRACKS
            ResultSet rs = stmt.executeQuery();//(as if it's typing into terminal)
            while (rs.next()) {
                track = new Track(rs.getInt("trackID"), rs.getString("trackName"), rs.getInt("trackArtist"), rs.getInt("trackGenre"), rs.getInt("trackLength"), rs.getString("trackPath"));
                //need to pass the path to a mediaplayer object in trackPlayerController
            }
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return track;
    
    }
    
    
    }
    
   
