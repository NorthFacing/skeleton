package com.bob.modules.sysOrgnization.service.impl;

import com.bob.core.base.service.impl.BaseServiceImpl;
import com.bob.modules.sysOrgnization.entity.SysOrgnization;
import com.bob.modules.sysOrgnization.entity.SysOrgnizationVo;
import com.bob.modules.sysOrgnization.mapper.SysOrgnizationMapper;
import com.bob.modules.sysOrgnization.service.SysOrgnizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysOrgnizationServiceImpl
 *
 * @author Bob
 * @Date 2016-1-3 22:55:44
 * @since v0.1
 */
@Service("sysOrgnizationService")
public class SysOrgnizationServiceImpl extends BaseServiceImpl<SysOrgnization, SysOrgnizationVo> implements SysOrgnizationService {

    @Autowired
    private SysOrgnizationMapper sysOrgnizationMapper;

}