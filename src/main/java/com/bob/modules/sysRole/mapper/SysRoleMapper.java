package com.bob.modules.sysRole.mapper;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.utils.myBatis.CRUDTemplate;
import com.bob.modules.sysRole.entity.SysRole;
import com.bob.modules.sysRole.entity.SysRoleVo;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * SysRoleMapper
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
public interface SysRoleMapper extends BaseMapper<SysRole, SysRoleVo> {

    @SelectProvider(type = CRUDTemplate.class, method = "select")
    SysRole select(SysRole entity);

    @SelectProvider(type = CRUDTemplate.class, method = "select")
    SysRoleVo selectVo(SysRole entity);

    @SelectProvider(type = CRUDTemplate.class, method = "select")
    List<SysRole> selectList(SysRole entity);

    @SelectProvider(type = CRUDTemplate.class, method = "select")
    List<SysRoleVo> selectVoList(SysRole entity);

}