package com.mall.biz.organization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<Organization> getPage(int pageNum, int pageSize, OrganizationVo organizationVo) {
        PageHelper.startPage(pageNum, pageSize);
        List<Organization> list = organizationMapper.select(organizationVo);
        return new PageInfo<Organization>(list);
    }
}