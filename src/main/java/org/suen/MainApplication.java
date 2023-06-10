package org.suen;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.suen.constant.ViewConstant;

import java.awt.event.ActionEvent;
import java.util.Optional;

@Slf4j
public class MainApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void init() throws Exception {
        super.init();
        log.info("init...");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        log.info("stop...");
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
        primaryStage.getIcons().add(new Image(ViewConstant.LOGO));

        log.info("start...");

        primaryStage.setOnCloseRequest(event ->{
            event.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("退出程序");
            alert.setHeaderText("xxxx");
            alert.setContentText("是否退出程序");
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(ViewConstant.LOGO));

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Platform.exit();
            }
        });
    }
}