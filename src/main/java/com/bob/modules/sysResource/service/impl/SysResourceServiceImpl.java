package com.bob.modules.sysResource.service.impl;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.base.service.impl.BaseServiceImpl;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.mapper.SysResourceMapper;
import com.bob.modules.sysResource.service.SysResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysResourceServiceImpl
 *
 * @author Bob
 * @Date 2015-12-30 22:03:45
 * @since v0.1
 */
@Slf4j
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service("sysResourceService")
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {

  @Autowired
  private SysResourceMapper sysResourceMapper;

  @Override
  public SysResource getEntity() {
    return new SysResource();
  }

  @Override
  public BaseMapper<SysResource> getMapper() {
    return sysResourceMapper;
  }

}