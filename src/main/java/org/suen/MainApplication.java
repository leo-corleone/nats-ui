package org.suen;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label label = new Label("username");
        BorderPane pane = new BorderPane(label);
        Scene scene = new Scene(pane , 200 , 50);

        primaryStage.setScene(scene);
        primaryStage.setTitle("NATS-UI");
        primaryStage.show();
        primaryStage.setHeight(600);
        primaryStage.setWidth(900);
        primaryStage.getIcons().add(new Image("/image/logo.png" ));

    }
}