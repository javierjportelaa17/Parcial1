package co.edu.uniquindio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LinguaPlusApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LinguaPlusApp.class.getResource("/MatriculaView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 460, 680);
        stage.setTitle("Gestión LinguaPlus");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}