package org.suen.controller;

import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.suen.component.AddConnectionLabel;
import org.suen.component.ConnectionLabel;
import org.suen.view.CenterPane;
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


    public void addConnectEvent(){
        connectionLabel.addEventFilter(MouseEvent.MOUSE_CLICKED , (event)->{
            log.info("mouse click...");
        });
    }


}
