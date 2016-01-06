package com.bob.modules.sysUser.mapper;

import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.utils.myBatis.CRUDTemplate;
import com.bob.modules.sysUser.entity.SysUser;
import com.bob.modules.sysUser.entity.SysUserQuery;
import com.bob.modules.sysUser.entity.SysUserVo;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * SysUserMapper
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.1
 */
public interface SysUserMapper extends BaseMapper<SysUser, SysUserVo, SysUserQuery> {

    @SelectProvider(type = CRUDTemplate.class, method = "select")
    SysUser select(SysUser entity);

    @SelectProvider(type = CRUDTemplate.class, method = "select")
    SysUserVo selectVo(SysUser entity);

    @SelectProvider(type = CRUDTemplate.class, method = "select")
    List<SysUser> selectList(SysUser entity);

    @SelectProvider(type = CRUDTemplate.class, method = "select")
    List<SysUserVo> selectVoList(SysUser entity);

    SysUser getByUserName(String name);

}