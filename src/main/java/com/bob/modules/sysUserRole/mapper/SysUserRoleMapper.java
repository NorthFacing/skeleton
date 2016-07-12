package com.bob.modules.sysUserRole.mapper;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.utils.myBatis.CRUDTemplate;
import com.bob.modules.sysUserRole.entity.SysUserRole;
import com.bob.modules.sysUserRole.entity.SysUserRoleQuery;
import com.bob.modules.sysUserRole.entity.SysUserRoleVo;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * SysUserRoleMapper
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

  @SelectProvider(type = CRUDTemplate.class, method = "select")
  SysUserRole select(SysUserRole entity);

  @SelectProvider(type = CRUDTemplate.class, method = "select")
  SysUserRoleVo selectVo(SysUserRole entity);

  @SelectProvider(type = CRUDTemplate.class, method = "select")
  List<SysUserRole> selectList(SysUserRole entity);

  @SelectProvider(type = CRUDTemplate.class, method = "select")
  List<SysUserRoleVo> selectVoList(SysUserRole entity);

}