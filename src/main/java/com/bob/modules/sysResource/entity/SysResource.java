package com.bob.modules.sysResource.entity;

import com.bob.core.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SysResource
 *
 * @author Bob
 * @Date 2016-1-6 16:03:06
 * @since v0.1
 */
@Setter
@Getter
@ToString
public class SysResource extends BaseEntity {

  private String parentId;    //  父节点ID
  private String name;    //  名称
  private String type;    //  类型：1，菜单权限；2，按钮权限
  private String shiroKey;    //  shiro判断资源权限标识符
  private String resUrl;    //  菜单路径（菜单类型时必填）
  private Integer resLevel;    //  菜单层级（菜单类型时必填）
  private Integer resPriority;    //  菜单顺序（菜单类型时必填）
  private String resCode;    //  菜单CODE（自动生成）
  private String description;    //  描述
  private Integer status;    //  状态

}
