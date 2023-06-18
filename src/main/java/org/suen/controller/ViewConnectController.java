package org.suen.controller;

import cn.hutool.extra.spring.SpringUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.suen.exception.BusinessException;
import org.suen.exception.Error;
import org.suen.exception.MessageHandler;
import org.suen.service.NatsConnectionService;
import org.suen.util.FXMLLoaderUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author: suen
 * @time: 2023/6/14
 * @description:
 **/

@Slf4j
@FXMLController
public class ViewConnectController {


    @Resource
    private NatsConnectionService connectionService;

    @FXML
    Button connectBtn;

    @FXML
    Label hostLbl;

    @FXML
    Label portLbl;

    @FXML
    Label nameLbl;

    @FXML
    Label pwdLbl;

    @FXML
    TextField hostField;


    @FXML
    TextField portField;


    @FXML
    TextField nameField;


    @FXML
    TextField pwdField;

    @FXML
    BorderPane mainControlPane;



    Pane centerCtrlPane;




    public void onConnect(MouseEvent event){
        String host = hostField.getText();
        String port = portField.getText();
        String username = nameField.getText();
        String password = pwdField.getText();
        if (host == null || host.trim().length() == 0){
            MessageHandler.handleError(Error.HOST_NOT_NULL);
            return;
        }
        if (port == null || port.trim().length() == 0){
            MessageHandler.handleError(Error.IP_NOT_NULL);
            return;
        }
        try {
        boolean connect = connectionService.connect(host , Integer.valueOf(port) , username , password);
        if (connect){
           if (centerCtrlPane == null){
              centerCtrlPane = (Pane) FXMLLoaderUtil.getParent("fx-config-ctl.fxml");
           }
           mainControlPane.setCenter(centerCtrlPane);
        }
        } catch (BusinessException e) {
            MessageHandler.handleError(e.getError());
        }
    }

}
