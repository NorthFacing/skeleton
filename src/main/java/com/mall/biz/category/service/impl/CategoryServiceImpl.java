package com.mall.biz.category.service.impl;

import org.springframework.stereotype.Service;

import com.mall.biz.category.model.Category;
import com.mall.biz.category.model.CategoryVo;
import com.mall.biz.category.service.CategoryService;
import com.mall.core.base.service.impl.BaseServiceImpl;

/**
 * categoryServiceImpl
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 20:54:42
 */
@Service("categoryService")
public  class CategoryServiceImpl extends BaseServiceImpl<Category, CategoryVo> implements CategoryService {

}