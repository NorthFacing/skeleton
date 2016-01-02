package com.bob.core.excetpion;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 6876690910488878402L;

    public int code;
    public String msg;

    public BusinessException(int code) {
        this.code = code;
    }

    public BusinessException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
