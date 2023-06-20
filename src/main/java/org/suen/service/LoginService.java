package org.suen.service;

import org.springframework.stereotype.Service;
import org.suen.controller.MessageController;
import org.suen.domain.Login;
import org.suen.exception.BusinessException;
import org.suen.exception.Error;
import org.suen.exception.MessageHandler;
import org.suen.nats.NatsClient;

import javax.annotation.Resource;

/**
 * @author: suen
 * @time: 2023/6/16
 * @description:
 **/

@Service
public class LoginService {

    @Resource
    private  NatsClient natsClient;

    @Resource
    private MessageController messageController;


    public boolean loginNats(Login login) throws BusinessException {
        if (login.getHost() == null || login.getHost().trim().length() == 0){
            throw new BusinessException(Error.HOST_NOT_NULL);
        }
        if (login.getPort() == null || login.getPort().trim().length() == 0){
            throw new BusinessException(Error.IP_NOT_NULL);
        }
        natsClient.init(login.getHost() , Integer.valueOf(login.getPort()) , login.getUsername() , login.getPassword());
        messageController.refreshLogin(login);
        return true;
    }

}
