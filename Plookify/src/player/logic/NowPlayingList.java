/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.logic;

import java.util.ArrayList;

/**
 *
 * @author tunazzinaIshrat
 */
public class NowPlayingList {
    
    public static ArrayList<Track> trackList;
    public static ArrayList<String> paths;
    
    public NowPlayingList(){
        trackList = AddSongsController.totList;
        //paths = AddSongsController.pathToList;
    
    }
    
    
    
    
    
}
