package org.suen.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * @author: suen
 * @time: 2023/6/22
 * @description:
 **/
public class PublicationHBox extends HBox {


    private HBox outHBox;

    public PublicationHBox() {

        Label label = new Label();
        ImageView imageView = new ImageView(new Image("image/publish.png"));
        imageView.setFitWidth(10);
        imageView.setFitHeight(10);
        label.setGraphic(imageView);
        label.setTranslateX(-5);
        label.setTranslateY(-10);

        getChildren().add(label);

        outHBox = new HBox();
        outHBox.setMinWidth(670);
        outHBox.setAlignment(Pos.BASELINE_RIGHT);
        outHBox.setPadding(new Insets(5 , 0 , 5 ,0));
        setAlignment(Pos.BASELINE_RIGHT);

        setStyle("-fx-background-color: #79bda0; -fx-border-radius: 5px;-fx-background-radius: 5px; -fx-border-width: 5px; -fx-arrows-visible: true");
        setPadding(new Insets(10 ,10 ,0 ,10));
        outHBox.getChildren().add(this);
    }

    public HBox getComponent(){
        return outHBox;
    }

}
