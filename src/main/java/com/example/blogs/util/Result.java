package com.example.blogs.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ljz
 * @date 2025/10/23
 */
@Data
public class Result<T> implements Serializable {
    private int code;
    private String message;
    private T data;
    /**
     * 成功响应（无数据）
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(null);
        return result;
    }

    /**
     * 成功响应（带数据）
     * @param data 要返回的数据
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    /**
     * 成功响应（自定义消息+数据）
     * @param msg 自定义成功消息
     * @param data 要返回的数据
     */
    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    /**
     * 错误响应（默认错误消息）
     */
    public static <T> Result<T> error() {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMessage("操作失败");
        result.setData(null);
        return result;
    }

    /**
     * 错误响应（自定义错误消息）
     * @param msg 错误描述
     */
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMessage(msg);
        result.setData(null);
        return result;
    }

    /**
     * 错误响应（自定义状态码+消息）
     * @param code 错误状态码（如400-参数错误，404-资源不存在）
     * @param msg 错误描述
     */
    public static <T> Result<T> error(int code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(null);
        return result;
    }
}
