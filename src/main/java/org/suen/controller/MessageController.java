package org.suen.controller;

import de.felixroske.jfxsupport.FXMLController;
import lombok.Data;
import org.suen.service.MessageService;

import javax.annotation.Resource;

/**
 * @author: suen
 * @time: 2023/6/18
 * @description:
 **/
@Data
@FXMLController
public class MessageController {


    @Resource
    private MessageService messageService;

}
