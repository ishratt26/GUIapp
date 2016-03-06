/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account.gui.register;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Alen
 */
public class PaymentScene {
    
    private Scene scene;

    public PaymentScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("PaymentScene.fxml"));
            scene = new Scene(root, 600, 700);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene() {
        return scene;
    }
}
