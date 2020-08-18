package com.skylab.soft_v.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 软件表(Soft)实体类
 *
 * @author xw
 * @since 2020-08-12 18:27:01
 */
@Data
public class Soft implements Serializable {
    private static final long serialVersionUID = 762661091516825372L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 编码
     */
    private String code;
    /**
     * 使用型号
     */
    private Integer modelId;
    /**
     * 芯片组
     */
    private Integer chipId;
    /**
     * 版本号
     */
    private String version;
    /**
     * 工程师
     */
    private Integer engineer;
    /**
     * 销售
     */
    private Integer sale;
    /**
     * 初始客户
     */
    private String initialCustomer;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否使用
     */
    private Boolean inUse;
    /**
     * 上传日期
     */
    private Date uploadDate;
    /**
     * 生产烧录简介
     */
    private String burningProcess;
    /**
     * 配置信息
     */
    private String configInfo;
    /**
     * 定制硬件
     */
    private Boolean customHardware;
    /**
     * 串口配置
     */
    private String serialPortConfig;
    /**
     * flash(Byte)
     */
    private Integer flash;
    /**
     * WAN
     */
    private String WAN;
    /**
     * DDR(Byte)
     */
    private Integer DDR;
    /**
     * IRAM(Byte)
     */
    private Integer IRAM;
    /**
     * 天线开短路检测
     */
    private Boolean openShortDetection;
    /**
     * 波特率
     */
    private Integer baudRate;
    /**
     * 保存星历
     */
    private Boolean ephemeris;
    /**
     * 小数精度
     */
    private Integer accuracy;
    /**
     * TCXO(晶振)
     */
    private String TCXO;
    /**
     * 信号频率
     */
    private String frequency;
    /**
     * 是否需要烧录标准固件
     */
    private Boolean needBurnFirmware;
    /**
     * 标签打印工具
     */
    private Integer labelPrintTool;
    /**
     * GPIO检测软件
     */
    private Integer GPIODetection;
    /**
     * 标准固件批量烧录工具
     */
    private Integer burnTool;
    /**
     * 标准固件检测工具
     */
    private Integer detectionTool;
    /**
     * 产测工具
     */
    private Integer productTestTool;
    /**
     * 产测固件编码
     */
    private String productTestCode;
    /**
     * 种类
     */
    private Integer category;
    /**
     * 附件地址
     */
    private String address;
    /**
     * 预留列1
     */
    private String column1;
    /**
     * 预留列2
     */
    private String column2;
    /**
     * 预留列3
     */
    private String column3;
    /**
     * 预留列4
     */
    private String column4;
    /**
     * 预留列5
     */
    private String column5;
    /**
     * 预留列6
     */
    private String column6;
    /**
     * 预留列7
     */
    private String column7;
    /**
     * 预留列8
     */
    private String column8;
    /**
     * 预留列9
     */
    private String column9;
    /**
     * 预留列10
     */
    private String column10;
    /**
     * 预留列11
     */
    private String column11;
    /**
     * 预留列12
     */
    private String column12;
    /**
     * 预留列13
     */
    private String column13;
    /**
     * 预留列14
     */
    private String column14;
    /**
     * 预留列15
     */
    private String column15;
    /**
     * 预留列16
     */
    private String column16;
    /**
     * 预留列17
     */
    private String column17;
    /**
     * 预留列18
     */
    private String column18;
    /**
     * 预留列19
     */
    private String column19;
    /**
     * 预留列20
     */
    private String column20;
    /**
     * 预留列21
     */
    private String column21;
    /**
     * 预留列22
     */
    private String column22;
    /**
     * 预留列23
     */
    private String column23;
    /**
     * 预留列24
     */
    private String column24;
    /**
     * 预留列25
     */
    private String column25;
    /**
     * 预留列26
     */
    private String column26;
    /**
     * 预留列27
     */
    private String column27;
    /**
     * 预留列28
     */
    private String column28;
    /**
     * 预留列29
     */
    private String column29;
    /**
     * 预留列30
     */
    private String column30;


}