package org.suen.component;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.suen.constant.ViewConstant;

/**
 * @author: suen
 * @time: 2023/6/12
 * @description:
 **/
public class LogLabel extends Label {


    private static final int POSITION_X = 40 - ViewConstant.IMAGE_WIDTH / 2;

    private static final int POSITION_Y = 330;


    public LogLabel() {
        ImageView imageView = new ImageView(ViewConstant.LOG_ICON);
        imageView.setFitHeight(ViewConstant.IMAGE_HEIGHT);
        imageView.setFitWidth(ViewConstant.IMAGE_WIDTH);
        setGraphic(imageView);
        setTranslateY(POSITION_Y);
        setTranslateX(POSITION_X);
    }

}