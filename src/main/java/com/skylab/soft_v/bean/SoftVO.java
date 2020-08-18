package com.skylab.soft_v.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SoftVO {
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
    private String modelId;
    /**
     * 芯片组
     */
    private String chipId;
    /**
     * 版本号
     */
    private String version;
    /**
     * 工程师
     */
    private String engineer;
    /**
     * 销售
     */
    private String sale;
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
    private String inUse;
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
    private String customHardware;
    /**
     * 串口配置
     */
    private String serialPortConfig;
    /**
     * flash(Byte)
     */
    private String flash;
    /**
     * WAN
     */
    @JsonProperty("WAN")
    private String WAN;
    /**
     * DDR(Byte)
     */
    @JsonProperty("DDR")
    private String DDR;
    /**
     * IRAM(Byte)
     */
    @JsonProperty("IRAM")
    private String IRAM;
    /**
     * 天线开短路检测
     */
    private String openShortDetection;
    /**
     * 波特率
     */
    private Integer baudRate;
    /**
     * 保存星历
     */
    private String ephemeris;
    /**
     * 小数精度
     */
    private Integer accuracy;
    /**
     * TCXO(晶振)
     */
    @JsonProperty("TCXO")
    private String TCXO;
    /**
     * 信号频率
     */
    private String frequency;
    /**
     * 是否需要烧录标准固件
     */
    private String needBurnFirmware;
    /**
     * 标签打印工具
     */
    private String labelPrintTool;
    /**
     * GPIO检测软件
     */
    @JsonProperty("GPIODetection")
    private String GPIODetection;
    /**
     * 标准固件批量烧录工具
     */
    private String burnTool;
    /**
     * 标准固件检测工具
     */
    private String detectionTool;
    /**
     * 产测工具
     */
    private String productTestTool;
    /**
     * 产测固件编码
     */
    private String productTestCode;
    /**
     * 种类
     */
    private String category;
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
