package org.suen.util;

import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamManagement;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.api.StreamInfo;

import java.io.IOException;

/**
 * @author: suen
 * @time: 2023/4/2
 * @description: jetStream 工具类
 **/
public class JetStreamUtil {


    public static StreamInfo getStreamInfo(JetStreamManagement jsManagement , String streamName){
        try {
           return jsManagement.getStreamInfo(streamName);
        } catch (IOException | JetStreamApiException e) {
           return null;
        }
    }

    public static void addStream(JetStreamManagement jsManagement , StreamConfiguration streamConfiguration){
        try {
             jsManagement.addStream(streamConfiguration);
        } catch (IOException | JetStreamApiException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStream(JetStreamManagement jsManagement ,String streamName){
        try {
           jsManagement.deleteStream(streamName);
        } catch (IOException | JetStreamApiException e) {

        }
    }

    public static void deleteConsumer(JetStreamManagement jsManagement ,String streamName , String consumer){
        try {
            jsManagement.deleteConsumer(streamName ,consumer);
        } catch (IOException | JetStreamApiException e) {
        }
    }

    public static void updateOrAddConsumer(JetStreamManagement jsManagement , String streamName , ConsumerConfiguration consumerConfiguration){
        try {
            jsManagement.addOrUpdateConsumer(streamName ,consumerConfiguration);
        } catch (IOException | JetStreamApiException e) {
        }
    }
}
