package org.suen;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.suen.constant.ViewConstant;
import org.suen.view.LeftPane;
import org.suen.view.CenterPane;
import org.suen.view.MainPane;

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

        Scene scene = new Scene(new MainPane(), ViewConstant.WINDOW_WIDTH , ViewConstant.WINDOW_HEIGHT);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("NATS-UI");
        primaryStage.setHeight(ViewConstant.WINDOW_HEIGHT);
        primaryStage.setWidth(ViewConstant.WINDOW_WIDTH);
        primaryStage.setMinHeight(ViewConstant.WINDOW_HEIGHT);
        primaryStage.setMinWidth(ViewConstant.WINDOW_WIDTH);
        primaryStage.getIcons().add(new Image(ViewConstant.LOGO));
        primaryStage.show();
        log.info("start...");

//        primaryStage.setOnCloseRequest(event ->{
//            event.consume();
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("退出程序");
//            alert.setHeaderText("xxxx");
//            alert.setContentText("是否退出程序");
//            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
//            alertStage.getIcons().add(new Image(ViewConstant.LOGO));
//
//            Optional<ButtonType> result = alert.showAndWait();
//            if (result.get() == ButtonType.OK){
//                Platform.exit();
//            }
//        });
    }
}