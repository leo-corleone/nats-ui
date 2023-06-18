package org.suen.exception;

import lombok.Data;

/**
 * @author: suen
 * @time: 2023/6/16
 * @description: 业务异常类
 **/

@Data
public class BusinessException extends Exception{


    private String msg;

    private Integer code;

    private Error error;


    public BusinessException(Error error) {
        this.msg = error.getMsg();
        this.code = error.getCode();
        this.error = error;
    }

    public BusinessException(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public BusinessException() {
    }



}
