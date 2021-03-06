package com.bob.modules.sysLoginLog.mapper;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.utils.myBatis.CRUDTemplate;
import com.bob.modules.sysLoginLog.entity.SysLoginLog;
import com.bob.modules.sysLoginLog.entity.SysLoginLogVo;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * SysLoginLogMapper
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {

  @SelectProvider(type = CRUDTemplate.class, method = "select")
  SysLoginLogVo selectVo(SysLoginLog entity);

  @SelectProvider(type = CRUDTemplate.class, method = "select")
  List<SysLoginLogVo> selectVoList(SysLoginLog entity);

}