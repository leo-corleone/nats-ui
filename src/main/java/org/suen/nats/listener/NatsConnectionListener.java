package org.suen.nats.listener;

import cn.hutool.core.date.DateUtil;
import io.nats.client.Connection;
import io.nats.client.ConnectionListener;
import javafx.scene.control.TextArea;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import org.suen.util.NatsServerUtil;

import javax.annotation.Resource;

/**
 * @author: suen
 * @time: 2023/6/16
 * @description:
 **/

@Data
@Slf4j
@Component
public class NatsConnectionListener implements ConnectionListener {


    private TextArea textArea;

    /**
     * Connection related events that occur asynchronously in the client code are
     * sent to a ConnectionListener via a single method. The ConnectionListener can
     * use the event type to decide what to do about the problem.
     *
     * @param conn the connection associated with the error
     * @param type the type of event that has occurred
     */
    @Override
    public void connectionEvent(Connection conn, Events type) {
        String format = String.format("server: [%s]  NATS connection status changed %s",  NatsServerUtil.getServer(conn), type);
        log.info(format);
        textArea.setText(textArea.getText() +  DateUtil.now() +  " " +  format + "\n");
    }
}
