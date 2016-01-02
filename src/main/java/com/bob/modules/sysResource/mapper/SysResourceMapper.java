package com.bob.modules.sysResource.mapper;

import com.bob.core.base.mapper.BaseMapper;

import com.bob.core.utils.myBatis.CRUDTemplate;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.entity.SysResourceVo;
import org.apache.ibatis.annotations.SelectProvider;

public interface SysResourceMapper extends BaseMapper<SysResource, SysResourceVo> {

    @Override
    @SelectProvider(type = CRUDTemplate.class, method = "select")
    SysResource select(SysResource entity);

}