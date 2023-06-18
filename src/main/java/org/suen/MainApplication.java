package org.suen;


import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.suen.constant.ViewConstant;
import org.suen.util.FXMLLoaderUtil;

@Slf4j
@SpringBootApplication
public class MainApplication extends Application {


    private   static  ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(MainApplication.class, args);
        Application.launch(args);
    }


    @Override
    public void init() throws Exception {
        log.info("init...");
    }

    @Override
    public void stop() throws Exception {
        log.info("stop...");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(FXMLLoaderUtil.getParent("fx-config-main.fxml"), ViewConstant.WINDOW_WIDTH , ViewConstant.WINDOW_HEIGHT);
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


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}