package org.suen.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: suen
 * @time: 2023/6/19
 * @description:
 **/

@Data
public class Login implements Serializable {

    private String host;


    private String port;


    private String username;


    private String password;



    public String getLoginDesc(){
       return username == null ? "user" : username + "@" + host + ":" + port;
    }
}
