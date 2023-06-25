package org.suen.service;

import io.nats.client.Message;
import io.nats.client.MessageHandler;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.suen.domain.Login;
import org.suen.nats.NatsClient;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.function.Consumer;

/**
 * @author: suen
 * @time: 2023/6/19
 * @description:
 **/
@Service
public class MessageService {


    @Resource
    private NatsClient natsClient;


    public void publish(String subject , Object payload , Consumer<String> consumer){
        natsClient.publish(subject , payload);
        consumer.accept(payload.toString());
    }

    public void subscribe(String subject , MessageHandler messageHandler){
        natsClient.subscribe(subject , messageHandler);
    }


}
