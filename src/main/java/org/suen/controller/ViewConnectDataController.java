package org.suen.controller;

import de.felixroske.jfxsupport.FXMLController;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.suen.service.NatsConnectionService;

import javax.annotation.Resource;

/**
 * @author: suen
 * @time: 2023/6/18
 * @description:
 **/
@Data
@FXMLController
public class ViewConnectDataController {

    @Autowired
    private NatsConnectionService connectionService;




}
