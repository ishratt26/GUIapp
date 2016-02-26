/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicweather;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;

/**
 *
 * @author tunazzinaIshrat
 */
public class WeatherController implements Initializable {
    
    @FXML
    private Label location;
    @FXML
    private Label day;
    @FXML
    private Label temp;
    @FXML
    private Label advise;
    @FXML
    private Button addLocation;
    @FXML
    private Button tomorrow;
    @FXML
    private Button week;
    @FXML
    private MenuButton menu;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        //to do
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
