package net.transino.lms.web;

import com.github.pagehelper.Page;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.transino.lms.modules.ext.ws.server.NotifyElecBillTradeHandler;
import net.transino.lms.modules.ext.ws.server.notify.ElecBillStatus;
import net.transino.lms.modules.ext.ws.server.notify.NotifyElecBillStatusRequest;
import net.transino.lms.modules.ext.ws.server.notify.NotifyElecBillStatusResponse;
import net.transino.lms.web.entity.ExtElecAccep;
import net.transino.lms.web.entity.PostDataEntity;
import net.transino.lms.web.service.IExtElecAccepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @since 5.0
 */
@Slf4j
@RestController
public class NotifyElecBillStatusController {
    @Autowired
    NotifyElecBillTradeHandler handler;
    @Autowired
    IExtElecAccepService service;

    @PostMapping(value = "/notifyElec")
    Object notifyElec(@RequestBody PostDataEntity entity) {
        log.info(entity.toString());
        NotifyElecBillStatusRequest ret = new NotifyElecBillStatusRequest();

        try {
            ret.getHead().setBizCode(entity.getBizCode());
            ret.getHead().setBatchNo(entity.getBatchNo());

            ret.getBody().setAppDate(entity.getAppDate());
            ret.getBody().setElecBatchId(entity.getElecBatchId());
            ret.getBody().setElecSum(entity.getElecSum());
            ret.getBody().setElecCount(entity.getElecCount());

            int count = entity.getElecCount();
            int start = entity.getStartNo();
            List<PostDataEntity> list = new ArrayList<>(count);
            PostDataEntity item;
            for (int i = 0; i < count; i++) {
                item = new PostDataEntity();
                // 交易流水号	elecBatchId
                item.setElecBatchId(entity.getElecBatchId());
                // 电票票据id	elecId
                if (StringUtil.isEmpty(entity.getElecId())){
                    item.setElecId(entity.getElecBatchId() + "-" + String.valueOf(i+start));
                }else {
                    item.setElecId(entity.getElecId());
                }

                // 票号	accepNo
                if (StringUtil.isEmpty(entity.getAccepNo())){
                    item.setAccepNo(entity.getElecBatchId() + "-" + String.valueOf(i+start));
                }else {
                    item.setAccepNo(entity.getAccepNo());
                }

                // 业务模式	tradeType
                item.setTradeType(entity.getTradeType());
                // 状态
                item.setElecStatus(entity.getElecStatus());
                list.add(item);
            }
            send(list, ret);
            return this.handler.send(ret, NotifyElecBillStatusResponse.class);
        } catch (JAXBException e) {
            log.error("WSDL:", e);
        } catch (Exception ex) {
            log.error("SYS:", ex);
        }
        return null;
    }

    private void send(List<PostDataEntity> list, NotifyElecBillStatusRequest ret) {
        PostDataEntity entity = null;
        double elecSum = 0;

        for (PostDataEntity aList : list) {
            entity = aList;
            ElecBillStatus bean = new ElecBillStatus();
            // 交易流水号	elecBatchId
            bean.setElecBatchId(entity.getElecBatchId());
            // 电票票据id	elecId
            bean.setElecId(entity.getElecId());
            // 票号	accepNo
            bean.setAccepNo(entity.getAccepNo());
            // 业务模式	tradeType
            bean.setTradeType("2");
            // 状态
            bean.setElecStatus("4");
            elecSum += entity.getAccepAmount();
            ret.addBodyData(bean);
        }

        assert entity != null;
        ret.getHead().setBizCode(entity.getBizCode());
        ret.getHead().setBatchNo(entity.getBatchNo());

        ret.getBody().setAppDate(entity.getAppDate());
        ret.getBody().setElecBatchId(entity.getElecBatchId());
        ret.getBody().setElecSum(elecSum);
        ret.getBody().setElecCount(list.size());
    }

    @PostMapping(value = "/extElecAccep")
    Object extElecAccep(@RequestBody PostDataEntity entity){
        Page<ExtElecAccep> list = service.query(entity.getTradeType(), entity.getSort(), entity.getOffset(), entity.getPageSize());
        Map<String, Object> body = new HashMap<>(2);
        body.put("rows", list.getResult());
        body.put("total", list.getTotal());
        return body;
    }

    @PostMapping(value = "/notifyElecList")
    Object notifyElecList(@RequestBody List<PostDataEntity> list){
        NotifyElecBillStatusRequest ret = new NotifyElecBillStatusRequest();
        send(list, ret);
        try {
            return this.handler.send(ret, NotifyElecBillStatusResponse.class);
        } catch (JAXBException e) {
            log.error("WSDL:", e);
        } catch (Exception ex) {
            log.error("SYS:", ex);
        }
        return null;
    }
}