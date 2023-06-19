package org.suen.controller;

import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.suen.util.FXMLLoaderUtil;

/**
 * @author: suen
 * @time: 2023/6/11
 * @description: 连接界面控制器
 **/
@Data
@Slf4j
@Component
public class NavigationController {

    @FXML
    BorderPane mainPane;

    Pane loginPane;


    public void initialize(){

    }

    public void onLogin(){
        if (loginPane == null) {
            loginPane = (Pane) FXMLLoaderUtil.getParent("fx-login.fxml");
        }
        mainPane.setCenter(loginPane);
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
