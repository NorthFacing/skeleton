package com.mall.biz.brand.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.biz.brand.mapper.BrandMapper;
import com.mall.biz.brand.model.Brand;
import com.mall.biz.brand.model.BrandVo;
import com.mall.biz.brand.service.BrandService;
import com.mall.core.base.service.impl.BaseServiceImpl;

/**
 * brandServiceImpl
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 20:54:42
 */
@Service("brandService")
public class BrandServiceImpl extends BaseServiceImpl<Brand, BrandVo> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageInfo<Brand> getPage(int pageNum, int pageSize, BrandVo brandVo) {
        PageHelper.startPage(pageNum, pageSize);
        List<Brand> list = brandMapper.select(brandVo);
        return new PageInfo<Brand>(list);
    }

}