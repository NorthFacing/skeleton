package com.bob.biz.organization.mapper;

import com.bob.biz.organization.model.Organization;
import com.bob.biz.organization.model.OrganizationVo;
import com.bob.core.base.mapper.BaseMapper;

/**
 * organizationMapper
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 15:19:55
 */
public interface OrganizationMapper extends BaseMapper<Organization, OrganizationVo> {

    String getCodeByParentId(String parentId);
}