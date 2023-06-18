package org.suen.nats;

import com.alibaba.fastjson.JSONObject;
import io.nats.client.*;
import io.nats.client.impl.NatsMessage;
import io.nats.client.support.NatsConstants;
import org.suen.exception.BusinessException;
import org.suen.exception.Error;
import org.suen.nats.listener.NatsConnectionListener;
import org.suen.nats.listener.NatsErrorListener;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author: suen
 * @time: 2023/6/16
 * @description:
 **/
public class NatsClient {


    private Connection nc;


    private Dispatcher dispatcher;


    private final HashMap<String , Subscription> subscriptionMap = new HashMap<>();


    public boolean init(String host , Integer port , String username , String password) throws BusinessException {
        Options.Builder server = new Options.Builder().maxReconnects(-1).connectionListener(new NatsConnectionListener())
                .errorListener(new NatsErrorListener())
                .maxPingsOut(-1).server(host + ":" + port);
        if (username != null && username.trim().length() > 0 && password != null && password.trim().length() > 0){
            server.userInfo(username , password);
        }
        Options build = server.build();
        try {
            nc = Nats.connect(build);
            dispatcher = nc.createDispatcher();
        } catch (IOException | InterruptedException e) {
            throw new BusinessException(Error.NATS_CONNECT_ERROR);
        }
        return true;
    }

    public void subscribe(String subject , MessageHandler handler){
        Subscription subscription = dispatcher.subscribe(subject, handler);
        subscriptionMap.put(subject , subscription);
    }


    public void unsubscribe(String subject){
        Subscription subscription = subscriptionMap.get(subject);
        subscription.unsubscribe();
    }

    public void publish(String subject , Object payload){
        NatsMessage message = new NatsMessage.Builder().data(JSONObject.toJSONBytes(payload)).subject(subject).build();
        nc.publish(message);
    }


    public boolean isActive(){
        return nc != null && nc.getStatus() == Connection.Status.CONNECTED;
    }


    public void destroy(){
        if (isActive()){
            try {
                CompletableFuture<Boolean> drain = nc.drain(Duration.ofSeconds(5));
                drain.get();
            } catch (TimeoutException | InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}