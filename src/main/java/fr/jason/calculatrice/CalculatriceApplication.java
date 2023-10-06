package fr.jason.calculatrice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatriceApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalculatriceApplication.class.getResource("calculatrice.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 343, 391);
        stage.setResizable(false);
        stage.setTitle("Calculatrice");
        scene.getStylesheets().add(getClass().getResource("calculetteDesign.CSS").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}