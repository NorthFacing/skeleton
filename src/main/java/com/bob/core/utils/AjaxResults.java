package com.bob.core.utils;

import com.bob.core.contants.ResultCode;

/**
 * Ajax操作结果封装类
 * 
 * @since v0.0.1
 * @author Bob
 * @created 2015年7月6日 下午2:26:16
 */
public class AjaxResults {

    /** 信息码表 */
    private int code;
    /** 信息文字描述 */
    private String msg;
    /** 返回对象 */
    private Object data;

    /**
     * 操作成功，需要返回对象
     * 
     * @param data 返回对象
     * @since v0.0.1
     * @author Bob
     * @created 2015年7月6日 下午1:48:02
     */
    public AjaxResults(Object data) {
        this.code = ResultCode.SUCCESS;
        this.data = data;
    }

    /**
     * 操作失败，返回失败信息
     * 
     * @param code 失败信息码表
     * @param msg 失败信息文字描述
     * @since v0.0.1
     * @author Bob
     * @created 2015年7月6日 下午1:49:03
     */
    public AjaxResults(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通用返回结果
     * 
     * @param code 操作结果码表
     * @param msg 操作结果文字信息描述
     * @param data 操作信息返回值
     * @since v0.0.1
     * @author Bob
     * @created 2015年7月6日 下午1:54:21
     */
    public AjaxResults(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 操作成功，不需要返回对象
     * 
     * @since v0.0.1
     * @author Bob
     * @created 2015年7月6日 下午1:55:43
     */
    public static AjaxResults success() {
        return new AjaxResults(ResultCode.SUCCESS);
    }

    /**
     * 操作失败，未知系统错误
     * 
     * @since v0.0.1
     * @author Bob
     * @created 2015年7月6日 下午2:01:01
     */
    public static AjaxResults failed() {
        return new AjaxResults(ResultCode.FAILED);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
