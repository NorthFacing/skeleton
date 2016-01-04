package com.bob.modules.sysLoginLog.service.impl;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.base.service.impl.BaseServiceImpl;
import com.bob.modules.sysLoginLog.entity.SysLoginLog;
import com.bob.modules.sysLoginLog.entity.SysLoginLogQuery;
import com.bob.modules.sysLoginLog.entity.SysLoginLogVo;
import com.bob.modules.sysLoginLog.mapper.SysLoginLogMapper;
import com.bob.modules.sysLoginLog.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysLoginLogServiceImpl
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
@Service("sysLoginLogService")
public class SysLoginLogServiceImpl
        extends BaseServiceImpl<SysLoginLog, SysLoginLogVo, SysLoginLogQuery>
        implements SysLoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public SysLoginLog getEntity() {
        return new SysLoginLog();
    }

    @Override
    public BaseMapper<SysLoginLog, SysLoginLogVo, SysLoginLogQuery> getBaseMapper() {
        return sysLoginLogMapper;
    }
}