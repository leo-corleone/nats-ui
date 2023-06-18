package org.suen.service;

import io.nats.client.MessageHandler;
import org.springframework.stereotype.Service;
import org.suen.exception.BusinessException;
import org.suen.nats.NatsClient;

/**
 * @author: suen
 * @time: 2023/6/16
 * @description:
 **/

@Service
public class NatsConnectionService {

    private final NatsClient natsClient = new NatsClient();

    public boolean connect(String host , Integer port , String username , String password) throws BusinessException {
               natsClient.destroy();
      return   natsClient.init(host , port , username , password);
    }

    public void publish(String subject , Object payload) throws Exception{
        if (subject == null || subject.trim().length() > 0){
            throw new Exception("主题不能为空");
        }
        natsClient.publish(subject , payload);
    }

    public void unsubscribe(String subject)throws Exception{
        if (subject == null || subject.trim().length() > 0){
            throw new Exception("主题不能为空");
        }
        natsClient.unsubscribe(subject);
    }


    public void subscribe(String subject , MessageHandler handler)throws Exception{
        if (subject == null || subject.trim().length() > 0){
            throw new Exception("主题不能为空");
        }
        natsClient.subscribe(subject,handler);
    }
}
