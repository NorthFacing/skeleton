package com.bob.biz.brand.service.impl;

import org.springframework.stereotype.Service;

import com.bob.biz.brand.model.Brand;
import com.bob.biz.brand.model.BrandVo;
import com.bob.biz.brand.service.BrandService;
import com.bob.core.base.service.impl.BaseServiceImpl;

/**
 * brandServiceImpl
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 20:54:42
 */
@Service("brandService")
public class BrandServiceImpl extends BaseServiceImpl<Brand, BrandVo> implements BrandService {

}