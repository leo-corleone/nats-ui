package org.suen.view;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * @author: suen
 * @time: 2023/6/12
 * @description:
 **/
public class ConnectLeftPane extends BorderPane {

    public ConnectLeftPane() {
        setStyle("-fx-background-color: #fdfdfd ; -fx-min-width: 200px");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10);
        dropShadow.setOffsetX(3);// 水平方向，0则向左右两侧，正则向右，负则向左
        dropShadow.setSpread(0.1);// 颜色变淡的程度
        dropShadow.setColor(Color.GRAY);
        setEffect(dropShadow);
    }
}
