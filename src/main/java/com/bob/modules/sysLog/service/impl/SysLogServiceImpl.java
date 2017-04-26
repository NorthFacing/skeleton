package com.bob.modules.sysLog.service.impl;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.base.service.impl.BaseServiceImpl;
import com.bob.modules.sysLog.entity.SysLog;
import com.bob.modules.sysLog.mapper.SysLogMapper;
import com.bob.modules.sysLog.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Bob on 2016/4/8.
 */
@Slf4j
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service("SysLogService")
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {

  @Autowired
  private SysLogMapper sysLogMapper;

  @Override
  public SysLog getEntity() {
    return new SysLog();
  }

  @Override
  public BaseMapper<SysLog> getMapper() {
    return sysLogMapper;
  }
}
