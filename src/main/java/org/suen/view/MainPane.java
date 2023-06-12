package org.suen.view;

import javafx.scene.layout.BorderPane;
import org.springframework.stereotype.Component;

/**
 * @author: suen
 * @time: 2023/6/11
 * @description: 主面板
 **/

public class MainPane extends BorderPane {


    public MainPane() {
        CenterPane centerPane = new CenterPane();
        setCenter(centerPane);
        setLeft(new LeftPane(this , centerPane));
    }
}
