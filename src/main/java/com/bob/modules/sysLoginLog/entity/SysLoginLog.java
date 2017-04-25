package com.bob.modules.sysLoginLog.entity;

import com.bob.core.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * SysLoginLog
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
@Setter
@Getter
@ToString
public class SysLoginLog extends BaseEntity {

  private String userId;    //  用户ID
  private String userName;    //  用户名
  private LocalDateTime loginTime;    //  登陆时间
  private String loginIp;    //  登陆IP
  private Integer status;    //  状态

}
