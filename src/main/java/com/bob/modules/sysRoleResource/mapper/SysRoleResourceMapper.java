package com.bob.modules.sysRoleResource.mapper;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.utils.myBatis.CRUDTemplate;
import com.bob.modules.sysRoleResource.entity.SysRoleResource;
import com.bob.modules.sysRoleResource.entity.SysRoleResourceVo;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * SysRoleResourceMapper
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {

  @SelectProvider(type = CRUDTemplate.class, method = "select")
  SysRoleResourceVo selectVo(SysRoleResource entity);

  @SelectProvider(type = CRUDTemplate.class, method = "select")
  List<SysRoleResourceVo> selectVoList(SysRoleResource entity);

  void insertBatch(List<SysRoleResource> list);

  void deleteByRoleId(String id);
}