package org.suen.view;

import javafx.scene.layout.BorderPane;

/**
 * @author: suen
 * @time: 2023/6/11
 * @description: 主面板
 **/
public class MainPane extends BorderPane {


    public MainPane() {
        setCenter(new CenterPane());
        setLeft(new LeftPane());
    }
}
