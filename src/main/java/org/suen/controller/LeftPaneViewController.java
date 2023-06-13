package org.suen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: suen
 * @time: 2023/6/11
 * @description: 连接界面控制器
 **/
@Data
@Slf4j
public class LeftPaneViewController {


    @FXML
    BorderPane mainPane;

    @FXML
    Label connectLabel;


    @FXML
    Button butUp;

    public void onUp(){

    }


}
