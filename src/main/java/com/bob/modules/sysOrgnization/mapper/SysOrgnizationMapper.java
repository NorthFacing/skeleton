package com.bob.modules.sysOrgnization.mapper;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.utils.myBatis.CRUDTemplate;
import com.bob.modules.sysOrgnization.entity.SysOrgnization;
import com.bob.modules.sysOrgnization.entity.SysOrgnizationQuery;
import com.bob.modules.sysOrgnization.entity.SysOrgnizationVo;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * SysOrgnizationMapper
 *
 * @author Bob
 * @Date 2016-1-3 22:55:44
 * @since v0.1
 */
public interface SysOrgnizationMapper extends BaseMapper<SysOrgnization, SysOrgnizationVo, SysOrgnizationQuery> {

    @SelectProvider(type = CRUDTemplate.class, method = "select")
    SysOrgnization select(SysOrgnization entity);

    @SelectProvider(type = CRUDTemplate.class, method = "select")
    SysOrgnizationVo selectVo(SysOrgnization entity);

    @SelectProvider(type = CRUDTemplate.class, method = "select")
    List<SysOrgnization> selectList(SysOrgnization entity);

    @SelectProvider(type = CRUDTemplate.class, method = "select")
    List<SysOrgnizationVo> selectVoList(SysOrgnization entity);

}