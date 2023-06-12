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


    private static final int POSITION_X = 40 - ViewConstant.IMAGE_WIDTH / 2;

    private static final int POSITION_Y = 230;


    public AddConnectionLabel() {
        ImageView imageView = new ImageView(ViewConstant.ADD_ICON);
        imageView.setFitHeight(ViewConstant.IMAGE_HEIGHT);
        imageView.setFitWidth(ViewConstant.IMAGE_WIDTH);
        setGraphic(imageView);
        setTranslateY(POSITION_Y);
        setTranslateX(POSITION_X);
    }
}