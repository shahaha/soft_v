package com.skylab.soft_v.common;

import lombok.Data;

@Data
public class ResultBean<T> {
    /**
     * 请求是否成功
     */
    private boolean success;
    /**
     * 响应编码
     */
    private int code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;//返回的记录集合

    /**
     * 无数据返回，用于增改删
     */
    public static <T> ResultBean<T> success() {
        ResultBean<T> result = new ResultBean<T>();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("Request Success.");
        return result;
    }

    public static <T> ResultBean<T> success(T data) {
        ResultBean<T> result = new ResultBean<T>();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("Request Success.");
        result.setData(data);
        return result;
    }

    public static <T> ResultBean<T> success(String msg) {
        ResultBean<T> result = new ResultBean<T>();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg(msg);
        return result;
    }

    public static <T> ResultBean<T> success(String msg, T data) {
        ResultBean<T> result = new ResultBean<T>();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> ResultBean<T> error() {
        ResultBean<T> result = new ResultBean<T>();
        result.setSuccess(false);
        result.setCode(400);
        result.setMsg("Bad Request.");
        return result;
    }

    public static <T> ResultBean<T> error(String msg) {
        ResultBean<T> result = new ResultBean<T>();
        result.setSuccess(false);
        result.setCode(400);
        result.setMsg(msg);
        return result;
    }

    public static <T> ResultBean<T> error(String msg,T data) {
        ResultBean<T> result = new ResultBean<T>();
        result.setSuccess(false);
        result.setCode(400);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> ResultBean<T> internalError() {
        ResultBean<T> result = new ResultBean<T>();
        result.setSuccess(false);
        result.setCode(500);
        result.setMsg("Internal Server Error.");
        return result;
    }

    public static <T> ResultBean<T> internalError(String msg) {
        ResultBean<T> result = new ResultBean<T>();
        result.setSuccess(false);
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

    public static <T> ResultBean<T> internalError(String msg,T data) {
        ResultBean<T> result = new ResultBean<T>();
        result.setSuccess(false);
        result.setCode(500);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

}