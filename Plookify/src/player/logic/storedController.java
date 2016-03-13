/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.logic;

import player.gui.TrackPlayerController;

/**
 *
 * @author tunazzinaIshrat
 */
public class storedController {
    private TrackPlayerController controller;
    
    
    private storedController() {
    }
    
    public void setController(TrackPlayerController controller){
        this.controller = controller;
    }
    
    public TrackPlayerController getController(){
        return controller;
    }
    
    public static storedController getInstance() {
        return storedControllerHolder.INSTANCE;
    }
    
    private static class storedControllerHolder {

        private static final storedController INSTANCE = new storedController();
    }
}
