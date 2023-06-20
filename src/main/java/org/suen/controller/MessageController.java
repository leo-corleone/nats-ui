package org.suen.controller;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ObjectUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.suen.domain.Login;
import org.suen.exception.BusinessException;
import org.suen.nats.NatsClient;
import org.suen.service.LoginService;
import org.suen.service.MessageService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author: suen
 * @time: 2023/6/18
 * @description:
 **/

@FXMLController
public class MessageController  implements Initializable {


    @Resource
    private MessageService messageService;

    @Resource
    private NavigationController navigationController;


    @Resource
    private NatsClient natsClient;


    @FXML
    Label statusLbl;

    @FXML
    Label descLbl;

    @FXML
    Label connectTipLbl;

    String userDesc;

    private Login login;


    private Tooltip tooltip = new Tooltip();

    private ImageView imageView = new ImageView();

    @PostConstruct
    private void init(){
        refreshStatusImage();
    }


    public synchronized void onClick(){
        if (natsClient.isActive()){
            onClose();
        }else {
            onConnect();
        }
    }

    // 关闭连接
    private void onClose(){
        natsClient.destroy();
    }

    // 连接
    private void onConnect(){
        try {
            natsClient.init(login.getHost() , Integer.valueOf(login.getPort()) , login.getUsername() , login.getPassword());
            connectTipLbl.setText("");
        } catch (BusinessException e) {
            connectTipLbl.setTextFill(Color.RED);
            connectTipLbl.setText(e.getMsg());
        }

    }


    public void onBackToLogin(){
        // 关闭连接
        natsClient.destroy();
        navigationController.onBackTOLogin();
    }

    public void refreshLogin(Login login) {
        String desc = "user";
        if (ObjectUtil.isNotEmpty(login.getUsername())){
            desc = login.getUsername();
        };
        desc += "@"+ login.getHost() +":"+ login.getPort();
        if (descLbl != null){
            descLbl.setText(desc);
        }
        userDesc = desc;
        this.login = login;
    }

    private void refreshStatusImage(){
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        imageView.setCache(false);
        tooltip.setContentDisplay(ContentDisplay.BOTTOM);
        new Thread(()->{
            while (true){
                if (natsClient.isActive()){
                    imageView.setImage(new Image("image/off.png"));
                    tooltip.setText("点击关闭连接");
                }else{
                    imageView.setImage(new Image("image/on.png"));
                    tooltip.setText("点击连接");
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusLbl.setGraphic(imageView);
        statusLbl.setTooltip(tooltip);
        descLbl.setText(userDesc);
    }
}
