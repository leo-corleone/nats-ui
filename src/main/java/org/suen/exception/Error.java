package org.suen.exception;

import javafx.scene.control.Alert;
import lombok.Data;

/**
 * @author: suen
 * @time: 2023/6/16
 * @description:
 **/

@Data
public class Error {


    private Integer code;


    private String msg;


    private Alert.AlertType alertType;


    public Error(Integer code, String msg , Alert.AlertType alertType) {
        this.code = code;
        this.msg = msg;
        this.alertType = alertType;
    }

    public Error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Error() {
    }

    public static Error NATS_CONNECT_ERROR = new Error(ErrorCode.NATS_CONNECT_ERROR , "nats连接错误" , Alert.AlertType.ERROR);


    public static Error HOST_NOT_NULL = new Error(ErrorCode.TEXT_NOT_NULL , "主机输入不能为空" , Alert.AlertType.ERROR);



    public static Error IP_NOT_NULL = new Error(ErrorCode.TEXT_NOT_NULL , "端口号输入不能为空" , Alert.AlertType.ERROR);
}
