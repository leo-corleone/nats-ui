package org.suen.nats.listener;

import io.nats.client.Connection;
import io.nats.client.ConnectionListener;
import lombok.extern.slf4j.Slf4j;
import org.suen.util.NatsServerUtil;

/**
 * @author: suen
 * @time: 2023/6/16
 * @description:
 **/

@Slf4j
public class NatsConnectionListener implements ConnectionListener {

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
        log.info("service: [{}] server: [{}]  NATS connection status changed {}" ,conn.getOptions().getConnectionName() , NatsServerUtil.getServer(conn), type);
    }
}
