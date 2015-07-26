package com.bob.biz.hotel.model;

import com.bob.core.base.model.BaseModel;

/**
 * hotel
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-25 15:14:35
 */
public class Hotel extends BaseModel {

    private String managerId;    // 酒店经理
    private String orgId;    // 组织ID
    private String orgCode;    // 组织CODE
    private String name;    // 酒店名称
    private String mobile;    // 酒店电话
    private String telePhone;    // 酒店手机
    private String address;    // 酒店详细地址
    private Boolean isDelete;   // 是否删除

    /** 酒店经理 */
    public String getManagerId() {
        return managerId;
    }

    public Hotel setManagerId(String managerId) {
        this.managerId = managerId;
        return this;
    }

    /** 组织ID */
    public String getOrgId() {
        return orgId;
    }

    public Hotel setOrgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    /** 组织CODE */
    public String getOrgCode() {
        return orgCode;
    }

    public Hotel setOrgCode(String orgCode) {
        this.orgCode = orgCode;
        return this;
    }

    /** 酒店名称 */
    public String getName() {
        return name;
    }

    public Hotel setName(String name) {
        this.name = name;
        return this;
    }

    /** 酒店电话 */
    public String getMobile() {
        return mobile;
    }

    public Hotel setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    /** 酒店手机 */
    public String getTelePhone() {
        return telePhone;
    }

    public Hotel setTelePhone(String telePhone) {
        this.telePhone = telePhone;
        return this;
    }

    /** 酒店详细地址 */
    public String getAddress() {
        return address;
    }

    public Hotel setAddress(String address) {
        this.address = address;
        return this;
    }

    /** 是否删除 */
    public Boolean getIsDelete() {
        return isDelete;
    }

    public Hotel setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Hotel");
        sb.append(" [ ");
        sb.append("id=").append(this.id).append(", ");
        sb.append("managerId=").append(this.managerId).append(", ");
        sb.append("orgId=").append(this.orgId).append(", ");
        sb.append("orgCode=").append(this.orgCode).append(", ");
        sb.append("name=").append(this.name).append(", ");
        sb.append("mobile=").append(this.mobile).append(", ");
        sb.append("telePhone=").append(this.telePhone).append(", ");
        sb.append("address=").append(this.address).append(", ");
        sb.append("isDelete=").append(this.isDelete).append(", ");
        sb.append("createTime=").append(this.createTime).append(", ");
        sb.append("createUser=").append(this.createUser).append(", ");
        sb.append("updateTime=").append(this.updateTime).append(", ");
        sb.append("updateUser=").append(this.updateUser);
        sb.append(" ]");
        return sb.toString();
    }
}
