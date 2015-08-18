package com.bob.modules.user.model;

import com.bob.core.base.model.BaseModel;

/**
 * user
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-25 13:22:46
 */
public class User extends BaseModel {

    private String orgId;       // 层级ID
    private String orgCode;     // 层级code
    private String userName;    // 用户名
    private String passWord;    // 密码
    private String nickName;    // 昵称
    private String userRole;    // 角色
    private boolean isDelete;    // 是否删除

    /** 层级ID */
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /** 层级code */
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /** 用户名 */
    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /** 密码 */
    public String getPassWord() {
        return passWord;
    }

    public User setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    /** 昵称 */
    public String getNickName() {
        return nickName;
    }

    public User setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    /** 角色 */
    public String getUserRole() {
        return userRole;
    }

    public User setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }

    /** 是否删除 */
    public boolean getIsDelete() {
        return isDelete;
    }

    public User setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User");
        sb.append(" [ ");
        sb.append("id=").append(this.id).append(", ");
        sb.append("userName=").append(this.userName).append(", ");
        sb.append("passWord=").append(this.passWord).append(", ");
        sb.append("nickName=").append(this.nickName).append(", ");
        sb.append("userRole=").append(this.userRole).append(", ");
        sb.append("isDelete=").append(this.isDelete).append(", ");
        sb.append("createTime=").append(this.createTime).append(", ");
        sb.append("createUser=").append(this.createUser).append(", ");
        sb.append("updateTime=").append(this.updateTime).append(", ");
        sb.append("updateUser=").append(this.updateUser);
        sb.append(" ]");
        return sb.toString();
    }
}
