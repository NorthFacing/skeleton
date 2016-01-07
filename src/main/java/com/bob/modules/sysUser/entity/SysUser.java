package com.bob.modules.sysUser.entity;

import com.bob.core.base.entity.BaseEntity;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * SysUser
 *
 * @author Bob
 * @Date 2016-1-7 16:13:50
 * @since v0.1
 */
public class SysUser extends BaseEntity {

    private String userName;    //  用户名/登录名
    private String nickName;    //  昵称/显示名
    private String passWord;    //  密码
    private String salt;    //  加密盐
    private String depId;    //  归属部门ID
    private String depCode;    //  归属部门CODE
    private Integer status;    //  状态


    /**
     * 用户名/登录名
     */
    public String getUserName() {
        return userName;
    }

    public SysUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }


    /**
     * 昵称/显示名
     */
    public String getNickName() {
        return nickName;
    }

    public SysUser setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }


    /**
     * 密码
     */
    public String getPassWord() {
        return passWord;
    }

    public SysUser setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }


    /**
     * 加密盐
     */
    public String getSalt() {
        return salt;
    }

    public SysUser setSalt(String salt) {
        this.salt = salt;
        return this;
    }


    /**
     * 归属部门ID
     */
    public String getDepId() {
        return depId;
    }

    public SysUser setDepId(String depId) {
        this.depId = depId;
        return this;
    }


    /**
     * 归属部门CODE
     */
    public String getDepCode() {
        return depCode;
    }

    public SysUser setDepCode(String depCode) {
        this.depCode = depCode;
        return this;
    }


    /**
     * 状态
     */
    public Integer getStatus() {
        return status;
    }

    public SysUser setStatus(Integer status) {
        this.status = status;
        return this;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
