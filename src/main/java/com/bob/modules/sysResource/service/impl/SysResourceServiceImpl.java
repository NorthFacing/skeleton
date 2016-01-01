package com.bob.modules.sysResource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.entity.SysResourceVo;
import com.bob.modules.sysResource.mapper.SysResourceMapper;
import com.bob.modules.sysResource.service.SysResourceService;

/**
 * SysResourceServiceImpl
 * @since v0.0.1
 * @author Bob
 * @Date 2015-12-30 22:03:45
 */
@Service("sysResourceService")
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    public void getById(String id){

        sysResourceMapper.getVoById("id");
        sysResourceMapper.getList(new SysResourceVo());
        sysResourceMapper.getVoList(new SysResourceVo());
    }

}