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
        result.setCode(ResultEnums.DEFAULT_ERROR.getCode());
        result.setMsg(ResultEnums.DEFAULT_ERROR.getMsg());
        return result;
    }

    public static Result fail(String msg) {
        Result result = new Result<>();
        result.setCode(ResultEnums.DEFAULT_ERROR.getCode());
        result.setMsg(msg);
        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(ResultEnums.OK.getCode());
        result.setMsg(ResultEnums.OK.getMsg());
        return result;
    }

    public static <V> Result<V> success(V data) {
        Result result = new Result<>();
        result.setCode(ResultEnums.OK.getCode());
        result.setMsg(ResultEnums.OK.getMsg());
        result.setData(data);
        return result;
    }

    public static void main(String[] args) {
        Result<String> result = Result.success();
        System.out.println(result.toString());
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
        this.setMsg(ResultEnums.OK.getMsg());
        this.setCode(ResultEnums.OK.getCode());
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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
