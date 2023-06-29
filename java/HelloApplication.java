package com.example.project_master_utillisation_relation_semantique_dumls;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // APPLE LA PAGE FXML
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 440);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        //THE PAGE FAML
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}