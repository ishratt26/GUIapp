/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.logic;

import common.*;

/**
 *
 * @author tunazzinaIshrat
 */
public class Track {
    private int trackID;
    private String trackName;
    private int trackArtist;
    private int trackGenre;
    private int trackLength;
    private String trackPath;
    
    public Track(int trackID, String trackName, int trackArtist, int trackGenre, int trackLength, String trackPath){
        this.trackID = trackID;
        this.trackName = trackName;
        this.trackArtist = trackArtist;
        this.trackGenre = trackGenre;
        this.trackLength = trackLength;
        this.trackPath = trackPath;
    }

    public int getTrackID() {
        return trackID;
    }

    public String getTrackName() {
        return trackName;
    }

    public int getTrackArtist() {
        return trackArtist;
    }

    public int getTrackGenre() {
        return trackGenre;
    }

    public int getTrackLength() {
        return trackLength;
    }

    public String getTrackPath() {
        return trackPath;
    }
    
}
