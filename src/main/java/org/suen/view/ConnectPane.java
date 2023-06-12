package org.suen.view;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * @author: suen
 * @time: 2023/6/12
 * @description: 连接面板
 **/
public class ConnectPane extends BorderPane {

    private ConnectLeftPane connectLeftPane = new ConnectLeftPane();

    public ConnectPane(){

         setLeft(connectLeftPane);
    }

}
