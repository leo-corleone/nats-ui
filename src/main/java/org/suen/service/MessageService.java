package org.suen.service;

import org.springframework.stereotype.Service;
import org.suen.domain.Login;

/**
 * @author: suen
 * @time: 2023/6/19
 * @description:
 **/

@Service
public class MessageService {

    private Login login;



    public void  setLogin(Login login){
        this.login = login;
    }


}
