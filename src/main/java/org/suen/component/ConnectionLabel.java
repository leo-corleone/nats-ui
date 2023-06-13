package org.suen.component;

import javafx.scene.ImageCursor;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import lombok.extern.slf4j.Slf4j;
import org.suen.constant.ViewConstant;

/**
 * @author: suen
 * @time: 2023/6/11
 * @description: 连接标签
 **/

@Slf4j
public class ConnectionLabel extends Label {


    private static final int POSITION_X = 40 - ViewConstant.IMAGE_WIDTH / 2;

    private static final int POSITION_Y = 130;


    public ConnectionLabel() {
       ImageView imageView = new ImageView(ViewConstant.CONNECTION_ICON);
       imageView.setFitHeight(ViewConstant.IMAGE_HEIGHT);
       imageView.setFitWidth(ViewConstant.IMAGE_WIDTH);
       setGraphic(imageView);
       setTranslateY(POSITION_Y);
       setTranslateX(POSITION_X);
    }

}
