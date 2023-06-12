package org.suen;


import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.suen.constant.ViewConstant;
import org.suen.view.MainPane;

import javax.annotation.Resource;

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
        primaryStage.getIcons().add(new Image(ViewConstant.LOGO_ICON));
        primaryStage.show();
        log.info("start...");
    }
}