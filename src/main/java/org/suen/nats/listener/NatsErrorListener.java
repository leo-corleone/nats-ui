package org.suen.nats.listener;

import cn.hutool.core.date.DateUtil;
import io.nats.client.*;
import io.nats.client.support.Status;
import javafx.scene.control.TextArea;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.suen.util.NatsServerUtil;

/**
 * @time: 2022/10/12
 * @author: .sun
 */


@Data
@Slf4j
@Component
public class NatsErrorListener implements ErrorListener {


    private TextArea textArea;

    public NatsErrorListener() {

    }

    /**
     * NATs related errors that occur asynchronously in the client library are sent
     * to an ErrorListener via errorOccurred. The ErrorListener can use the error text to decide what to do about the problem.
     * <p>The text for an error is described in the protocol doc at `https://nats.io/documentation/internals/nats-protocol`.
     * <p>In some cases the server will close the clients connection after sending one of these errors. In that case, the
     * connections {@link ConnectionListener ConnectionListener} will be notified.
     *
     * @param conn  The connection associated with the error
     * @param error The text of error that has occurred, directly from the server
     */
    @Override
    public void errorOccurred(Connection conn, String error) {
        String format = String.format("server : [%s]  ,  NATS connection error occurred :[%s]", NatsServerUtil.getServer(conn), error);
        log.error(format);
        textArea.setText(textArea.getText() +  DateUtil.now() +  " " +  format + "\n");
    }

    /**
     * Exceptions that occur in the "normal" course of operations are sent to the
     * ErrorListener using exceptionOccurred. Examples include, application exceptions
     * during Dispatcher callbacks, IOExceptions from the underlying socket, etc..
     * The library will try to handle these, via reconnect or catching them, but they are
     * forwarded here in case the application code needs them for debugging purposes.
     *
     * @param conn The connection associated with the error
     * @param exp  The exception that has occurred, and was handled by the library
     */
    @Override
    public void exceptionOccurred(Connection conn, Exception exp) {
        String format = String.format("server : [%s]  ,  NATS connection exception occurred :[%s]", NatsServerUtil.getServer(conn), exp);
        log.error(format);
        textArea.setText(textArea.getText() +  DateUtil.now() +  " " +  format + "\n");
    }

    /**
     * Called by the connection when a &quot;slow&quot; consumer is detected. This call is only made once
     * until the consumer stops being slow. At which point it will be called again if the consumer starts
     * being slow again.
     *
     * <p>See {@link Consumer#setPendingLimits(long, long) Consumer.setPendingLimits}
     * for information on how to configure when this method is fired.
     *
     * <p> Slow consumers will result in dropped messages each consumer provides a method
     * for retrieving the count of dropped messages, see {@link Consumer#getDroppedCount() Consumer.getDroppedCount}.
     *
     * @param conn     The connection associated with the error
     * @param consumer The consumer that is being marked slow
     */
    @Override
    public void slowConsumerDetected(Connection conn, Consumer consumer) {
        String format = String.format("server : [%s]  ,  NATS connection slow consumer detected", NatsServerUtil.getServer(conn));
        log.error(format);
        textArea.setText(textArea.getText() +  DateUtil.now() +  " " +  format + "\n");
    }

    /**
     * Called by the connection when a message is discarded.
     *
     * @param conn The connection that discarded the message
     * @param msg  The message that is discarded
     */
    @Override
    public void messageDiscarded(Connection conn, Message msg) {
        String format = String.format("server : [{}]  ,  NATS connection message discard :[{}]",NatsServerUtil.getServer(conn), msg.getSubject());
        log.error(format);
        textArea.setText(textArea.getText() +  DateUtil.now() +  " " +  format + "\n");
    }

    /**
     * Called when subscription heartbeats are missed according to the configured period and threshold.
     * The consumer must be configured with an idle heartbeat time.
     *
     * @param conn                 The connection that had the issue
     * @param sub                  the JetStreamSubscription that this occurred on
     * @param lastStreamSequence   the last received stream sequence
     * @param lastConsumerSequence the last received consumer sequence
     */
    @Override
    public void heartbeatAlarm(Connection conn, JetStreamSubscription sub, long lastStreamSequence, long lastConsumerSequence) {
        String format = String.format("server : [{}]  ,  NATS connection heartbeat alarm :[{}]",NatsServerUtil.getServer(conn), sub);
        log.error(format);
        textArea.setText(textArea.getText() +  DateUtil.now() +  " " +  format + "\n");
    }

    /**
     * Called by the connection when an unhandled status is received.
     *
     * @param conn   The connection that had the issue
     * @param sub    the JetStreamSubscription that this occurred on
     * @param status the status
     */
    @Override
    public void unhandledStatus(Connection conn, JetStreamSubscription sub, Status status) {
        String format = String.format("server : [{}]  ,  NATS connection unhandled status :[{}]", NatsServerUtil.getServer(conn), status);
        log.error(format);
        textArea.setText(textArea.getText() +  DateUtil.now() +  " " +  format + "\n");
    }

    /**
     * Called by the connection when a flow control is processed.
     *
     * @param conn    The connection that had the issue
     * @param sub     the JetStreamSubscription that this occurred on
     * @param subject the flow control subject that was handled
     * @param source  enum indicating flow control handling in response to which type of message
     */
    @Override
    public void flowControlProcessed(Connection conn, JetStreamSubscription sub, String subject, FlowControlSource source) {
        String format = String.format("server : [{}]  ,  NATS connection flow control processed :[{}]",NatsServerUtil.getServer(conn), sub);
        log.error(format);
        textArea.setText(textArea.getText() +  DateUtil.now() +  " " +  format + "\n");
    }

}
