package com.bob.modules.sysRole.entity;

import com.bob.core.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * SysRole
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
@Setter
@Getter
@ToString
public class SysRole extends BaseEntity {

  @NonNull
  private String name;    //  名称
  @NonNull
  private String description;    //  描述
  @NonNull
  private Integer status;    //  状态

}
