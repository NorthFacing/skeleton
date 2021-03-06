package com.bob.modules.sysResource.mapper;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.utils.myBatis.CRUDTemplate;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.entity.SysResourceVo;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface SysResourceMapper extends BaseMapper<SysResource> {

  @SelectProvider(type = CRUDTemplate.class, method = "select")
  SysResourceVo selectVo(SysResource entity);

  @SelectProvider(type = CRUDTemplate.class, method = "select")
  List<SysResourceVo> selectVoList(SysResource entity);

  List<SysResource> getResourcesByUserId(String uId);

  List<SysResource> getAllResources();

}