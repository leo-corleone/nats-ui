package org.suen.domain;

import lombok.Data;

/**
 * @author: suen
 * @time: 2023/6/22
 * @description:
 **/
@Data
public class Message {

    private String subject;


    private String payload;


    private String type;

}
