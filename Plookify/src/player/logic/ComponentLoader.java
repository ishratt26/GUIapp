/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import javax.swing.JOptionPane;
import player.gui.TrackPlayerController;

/**
 *
 * @author tunazzinaIshrat
 */
public class ComponentLoader {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "The application works.");
        TrackPlayerController c = new TrackPlayerController();
        
        c.play();
    }
}
