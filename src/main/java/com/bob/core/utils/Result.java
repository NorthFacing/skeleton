package com.bob.core.utils;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 5616070434977448606L;

    private String code;
    private String msg;
    private T data;

    public Result() {
        super();
    }


    public static Result fail() {
        Result result = new Result<>();
        result.setCode(ResultEnums.ERROR.getCode());
        result.setMsg(ResultEnums.ERROR.getMsg());
        return result;
    }

    public static Result fail(String msg) {
        Result result = new Result<>();
        result.setCode(ResultEnums.ERROR.getCode());
        result.setMsg(msg);
        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(ResultEnums.SUCCESS.getCode());
        result.setMsg(ResultEnums.SUCCESS.getMsg());
        return result;
    }

    public static <V> Result<V> success(V data) {
        Result result = new Result<>();
        result.setCode(ResultEnums.SUCCESS.getCode());
        result.setMsg(ResultEnums.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    public Result enumResult(ResultEnums resultEnums) {
        Result result = new Result<>();
        result.setCode(resultEnums.getCode());
        result.setMsg(resultEnums.getMsg());
        return result;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
        this.setMsg(ResultEnums.SUCCESS.getMsg());
        this.setCode(ResultEnums.SUCCESS.getCode());
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static void main(String[] args) {
        Result<String> result = Result.success();
        System.out.println(result.toString());
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
