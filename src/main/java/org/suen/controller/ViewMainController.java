package org.suen.controller;

import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author: suen
 * @time: 2023/6/11
 * @description: 连接界面控制器
 **/
@Data
@Slf4j
public class ViewMainController {


    @FXML
    BorderPane mainPane;

    @FXML
    Label connectLbl;

    @FXML
    Label addLbl;
    @FXML
    Label logLbl;

    public void initialize(){
        log.info("initial...");
    }

    public void onConnectView(){
        try {
            Pane root = FXMLLoader.load(new ClassPathResource("fx-config-connect.fxml").getURL());
            mainPane.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void onHover(MouseEvent event){
        EventTarget target = event.getTarget();
        if (target instanceof Label){
            ((Label)target).setStyle("-fx-opacity: .5");
        }
    }


    public void onLeave(MouseEvent event){
        EventTarget target = event.getTarget();
        if (target instanceof Label){
            ((Label)target).setStyle("");
        }
    }


}
