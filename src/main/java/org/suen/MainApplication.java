package org.suen;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.JobExecutionExitCodeGenerator;
import org.springframework.context.ApplicationContext;
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
        SpringApplication.exit(applicationContext, new JobExecutionExitCodeGenerator());
        log.info("stop...");
        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(FXMLLoaderUtil.getParent("fx-navigation.fxml"), ViewConstant.WINDOW_WIDTH , ViewConstant.WINDOW_HEIGHT);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("NATS-UI");
        primaryStage.setHeight(ViewConstant.WINDOW_HEIGHT);
        primaryStage.setResizable(false);
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