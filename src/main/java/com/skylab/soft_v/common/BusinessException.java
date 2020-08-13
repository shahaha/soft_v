package com.skylab.soft_v.common;

public class BusinessException extends RuntimeException{
    /**
     * 异常编号
     */
    private final int messageCode;
    /**
     * 对messageCode 异常信息进行补充说明
     */
    private final String detailMessage;
    public BusinessException(int messageCode, String message) {
        super(message);
        this.messageCode = messageCode;
        this.detailMessage = message;
    }
    public int getMessageCode() {
        return messageCode;
    }
    public String getDetailMessage() {
        return detailMessage;
    }
}
