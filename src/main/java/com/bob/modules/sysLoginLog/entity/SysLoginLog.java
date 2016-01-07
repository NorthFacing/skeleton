package com.bob.modules.sysLoginLog.entity;

import com.bob.core.base.entity.BaseEntity;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.time.LocalDateTime;

/**
 * SysLoginLog
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
public class SysLoginLog extends BaseEntity {

    private String iD;    //  ID
    private String userId;    //  用户ID
    private String userName;    //  用户名
    private LocalDateTime loginTime;    //  登陆时间
    private String loginIp;    //  登陆IP
    private Integer status;    //  状态


    /**
     * ID
     */
    public String getID() {
        return iD;
    }

    public SysLoginLog setID(String iD) {
        this.iD = iD;
        return this;
    }


    /**
     * 用户ID
     */
    public String getUserId() {
        return userId;
    }

    public SysLoginLog setUserId(String userId) {
        this.userId = userId;
        return this;
    }


    /**
     * 用户名
     */
    public String getUserName() {
        return userName;
    }

    public SysLoginLog setUserName(String userName) {
        this.userName = userName;
        return this;
    }


    /**
     * 登陆时间
     */
    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public SysLoginLog setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
        return this;
    }


    /**
     * 登陆IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    public SysLoginLog setLoginIp(String loginIp) {
        this.loginIp = loginIp;
        return this;
    }


    /**
     * 状态
     */
    public Integer getStatus() {
        return status;
    }

    public SysLoginLog setStatus(Integer status) {
        this.status = status;
        return this;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
