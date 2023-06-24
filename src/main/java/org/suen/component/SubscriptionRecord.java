package org.suen.component;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.suen.nats.NatsClient;
import org.suen.util.ColorUtil;

/**
 * @author: suen
 * @time: 2023/6/23
 * @description:
 **/


public class SubscriptionRecord extends HBox {



    private VBox parentPane;

    private Label topicLabel;


    private NatsClient natsClient;

    public SubscriptionRecord() {

        topicLabel = new Label();
        topicLabel.setTextFill(Color.WHITE);
        setAlignment(Pos.BASELINE_LEFT);
        setMinWidth(150);
        setMaxHeight(50);
        setPadding(new Insets(10,10,10,10));
        addUnSubscribeImage();
        getChildren().add(topicLabel);
    }

    private void addUnSubscribeImage() {
        Label label = new Label();
        ImageView imageView = new ImageView(new Image("image/unsubscribe.png"));
        imageView.setFitWidth(10);
        imageView.setFitHeight(15);
        label.setGraphic(imageView);
        label.setTranslateX(-5);
        label.setTranslateY(-10);
        label.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            natsClient.unsubscribe(topicLabel.getText());
            removeSubscribe();
        });
        getChildren().add(label);
    }


    private void removeSubscribe(){
        parentPane.getChildren().remove(this);
    }

    public void setParentPane(VBox vBox){
        this.parentPane = vBox;
    }

    public void setTopic(String topic){
        topicLabel.setText(topic);
    }

    public void setNatsClient(NatsClient natsClient){
        this.natsClient = natsClient;
    }

    public void setBgColor(Color color){
        setStyle("-fx-background-radius: 10px; -fx-arrows-visible: true;-fx-border-width: 1;-fx-background-color:" + ColorUtil.toHexString(color));
    }
}
