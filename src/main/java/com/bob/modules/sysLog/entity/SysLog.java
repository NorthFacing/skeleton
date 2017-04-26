package com.bob.modules.sysLog.entity;

import com.bob.core.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * SysLog
 *
 * @author Bob
 * @Date 2016-3-3 9:27:06
 * @since v0.1
 */
@Setter
@Getter
@ToString
public class SysLog extends BaseEntity {

  private String userName;    //  用户姓名
  private String userIp;    //  IP
  private String dataId;    //  数据ID
  private String oprName;    //  操作名称
  private String oprType;    //  操作类型
  private LocalDateTime oprTime;    //  操作时间
  private String module;    //  模块
  private String url;    //  URL地址
  private String inParams;    //  入参
  private String outParams;    //  出参

}
