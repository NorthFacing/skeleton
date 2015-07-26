package com.bob.biz.organization.service;

import com.bob.biz.organization.model.Organization;
import com.bob.biz.organization.model.OrganizationVo;
import com.bob.core.base.service.BaseService;

/**
 * organizationService
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 15:19:55
 */
public interface OrganizationService extends BaseService<Organization, OrganizationVo> {

    String getCodeByParentId(String parentId);
}