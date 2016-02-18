/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlist;

/**
 *
 * @author monicadzhaleva
 */


public class playlist {

    private int playlistID;
    private String playlistName;
    
    public int getID()
    {
        return playlistID;
    }
    
    public void setID(int pID)
    {
        playlistID=pID;
    }
    
    public String getName()
    {
        return playlistName;
    }
    
    public void setName(String pName)
    {
        playlistName=pName;
       
    }
}

 
