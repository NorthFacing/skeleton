package com.bob.modules.sysRole.service.impl;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.base.service.impl.BaseServiceImpl;
import com.bob.modules.sysRole.entity.SysRole;
import com.bob.modules.sysRole.entity.SysRoleVo;
import com.bob.modules.sysRole.mapper.SysRoleMapper;
import com.bob.modules.sysRole.service.SysRoleService;
import com.bob.modules.sysRoleResource.entity.SysRoleResource;
import com.bob.modules.sysRoleResource.mapper.SysRoleResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SysRoleServiceImpl
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
@Slf4j
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

  @Autowired
  private SysRoleMapper sysRoleMapper;
  @Autowired
  private SysRoleResourceMapper sysRoleResourceMapper;

  @Override
  public SysRole getEntity() {
    return new SysRole();
  }

  @Override
  public BaseMapper<SysRole> getMapper() {
    return sysRoleMapper;
  }

  @Override
  public String saveVo(SysRoleVo entity) {
    SysRole role = this.getEntity();
    BeanUtils.copyProperties(entity, role);
    // 保存角色
    if (StringUtils.isEmpty(entity.getId())) {
      sysRoleMapper.insert(role);
    } else {
      sysRoleMapper.update(role);
      sysRoleResourceMapper.deleteByRoleId(entity.getId());
    }
    // id赋值，为了下面的方法少传一个参数
    entity.setId(role.getId());
    // 保存角色和权限关联关系
    insertRoleResource(entity);
    return entity.getId();
  }

  private void insertRoleResource(SysRoleVo entity) {
    List<String> resourceIds = entity.getResourceIds();
    if (CollectionUtils.isNotEmpty(resourceIds)) {

      List<SysRoleResource> list = resourceIds.parallelStream()
        .filter(rsId -> !rsId.isEmpty())
        .map(rsId -> {
          SysRoleResource rs = new SysRoleResource();
          rs.setRoleId(entity.getId());
          rs.setResourceId(rsId);
          return rs;
        })
        .collect(Collectors.toList());

      sysRoleResourceMapper.insertBatch(list);
    }
  }

}