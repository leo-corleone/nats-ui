package org.suen.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.suen.component.AddConnectionLabel;
import org.suen.component.ConnectionLabel;

/**
 * @author: suen
 * @time: 2023/6/11
 * @description: 左主面板
 **/
public class LeftPane extends GridPane {

    public LeftPane() {
        setStyle("-fx-background-color: #2d4048 ; -fx-min-width: 80px");
        addLabel();
    }

    private void addLabel(){
       getChildren().add(new ConnectionLabel());
        getChildren().add(new AddConnectionLabel());
    }


}
