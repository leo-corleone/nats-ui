package org.suen.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.text.StringEscapeUtils;
import org.suen.component.*;
import org.suen.domain.Login;
import org.suen.exception.BusinessException;
import org.suen.nats.NatsClient;
import org.suen.service.MessageService;
import org.suen.util.JsonUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author: suen
 * @time: 2023/6/18
 * @description:
 **/

@FXMLController
public class MessageController implements Initializable {


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

    @FXML
    Label publishLbl;

    @FXML
    Label requestLbl;


    @FXML
    ComboBox<String> comboBox;

    @FXML
    TextField topicTxt;


    @FXML
    TextArea payloadText;

    @FXML
    VBox subRecord;


    @FXML
    ScrollPane subScPane;

    List<SubscriptionRecord> subscriptionRecordList = new ArrayList<>();

    String userDesc;

    private Login login;


    private Tooltip tooltip = new Tooltip();

    private ImageView imageView = new ImageView();


    private Image offImage = new Image("image/off.png");

    private Image onImage = new Image("image/on.png");

    @PostConstruct
    private void init() {
        refreshStatusImage();
    }


    public void onSubscription() {
        showSubscriptionPane();
    }

    private void showSubscriptionPane() {

        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeight(300);
        inputDialog.setWidth(500);
        inputDialog.setGraphic(new ImageView("image/subscribe.png"));
        Window window = inputDialog.getDialogPane().getScene().getWindow();
        ((Stage)window).getIcons().add(new Image("image/logo.png"));
        SubscriptionDialogPane subscriptionDialogPane = new SubscriptionDialogPane();
        inputDialog.setDialogPane(subscriptionDialogPane);
        inputDialog.setTitle("New Subscription");
        inputDialog.show();
        inputDialog.setResultConverter(buttonType -> {

            if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE){
                TextField topicField = subscriptionDialogPane.getTopicField();
                if (topicField.getText() != null && topicField.getText().trim().length() > 0){
                    ColorPicker colorPicker = subscriptionDialogPane.getColorPicker();
                    Color color = colorPicker.getValue();
                    String topic = topicField.getText();
                    // 1.订阅记录
                    SubscriptionRecord subscriptionRecord = new SubscriptionRecord();
                    subscriptionRecord.setTopic(topic);
                    subscriptionRecord.setBgColor(color);
                    subscriptionRecord.setParentPane(subRecord);
                    subscriptionRecord.setNatsClient(natsClient);
                    subRecord.getChildren().add(subscriptionRecord);
                    messageService.subscribe(topic, message -> Platform.runLater(()->{
                        // 2.处理对话框记录
                        String data = StringEscapeUtils.unescapeJava(new String(message.getData() , StandardCharsets.UTF_8));
                        String subject = message.getSubject();
                        SubscriptionHBox subscriptionHBox = new SubscriptionHBox();
                        subscriptionHBox.setData(new SubscriptionLabel(subject , data));
                        subscriptionHBox.setBackgroundColor(color);
                        msgPane.getChildren().add(subscriptionHBox.getComponent());
                    }));
                }
            }
            return buttonType.getText();});
    }


    public void onPublish() {

        String topic = topicTxt.getText();
        if (topic == null || topic.trim().length() == 0) {
            // TODO 提示信息
            return;
        }
        String payloadData = payloadText.getText();
        Object payload;
        if (payloadData == null){
            payload = "";
        }else {
            payload = JsonUtil.isJson(payloadData) ? JSON.parseObject(payloadData , Object.class) : StringEscapeUtils.unescapeJava(payloadData);
        }
        messageService.publish(topic, payload, data -> {
            showPublicationData(topic, data);
        });
    }



    public void onRequest() {
        String topic = topicTxt.getText();
        if (topic == null || topic.trim().length() == 0) {
            // TODO 提示信息
            return;
        }
        String payloadData = payloadText.getText();
        Object payload;
        if (payloadData == null){
            payload = "";
        }else {
            payload = JsonUtil.isJson(payloadData) ? JSON.parseObject(payloadData , Object.class) : StringEscapeUtils.unescapeJava(payloadData);
        }
        messageService.publish(topic, payload, data -> {
            showPublicationData(topic, data);
        });
    }




    private void showPublicationData(String subject, String payload) {
        PublicationHBox publicationHBox = new PublicationHBox();
        publicationHBox.setData(new PublicationLabel(subject, payload));
        msgPane.getChildren().add(publicationHBox.getComponent());
    }

    public synchronized void onClick() {
        if (natsClient.isActive()) {
            onClose();
        } else {
            onConnect();
        }
    }

    // 关闭连接
    private void onClose() {
        natsClient.destroy();
        clearMessagePaneRecord();
        clearSubscriptionRecord();
    }

    // 连接
    private void onConnect() {
        try {
            natsClient.init(login.getHost(), Integer.valueOf(login.getPort()), login.getUsername(), login.getPassword());
            connectTipLbl.setText("");
        } catch (BusinessException e) {
            connectTipLbl.setTextFill(Color.RED);
            connectTipLbl.setText(e.getMsg());
        }

    }


    public void onBackToLogin() {
        clearMessagePaneRecord();
        clearSubscriptionRecord();
        // 关闭连接
        natsClient.destroy();
        navigationController.onBackTOLogin();
    }

    private void clearSubscriptionRecord() {
        ObservableList<Node> children = subRecord.getChildren();
        children.clear();
    }

    private void clearMessagePaneRecord() {
        ObservableList<Node> children = msgPane.getChildren();
        children.clear();
    }

    public void refreshLogin(Login login) {
        if (descLbl != null) {
            descLbl.setText(login.getLoginDesc());
        }
        userDesc = login.getLoginDesc();
        this.login = login;
    }

    private void refreshStatusImage() {
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);
        imageView.setCache(false);
        tooltip.setContentDisplay(ContentDisplay.BOTTOM);
        ThreadUtil.execAsync(() -> {
            while (true) {
                if (natsClient.isActive()) {
                    imageView.setImage(offImage);
                } else {
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
        addModeListener();
    }

    private void addModeListener() {
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            boolean isPublish = newValue.equalsIgnoreCase("Publish");
            publishLbl.setVisible(isPublish);
            requestLbl.setVisible(!isPublish);
        });
    }

    private void addScrollPaneListener() {
        msgPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(scrollPane.getVmax());
        });
    }

    private void initComponentStyle() {

    }


    private void initComponentData() {
        connectBtn.setGraphic(imageView);
        connectBtn.setTooltip(tooltip);
        descLbl.setText(userDesc);
    }

    private void addImageListener() {
        imageView.imageProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                if (newValue == onImage) {
                    tooltip.setText("点击连接");
                    connectBtn.setText("Connect");
                    connectBtn.setStyle("-fx-text-fill: #FFF;-fx-border-radius: 50px ;-fx-alignment: CENTER;-fx-background-radius: 25px; -fx-arrows-visible: true;-fx-border-width: 1;-fx-background-color:#36cb8c ");
                } else {
                    tooltip.setText("点击关闭连接");
                    connectBtn.setText("Disconnect");
                    connectBtn.setStyle("-fx-text-fill: #fff;-fx-border-radius: 50px ;-fx-alignment: CENTER;-fx-background-radius: 25px; -fx-arrows-visible: true;-fx-border-width: 1;-fx-background-color: #e97070 ");
                }
            });
        });
    }
}
