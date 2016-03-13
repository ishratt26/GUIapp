/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.logic;

import java.util.Objects;



/**
 *
 * @author tunazzinaIshrat
 */
public class Track {
    private int trackID;
    private String trackName;
    private String trackArtist;
    private String trackGenre;
    private String trackLength;
    private String trackPath;
    
    public Track(int trackID, String trackName, String trackArtist, String trackGenre, String trackLength, String trackPath){
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

    public String getTrackArtist() {
        return trackArtist;
    }

    public String getTrackGenre() {
        return trackGenre;
    }

    public String getTrackLength() {
        return trackLength;
    }

    public String getTrackPath() {
        return trackPath;
    }
    
    @Override
    public boolean equals(Object object) {

        if (object != null && object instanceof Track) {
            Track track = (Track) object;
            if (trackName == null) {
                return (track.trackName == null);
            }
            else {
                return trackName.equals(track.trackName);
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.trackName);
        return hash;
    }
    
    
}
