package org.suen.component;

import com.sun.javafx.scene.control.skin.ButtonBarSkin;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;
import org.suen.constant.ViewConstant;
import org.suen.event.MouseClickEvent;

/**
 * @author: suen
 * @time: 2023/6/11
 * @description: 连接标签
 **/

@Slf4j
public class ConnectionLabel extends Label {


    private static final int IMAGE_WIDTH = 30;

    private static final int IMAGE_HEIGHT = 30;

    private static final int POSITION_X = 40 - IMAGE_WIDTH / 2;

    private static final int POSITION_Y = 80;


    public ConnectionLabel() {
       ImageView imageView = new ImageView(ViewConstant.CONNECTION_ICON);
       imageView.setFitHeight(IMAGE_HEIGHT);
       imageView.setFitWidth(IMAGE_WIDTH);
       setGraphic(imageView);
       setTranslateY(POSITION_Y);
       setTranslateX(POSITION_X);
       setEventHandler(MouseEvent.MOUSE_CLICKED , event -> changeIcon());
    }

    private void changeIcon(){

    }
}
