package com.mohware.mills.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.StageStyle;

/**
 * JavaFX main
 */
public class main extends Application {

    public static final String CURRENCY = "Kshs. ";

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/pos.fxml"));
        stage.setMaximized(true);

        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 960, 600));
        //stage.initStyle(StageStyle.UNDECORATED);
        //stage.setScene(new Scene(root));
        //root.getStylesheets().add("/css/styles.css");

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
