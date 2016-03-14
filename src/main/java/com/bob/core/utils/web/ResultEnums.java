package com.bob.core.utils.web;

public enum ResultEnums {

    SUCCESS("200", "成功"),
    DEFAULT_ERROR("1000", "系统出错, 请联系管理员!"),
    ERROR("1001", "系统出错,错误未知!"),
    PARAM_ERROR("1002", "提交参数异常, 请联系管理员!"),
    SERVER_INNER_ERROR("1003", "请求错误，服务器内部发生异常！请联系管理员!"),
    SESSION_EXPIRE("1004", "未登录或会话已过期, 请重新登录!"),
    ILLEGAL_ACCESS("1005", "无权限,非法访问!"),
    RECORD_NOT_EXSIT("5001", "不存在此记录!"),
    DATA_SAVE_ERROR("5002", "保存出错!"),
    PARAM_VALIDATE_ERROR("5003", "输入参数不正确！"),
    SUCCESS_BUT_EXIST_ERROR("5004", "操作成功但存在处理不成功记录");

    private String code;

    private String msg;

    private ResultEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
