package account.gui.register.payment.gui;

import java.util.ArrayList;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PaymentSceneController {
    
    @FXML private TextField cardNumberTextField, cardHolderName, cvcTextField;
    @FXML private ChoiceBox monthChoiceBox, yearChoiceBox;
    
    /*public PaymentSceneController() {
        monthChoiceBox.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int maxYear = currentYear + 20;
        ArrayList<Integer> yearRange = new ArrayList<Integer>();
        for (int i = currentYear; i < maxYear; i++)
            yearRange.add(i);
        yearChoiceBox.setItems(FXCollections.observableArrayList(yearRange));
    }*/
    
    @FXML protected void payNowButton() {
        
    }
    
}
