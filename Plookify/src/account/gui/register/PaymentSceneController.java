package account.gui.register;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaymentSceneController {
    
    @FXML private TextField cardNumberTextField, cardHolderName, cvcTextField;
    @FXML private ChoiceBox monthChoiceBox, yearChoiceBox;
    @FXML private Text errorText;
    
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
                
                //SO CAN ENTER NUMBERS
                cardNumberTextField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        try {
                            if (newValue.matches("\\d*")) {
                                int value = Integer.parseInt(newValue);
                            } else {
                                cardNumberTextField.setText(oldValue);
                            }
                        } catch (NumberFormatException ex) {}
                    }
                });
                
                cvcTextField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        try {
                            if (newValue.matches("\\d*") && newValue.length() < 4) {
                                int value = Integer.parseInt(newValue);
                            } else {
                                cvcTextField.setText(oldValue);
                            }
                        } catch (NumberFormatException ex) {}
                    }
                });
            }
        });
    }
    
    @FXML protected void payNowButton() {
        String errors = errorCheck();
        if (errors.equals("")) {
            cardNumberTextField.setStyle("");
            cardHolderName.setStyle("");
            cvcTextField.setStyle("");
            monthChoiceBox.setStyle("");
            yearChoiceBox.setStyle("");
            try {
            Stage stage = (Stage) cardNumberTextField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("SuccessScene.fxml"));
            Scene scene = new Scene(root, 600, 700);
            stage.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(RegisterSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            errorText.setText("");
            cardNumberTextField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            cardHolderName.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            cvcTextField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            monthChoiceBox.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            yearChoiceBox.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            errorText.setText(errors);
        }
    }
    
    public String errorCheck() {
        String errors = "";
        
        if (cardNumberTextField.getText().equals(""))
            errors = errors + " \u2022 Card Number cannot be empty.";
        if (cardHolderName.getText().equals(""))
            errors = errors + " \u2022 Card Holder Name cannot be empty.";
        if (cvcTextField.getText().equals(""))
            errors = errors + " \u2022 CVC Code cannot be empty.";
        if (monthChoiceBox.getSelectionModel().getSelectedItem() == null)
            errors = errors + " \u2022 Please enter card expiry month.";
        if (yearChoiceBox.getSelectionModel().getSelectedItem() == null)
            errors = errors + " \u2022 Please enter card expiry month.";
        
        return errors;
    }
    
}
