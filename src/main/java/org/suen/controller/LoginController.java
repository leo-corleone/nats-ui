package org.suen.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;
import org.suen.domain.Login;
import org.suen.exception.BusinessException;
import org.suen.service.LoginService;
import org.suen.util.FXMLLoaderUtil;

import javax.annotation.Resource;

/**
 * @author: suen
 * @time: 2023/6/14
 * @description:
 **/

@Slf4j
@FXMLController
public class LoginController {


    @Resource
    private LoginService loginService;

    @FXML
    Button connectBtn;

    @FXML
    Label hostLbl;

    @FXML
    Label portLbl;

    @FXML
    Label nameLbl;

    @FXML
    Label pwdLbl;

    @FXML
    TextField hostField;

    @FXML
    TextField portField;

    @FXML
    TextField nameField;

    @FXML
    TextField pwdField;

    @FXML
    Label loginTipLbl;

    @FXML
    BorderPane mainControlPane;

    Pane messagePane;


    public void onConnect(MouseEvent event) {

        Login login = new Login();
        login.setHost(hostField.getText());
        login.setPort(portField.getText());
        login.setUsername(nameField.getText());
        login.setPassword(pwdField.getText());

        try {
            if (loginService.loginNats(login)) {
                if (messagePane == null) {
                    messagePane = (Pane) FXMLLoaderUtil.getParent("fx-message.fxml");
                }
                mainControlPane.setCenter(messagePane);
            }
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }

    }

}
