package org.suen.controller;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ObjectUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.suen.component.PublicationHBox;
import org.suen.component.ReplyHBox;
import org.suen.component.SubscriptionHBox;
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
    Button connectBtn;

    @FXML
    Label descLbl;

    @FXML
    Label connectTipLbl;

    @FXML
    Pane subscriptionPane;

    @FXML
    Pane msgPane;


    @FXML
    ScrollPane scrollPane;

    String userDesc;

    private Login login;


    private Tooltip tooltip = new Tooltip();

    private ImageView imageView = new ImageView();


    private Image offImage = new Image("image/off.png");

    private Image onImage = new Image("image/on.png");

    @PostConstruct
    private void init(){
        refreshStatusImage();
    }


    public void onSubscription(MouseEvent event){

        SubscriptionHBox subscriptionHBox = new SubscriptionHBox();
        subscriptionHBox.setData(new Label("topic:23131232123"));


        PublicationHBox publicationHBox = new PublicationHBox();
        Label label = new Label("topic:dasdasdasdas\nd\nasdasdasd");
        publicationHBox.setData(label);


        ReplyHBox replyHBox = new ReplyHBox();
        Label label1 = new Label("topic:qweowjdlkjasldjlkasjldjlasds\nd\nasdasdasd");
        replyHBox.setData(label1);

        msgPane.getChildren().addAll(subscriptionHBox.getComponent() ,publicationHBox.getComponent() , replyHBox.getComponent());

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
        if (descLbl != null){
            descLbl.setText(login.getLoginDesc());
        }
        userDesc = login.getLoginDesc();
        this.login = login;
    }

    private void refreshStatusImage(){
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);
        imageView.setCache(false);
        tooltip.setContentDisplay(ContentDisplay.BOTTOM);
        ThreadUtil.execAsync(()->{
            while (true){
                if (natsClient.isActive()){
                    imageView.setImage(offImage);
                }else{
                    imageView.setImage(onImage);
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComponentData();
        initComponentStyle();
        addImageListener();
        addScrollPaneListener();
    }

    private void addScrollPaneListener() {
        msgPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(scrollPane.getVmax());
        });
    }

    private void initComponentStyle() {

    }


    private void initComponentData(){
        connectBtn.setGraphic(imageView);
        connectBtn.setTooltip(tooltip);
        descLbl.setText(userDesc);
    }

    private void addImageListener(){
        imageView.imageProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(()->{
                if (newValue == onImage){
                    tooltip.setText("点击连接");
                    connectBtn.setText("Connect");
                    connectBtn.setStyle("-fx-text-fill: #FFF;-fx-border-radius: 50px ;-fx-alignment: CENTER;-fx-background-radius: 25px; -fx-arrows-visible: true;-fx-border-width: 1;-fx-background-color:#36cb8c ");
                }else {
                    tooltip.setText("点击关闭连接");
                    connectBtn.setText("Disconnect");
                    connectBtn.setStyle("-fx-text-fill: #fff;-fx-border-radius: 50px ;-fx-alignment: CENTER;-fx-background-radius: 25px; -fx-arrows-visible: true;-fx-border-width: 1;-fx-background-color: #e97070 ");
                }
            });
        });
    }
}
