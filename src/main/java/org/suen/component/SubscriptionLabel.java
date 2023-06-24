package org.suen.component;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;

/**
 * @author: suen
 * @time: 2023/6/24
 * @description:
 **/
public class SubscriptionLabel extends Label {

    private String topic;


    private String data;

    private Tooltip tooltip = new Tooltip();


    public SubscriptionLabel(String topic, String data) {
        this.topic = topic;
        this.data = data;
        String desc = "Topic:" + topic + "\n\n";
        desc += data;
        setText(desc);
        setTextFill(Color.WHITE);
        setMaxWidth(630);

        tooltip.setText(data);
        tooltip.setContentDisplay(ContentDisplay.LEFT);
        setTooltip(tooltip);
    }

}
