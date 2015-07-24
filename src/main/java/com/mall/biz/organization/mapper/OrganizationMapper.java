package com.mall.biz.organization.mapper;

import com.mall.biz.organization.model.Organization;
import com.mall.biz.organization.model.OrganizationVo;
import com.mall.core.base.mapper.BaseMapper;

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