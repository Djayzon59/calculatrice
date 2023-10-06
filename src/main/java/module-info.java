module fr.jason.calculatrice {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.jason.calculatrice to javafx.fxml;
    exports fr.jason.calculatrice;
}