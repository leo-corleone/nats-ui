package org.suen.exception;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author: suen
 * @time: 2023/6/16
 * @description: 异常处理器
 **/
public class MessageHandler {


    public static void handleError(Error error){
       Alert alert = new Alert(error.getAlertType());
       alert.setContentText(error.getMsg());
       alert.setTitle("错误");
       alert.setHeaderText(null);
       Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
       stage.getIcons().add(new Image("image/logo.png"));
       alert.showAndWait();
    }

}
