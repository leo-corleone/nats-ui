package org.suen.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.suen.nats.listener.NatsConnectionListener;
import org.suen.nats.listener.NatsErrorListener;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author: suen
 * @time: 2023/7/16
 * @description:
 **/

@FXMLController
public class NatsLogController {

    @FXML
    public TextArea natsLogArea;

    @Resource
    private NatsConnectionListener natsConnectionListener;

    @Resource
    private NatsErrorListener natsErrorListener;



    public void initialize(){
        natsErrorListener.setTextArea(natsLogArea);
        natsConnectionListener.setTextArea(natsLogArea);
    }

}
