package net.transino.lms.web.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author lee
 * @since 5.0
 */
@Getter
@Setter
public class ExtElecAccep {
    /**
     * 电票发来申请批次号
     */
    private String elecBatchId;

    /**
     * 每张票一个非票据号的id
     */
    private String elecId;

    /**
     * 票据号码
     */
    private String accepNo;

    /**
     * 是否财务公司汇票#0000
     */
    private String bown;

    /**
     * 币种
     */
    private String accepCurrType;

    /**
     * 票面金额
     */
    private double accepAmount;

    /**
     * 承兑日期
     */
    private Date issueDate;

    /**
     * 到期日
     */
    private Date dueDate;

    /**
     * 付款行行号
     */
    private String draweeNo;

    /**
     * 付款行全称
     */
    private String draweeName;

    /**
     * 付款行地址
     */
    private String draweeAddress;

    /**
     * 出票人证件类型
     */
    private String drawerIdType;

    /**
     * 出票人证件号码
     */
    private String drawerIdNo;

    /**
     * 出票人客户号
     */
    private String drawerCusId;

    /**
     * 出票人姓名
     */
    private String drawerName;

    /**
     * 出票人账号
     */
    private String drawerAccount;

    /**
     * 收款银行号
     */
    private String payeeBankNo;

    /**
     * 收款银行名
     */
    private String payeeBankName;

    /**
     * 收款银行地址
     */
    private String payeeBankAddress;

    /**
     * 收票人证件类型
     */
    private String payeeIdType;

    /**
     * 收票人证件号码
     */
    private String payeeIdNo;

    /**
     * 收票人客户号
     */
    private String payeeCusId;

    /**
     * 收票人姓名
     */
    private String payeeName;

    /**
     * 收票人账号
     */
    private String payeeAccount;

    /**
     * 电票交易类型#2539
     */
    private String tradeType;

    /**
     * 是否已使用#0000
     */
    private String bused;

    /**
     * '电票发的申请日期
     */
    private Date appDate;

    private String aid;
    private String createdBy;
    private String createdOrg;
    private Date createdDate;
    private String lastModifiedBy;
    private String lastModifiedOrg;
    private Date lastModifiedDate;
}