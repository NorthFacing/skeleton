package com.bob.core.session;

import java.io.Serializable;

public class LoginUser implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  private String userName;
  private String password;
  private Long telephone;
  private String corpId;
  private String deptId;
  private String status;
  private String salt;

  public LoginUser() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getTelephone() {
    return telephone;
  }

  public void setTelephone(Long telephone) {
    this.telephone = telephone;
  }

  public String getCorpId() {
    return corpId;
  }

  public void setCorpId(String corpId) {
    this.corpId = corpId;
  }

  public String getDeptId() {
    return deptId;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }
}
