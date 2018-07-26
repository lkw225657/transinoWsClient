package net.transino.lms.web;

import lombok.extern.slf4j.Slf4j;
import net.transino.lms.modules.ext.ws.server.ApplyElecBillTradeHandler;
import net.transino.lms.modules.ext.ws.server.apply.ApplyElecBillTradeRequest;
import net.transino.lms.modules.ext.ws.server.apply.ApplyElecBillTradeResponse;
import net.transino.lms.modules.ext.ws.server.apply.ElecBillTrade;
import net.transino.lms.web.entity.PostDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.util.Date;

/**
 * @author lee
 * @since 5.0
 */
@Slf4j
@RestController
public class ApplyDiscountController {
    @Autowired
    ApplyElecBillTradeHandler handler;

    @PostMapping(value = "/applyElec")
    Object applyDiscount(@RequestBody PostDataEntity entity) {
        log.info(entity.toString());

        ApplyElecBillTradeRequest ret = new ApplyElecBillTradeRequest();
        try {
            ret.getHead().setBizCode(entity.getBizCode());
            ret.getHead().setBatchNo(entity.getBatchNo());

            String elecBatchId = entity.getElecBatchId() + "-" + String.valueOf(System.currentTimeMillis());

            int count = entity.getElecCount();
            ret.getBody().setDueDate(entity.getDiscDate());
            ret.getBody().setAppDate(entity.getAppDate());
            ret.getBody().setElecBatchId(elecBatchId);
            ret.getBody().setDiscIr(entity.getDiscIr());
            ret.getBody().setElecSum(entity.getAccepAmount() * count);
            ret.getBody().setElecCount(entity.getElecCount());
            ret.getBody().setInWayDays(entity.getInWayDays());

            for (int i = 1; i<= count; i++) {
                ElecBillTrade bean = new ElecBillTrade();
                // 交易流水号	elecBatchId
                bean.setElecBatchId(elecBatchId);
                // 电票票据id	elecId
                bean.setElecId(elecBatchId + "-" + String.valueOf(i));
                // 票号	accepNo
                bean.setAccepNo(elecBatchId + "-" + String.valueOf(i));
                // 票据介质类型	accepDielType
                bean.setAccepDielType(entity.getAccepDielType());
                // 财务公司票据	bown
                bean.setBown(entity.getBown());
                // 币种	accepCurrType
                bean.setAccepCurrType(entity.getAccepCurrType());
                // 票面金额	accepAmount
                bean.setAccepAmount(entity.getAccepAmount());
                // 承兑日期	issueDate
                bean.setIssueDate(entity.getIssueDate());
                // 到期日	dueDate
                bean.setDueDate(entity.getDueDate());
                // 付款行行号	draweeNo
                bean.setDraweeNo(entity.getDraweeNo());
                // 付款行全称	draweeName
                bean.setDraweeName(entity.getDraweeName());
                // 付款行地址	draweeAddress
                bean.setDraweeAddress(entity.getDraweeAddress());
                // 出票人证件类型	drawerIdType
                bean.setDrawerIdType(entity.getDrawerIdType());
                // 出票人证件号码	drawerIdNo
                bean.setDrawerIdNo(entity.getDrawerIdNo());
                // 出票人客户号	drawerCusId
                bean.setDrawerCusId(entity.getDrawerCusId());
                // 出票人姓名	drawerName
                bean.setDrawerName(entity.getDrawerName());
                // 出票人账号	drawerAccount
                bean.setDrawerAccount(entity.getDrawerAccount());
                // 收款银行号	payeeBankNo
                bean.setPayeeBankNo(entity.getPayeeBankNo());
                // 收款银行名	payeeBankName
                bean.setPayeeBankName(entity.getPayeeBankName());
                // 收款行地址	payeeBankAddress
                bean.setPayeeBankAddress(entity.getPayeeBankAddress());
                // 收票人证件类型	payeeIdType
                bean.setPayeeIdType(entity.getPayeeIdType());
                // 收票人证件号码	payeeIdNo
                bean.setPayeeIdNo(entity.getPayeeIdNo());
                // 收票人客户号	payeeCusId
                bean.setPayeeCusId(entity.getPayeeCusId());
                // 收票人姓名	payeeName
                bean.setPayeeName(entity.getPayeeName());
                // 收票人账号	payeeAccount
                bean.setPayeeAccount(entity.getPayeeAccount());
                // 业务模式	tradeType
                bean.setTradeType(entity.getTradeType().substring(0,1));
                // 申请日期
                bean.setAppDate(entity.getAppDate());

                bean.setPayeeAccounts(entity.getPayeeAccounts());

                ret.addBodyData(bean);
            }
            return this.handler.send(ret, ApplyElecBillTradeResponse.class);
        } catch (JAXBException e) {
            log.error("WSDL:", e);
        } catch (Exception ex) {
            log.error("SYS:", ex);
        }
        return null;
    }
}