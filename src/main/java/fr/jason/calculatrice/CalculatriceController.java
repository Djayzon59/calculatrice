package fr.jason.calculatrice;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Calculatrice;

public class CalculatriceController {

    Calculatrice calculatrice;
    @FXML
    private Label affichageLabel;


    @FXML
    private void initialize(){
        calculatrice = new Calculatrice("0");
    }

    @FXML
    protected void onButtonClick(Event event) {
            Button button = (Button) event.getSource();
            calculatrice.touche(button.getText());
            affichageLabel.setText(calculatrice.toString());
    }
}