package net.transino.lms.web;

import lombok.extern.slf4j.Slf4j;
import net.transino.lms.Config;
import net.transino.lms.modules.ext.ws.server.ApplyElecBillTradeHandler;
import net.transino.lms.modules.ext.ws.server.apply.ApplyElecBillTradeRequest;
import net.transino.lms.modules.ext.ws.server.apply.ApplyElecBillTradeResponse;
import net.transino.lms.modules.ext.ws.server.apply.ElecBillTrade;
import net.transino.lms.web.entity.PostDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

/**
 * @author lee
 * @since 5.0
 */
@Slf4j
@RestController
public class SoapServerController {
    @Autowired
    Config config;

    @Autowired
    ApplyElecBillTradeHandler handler;

    @PostMapping(value = "/serverSet")
    Object serverSet(@RequestBody PostDataEntity entity) {
        log.info(entity.toString());
        config.setWsdlLocation(entity.getWsdlLocation());
        config.setWsdlLocation2(entity.getWsdlLocation2());
        return null;
    }
}