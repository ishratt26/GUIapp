/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.ArrayList;

/**
 *
 * @author tunazzinaIshrat
 */
public class FindTracks {
    
    public static void main(String[] args) {
        Database db = new Database();
        
        ArrayList<Track> tracks = db.getTrack("SELECT * FROM track ");
        for (int i = 0; i < tracks.size(); i++)
            System.out.println(tracks.get(i).getTrackID() + " " + tracks.get(i).getTrackName());
    }
    
}
