package com.bob.modules.sysOrgnization.entity;

import com.bob.core.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SysOrgnization
 *
 * @author Bob
 * @Date 2016-1-3 22:55:44
 * @since v0.1
 */
@Setter
@Getter
@ToString
public class SysOrgnization extends BaseEntity {

  private String parentId;    //  父节点ID
  private String code;    //  编码
  private String name;    //  名称
  private String fullName;    //  全程
  private Integer status;    //  状态

}
