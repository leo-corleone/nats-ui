package org.suen.component;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import org.springframework.boot.json.GsonJsonParser;
import org.suen.util.JsonUtil;

/**
 * @author: suen
 * @time: 2023/6/23
 * @description:
 **/
public class PublicationLabel extends Label {

    private String topic;


    private String data;

    private Tooltip tooltip = new Tooltip();


    public PublicationLabel(String topic, String data) {
        this.topic = topic;
        this.data = data;
        String desc = "Topic:" + topic + "\n\n";
        desc += JsonUtil.jsonFormat(data);
        setText(desc);
        setTextFill(Color.WHITE);
        setMaxWidth(630);

        tooltip.setText(desc);
        tooltip.setContentDisplay(ContentDisplay.LEFT);
        setTooltip(tooltip);
    }
}
