package org.suen.controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.suen.component.ConnectionLabel;
import org.suen.view.CenterPane;
import org.suen.view.ConnectPane;
import org.suen.view.LeftPane;
import org.suen.view.MainPane;

/**
 * @author: suen
 * @time: 2023/6/11
 * @description: 连接界面控制器
 **/



@Data
@Slf4j
public class ConnectViewController {

    private MainPane mainPane;

    private LeftPane leftPane;

    private CenterPane centerPane;

    private ConnectionLabel connectionLabel;

    private ConnectPane connectPane = new ConnectPane();



    /**
     * 鼠标点击 添加连接面板
     */
    public void mouseClickAddConnectPane(){
        connectionLabel.addEventFilter(MouseEvent.MOUSE_CLICKED , (event)->{
            mainPane.setCenter(connectPane);
            log.info("mouse click...");
        });
    }


}
