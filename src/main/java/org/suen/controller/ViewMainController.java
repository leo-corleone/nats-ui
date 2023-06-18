package org.suen.controller;

import cn.hutool.extra.spring.SpringUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.suen.service.NatsConnectionService;
import org.suen.util.FXMLLoaderUtil;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author: suen
 * @time: 2023/6/11
 * @description: 连接界面控制器
 **/
@Data
@Slf4j
@Component
public class ViewMainController {


    @Resource
    NatsConnectionService connectionService;

    @FXML
    BorderPane mainPane;

    @FXML
    Label connectLbl;


    Pane connectPane;

    @FXML
    Label addLbl;
    @FXML
    Label logLbl;


    public void initialize(){

    }

    public void onConnectView(){

        if (connectPane == null) {
            connectPane = (Pane) FXMLLoaderUtil.getParent("fx-config-connect.fxml");
        }
        mainPane.setCenter(connectPane);
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
