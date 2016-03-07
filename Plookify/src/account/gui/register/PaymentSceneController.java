package account.gui.register;

import account.logic.Register;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PaymentSceneController {
    
    @FXML private TextField cardNumberTextField, cardHolderName, cvcTextField;
    @FXML private ChoiceBox monthChoiceBox, yearChoiceBox;
    
    public PaymentSceneController() {
        Platform.runLater(new Runnable() {
            public void run() {
                monthChoiceBox.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                int maxYear = currentYear + 10;
                ArrayList<Integer> yearRange = new ArrayList<Integer>();
                for (int i = currentYear; i < maxYear; i++)
                    yearRange.add(i);
                yearChoiceBox.setItems(FXCollections.observableArrayList(yearRange));
            }
        });
    }
    
    @FXML protected void payNowButton() {
        try {
            Stage stage = (Stage) cardNumberTextField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../SuccessScene.fxml"));
            Scene scene = new Scene(root, 400, 600);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(RegisterSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
