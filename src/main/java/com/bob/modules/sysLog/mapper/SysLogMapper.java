package com.bob.modules.sysLog.mapper;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.utils.myBatis.CRUDTemplate;
import com.bob.modules.sysLog.entity.SysLog;
import com.bob.modules.sysLog.entity.SysLogVo;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * SysLogMapper
 *
 * @author Bob
 * @Date 2016-3-3 9:27:06
 * @since v0.1
 */
public interface SysLogMapper extends BaseMapper<SysLog> {

  @SelectProvider(type = CRUDTemplate.class, method = "select")
  SysLogVo selectVo(SysLog entity);

  @SelectProvider(type = CRUDTemplate.class, method = "select")
  List<SysLogVo> selectVoList(SysLog entity);

}