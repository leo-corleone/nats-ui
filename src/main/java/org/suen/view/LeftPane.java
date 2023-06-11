package org.suen.view;

import javafx.scene.layout.GridPane;
import org.suen.component.AddConnectionLabel;
import org.suen.component.ConnectionLabel;
import org.suen.controller.ConnectViewController;

/**
 * @author: suen
 * @time: 2023/6/11
 * @description: 左主面板
 **/
public class LeftPane extends GridPane {

    private ConnectViewController controller;

    public LeftPane(MainPane mainPane , CenterPane centerPane) {
        controller = new ConnectViewController();
        controller.setCenterPane(centerPane);
        controller.setLeftPane(this);
        controller.setMainPane(mainPane);
        setStyle("-fx-background-color: #2d4048 ; -fx-min-width: 80px");
        addLabel();
    }

    private void addLabel(){
        ConnectionLabel connectionLabel = new ConnectionLabel();
        getChildren().add(connectionLabel);
        getChildren().add(new AddConnectionLabel());
        controller.setConnectionLabel(connectionLabel);
        controller.mouseClickAddConnectPane();
    }


}
