package com.mall.biz.organization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.biz.organization.mapper.OrganizationMapper;
import com.mall.biz.organization.model.Organization;
import com.mall.biz.organization.model.OrganizationVo;
import com.mall.biz.organization.service.OrganizationService;
import com.mall.core.base.service.impl.BaseServiceImpl;

/**
 * organizationServiceImpl
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 15:19:55
 */
@Service("organizationService")
public class OrganizationServiceImpl extends BaseServiceImpl<Organization, OrganizationVo> implements
    OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public String getCodeByParentId(String parentId) {
        String code = organizationMapper.getCodeByParentId(parentId);
        if (code == null) {
            return "1";
        } else {
            String maxCode = code.substring(code.lastIndexOf(".") + 1);
            Integer next = Integer.parseInt(maxCode) + 1;
            return next.toString();
        }
    }

}