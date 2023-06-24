package org.suen.component;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lombok.Data;
import lombok.Getter;

/**
 * @author: suen
 * @time: 2023/6/24
 * @description:
 **/

@Getter
public class SubscriptionDialogPane extends DialogPane {


    private AnchorPane anchorPane;

    private TextField topicField;

    private ColorPicker colorPicker;

    public SubscriptionDialogPane() {

        anchorPane = new AnchorPane();

        anchorPane.setTranslateX(0);
        anchorPane.setTranslateY(0);
        anchorPane.setMinWidth(300);
        anchorPane.setMinHeight(240);
        getButtonTypes().add(new ButtonType("Cancel" , ButtonBar.ButtonData.CANCEL_CLOSE));
        getButtonTypes().add(new ButtonType("Confirm" , ButtonBar.ButtonData.OK_DONE));
        setContent(anchorPane);
        setMinWidth(500);
        setMinHeight(260);
        addTopicLabel();
        addTopicTextField();
        addColorLabel();
        addColorPicker();
    }

    private void addColorPicker() {
        colorPicker = new ColorPicker(Color.web("#0d6e5b"));
        colorPicker.setTranslateX(40);
        colorPicker.setTranslateY(140);
        colorPicker.setMinWidth(130);
        colorPicker.setMaxHeight(60);
        anchorPane.getChildren().add(colorPicker);
    }

    private void addColorLabel() {
        Label color = new Label();
        color.setText("Color");
        color.setTranslateY(120);
        color.setTranslateX(40);
        anchorPane.getChildren().add(color);
    }

    private void addTopicTextField() {
        topicField = new TextField();
        topicField.getStylesheets().add("css/textField.css");
        topicField.setMinWidth(420);
        topicField.setMaxHeight(50);
        topicField.setTranslateX(40);
        topicField.setTranslateY(60);
        topicField.setText("test.subject");
        topicField.setStyle("-fx-background-radius: 5px;-fx-border-radius: 5px ; -fx-arrows-visible: true;-fx-border-width: 1;-fx-border-color:#36cb8c");
        anchorPane.getChildren().add(topicField);
    }

    private void addTopicLabel() {
        ImageView imageView =new ImageView("image/subscribe.png");
        imageView.setFitWidth(10);
        imageView.setFitHeight(10);
        Label label = new Label();
        label.setGraphic(imageView);
        label.setText("Topic");
        label.setTranslateY(40);
        label.setTranslateX(40);
        anchorPane.getChildren().add(label);
    }
}
