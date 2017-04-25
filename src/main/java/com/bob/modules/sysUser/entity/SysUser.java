package com.bob.modules.sysUser.entity;

import com.bob.core.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * SysUser
 *
 * @author Bob
 * @Date 2016-1-7 16:13:50
 * @since v0.1
 */
@Setter
@Getter
@ToString
public class SysUser extends BaseEntity {

  @NonNull
  private String userName;    //  用户名/登录名
  @NonNull
  private String nickName;    //  昵称/显示名
  @NonNull
  private String passWord;    //  密码
  @NonNull
  private String salt;    //  加密盐
  @NonNull
  private String depId;    //  归属部门ID
  @NonNull
  private String depCode;    //  归属部门CODE
  @NonNull
  private Integer status;    //  状态

}
