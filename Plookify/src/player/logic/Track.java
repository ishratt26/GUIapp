/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.logic;



/**
 *
 * @author tunazzinaIshrat
 */
public class Track {
    private int trackID;
    private String trackName;
    private String trackArtist;
    private String trackGenre;
    private int trackLength;
    private String trackPath;
    
    public Track(String trackName, String trackArtist, String trackGenre, int trackLength, String trackPath){
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

    public String getTrackArtist() {
        return trackArtist;
    }

    public String getTrackGenre() {
        return trackGenre;
    }

    public int getTrackLength() {
        return trackLength;
    }

    public String getTrackPath() {
        return trackPath;
    }
    
}
