package com.bob.modules.sysLog.service.impl;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.base.service.impl.BaseServiceImpl;
import com.bob.modules.sysLog.entity.SysLog;
import com.bob.modules.sysLog.entity.SysLogQuery;
import com.bob.modules.sysLog.entity.SysLogVo;
import com.bob.modules.sysLog.mapper.SysLogMapper;
import com.bob.modules.sysLog.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Bob on 2016/4/8.
 */
@Service("SysLogService")
public class SysLogServiceImpl
        extends BaseServiceImpl<SysLog, SysLogVo, SysLogQuery>
        implements SysLogService {

  @Autowired
  private SysLogMapper sysLogMapper;

  @Override
  public SysLog getEntity() {
    return new SysLog();
  }

  @Override
  public BaseMapper<SysLog, SysLogVo, SysLogQuery> getMapper() {
    return sysLogMapper;
  }
}
