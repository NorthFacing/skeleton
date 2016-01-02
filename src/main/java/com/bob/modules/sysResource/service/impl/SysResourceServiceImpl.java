package com.bob.modules.sysResource.service.impl;

import com.bob.modules.sysResource.mapper.SysResourceMapper;
import com.bob.modules.sysResource.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysResourceServiceImpl
 *
 * @author Bob
 * @Date 2015-12-30 22:03:45
 * @since v0.0.1
 */
@Service("sysResourceService")
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    public void getById(String id) {

    }

}