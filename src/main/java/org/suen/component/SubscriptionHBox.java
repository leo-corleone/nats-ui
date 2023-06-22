package org.suen.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.awt.print.Book;

/**
 * @author: suen
 * @time: 2023/6/22
 * @description:
 **/
public class SubscriptionHBox extends HBox {

    private HBox outHBox;

    public SubscriptionHBox() {

        Label label = new Label();
        ImageView imageView = new ImageView(new Image("image/subscribe.png"));
        imageView.setFitWidth(10);
        imageView.setFitHeight(10);
        label.setGraphic(imageView);
        label.setTranslateX(-5);
        label.setTranslateY(-10);

        outHBox = new HBox();
        outHBox.setMinWidth(670);
        outHBox.setAlignment(Pos.BASELINE_LEFT);

        getChildren().add(label);
        setAlignment(Pos.BASELINE_LEFT);
        setStyle("-fx-background-color: #beef83; -fx-border-radius: 5px;-fx-background-radius: 5px; -fx-border-width: 5px; -fx-arrows-visible: true");
        setPadding(new Insets(10 ,10 ,0 ,10));

        outHBox.getChildren().add(this);
    }

    public void setBackgroundColor(String color){
        setStyle("-fx-background-color:" + color);
    }

    public HBox getComponent(){
        return outHBox;
    }

}
