package org.suen.component;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.suen.constant.ViewConstant;

/**
 * @author: suen
 * @time: 2023/6/11
 * @description:
 **/
public class AddConnectionLabel extends Label {


    private static final int IMAGE_WIDTH = 30;

    private static final int IMAGE_HEIGHT = 30;

    private static final int POSITION_X = 40 - IMAGE_WIDTH / 2;

    private static final int POSITION_Y = 160;


    public AddConnectionLabel() {
        ImageView imageView = new ImageView(ViewConstant.ADD_ICON);
        imageView.setFitHeight(IMAGE_HEIGHT);
        imageView.setFitWidth(IMAGE_WIDTH);
        setGraphic(imageView);
        setTranslateY(POSITION_Y);
        setTranslateX(POSITION_X);
        setEventHandler(MouseEvent.MOUSE_CLICKED, event -> changeIcon());
    }

    private void changeIcon() {

    }
}