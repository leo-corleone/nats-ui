package org.suen.component;

import cn.hutool.core.date.DateUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * @author: suen
 * @time: 2023/6/22
 * @description:
 **/
public class SubscriptionHBox extends HBox {


    private VBox outVBox;

    private HBox inHBox;

    public SubscriptionHBox() {
        initImage();
        initInHBox();
        initOutVBox();
        setAlignment(Pos.BASELINE_LEFT);
        setStyle("-fx-background-color: #beef83; -fx-border-radius: 5px;-fx-background-radius: 5px; -fx-border-width: 5px; -fx-arrows-visible: true");
        setPadding(new Insets(10, 10, 0, 10));
    }

    private void initOutVBox() {
        outVBox = new VBox(5);
        outVBox.setAlignment(Pos.BASELINE_LEFT);
        Label label = new Label(DateUtil.now());
        outVBox.setPadding(new Insets(10 , 0 , 10 , 0));
        label.setTextFill(Color.web("#36cb8c"));
        outVBox.getChildren().add(inHBox);
        outVBox.getChildren().add(label);
    }

    private void initInHBox() {
        inHBox = new HBox();
        inHBox.setMinWidth(670);
        inHBox.setAlignment(Pos.BASELINE_LEFT);
        inHBox.setPadding(new Insets(5, 0, 5, 0));
        inHBox.getChildren().add(this);
    }

    private void initImage() {
        Label label = new Label();
        ImageView imageView = new ImageView(new Image("image/subscribe.png"));
        imageView.setFitWidth(10);
        imageView.setFitHeight(10);
        label.setGraphic(imageView);
        label.setTranslateX(-5);
        label.setTranslateY(-10);
        getChildren().add(label);
    }

    public void setBackgroundColor(String color) {
        setStyle("-fx-background-color:" + color);
    }


    public void setData(Label label) {
        getChildren().add(label);
    }

    public VBox getComponent() {
        return outVBox;
    }

}
