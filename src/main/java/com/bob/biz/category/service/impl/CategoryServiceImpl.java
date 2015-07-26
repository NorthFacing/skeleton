package com.bob.biz.category.service.impl;

import org.springframework.stereotype.Service;

import com.bob.biz.category.model.Category;
import com.bob.biz.category.model.CategoryVo;
import com.bob.biz.category.service.CategoryService;
import com.bob.core.base.service.impl.BaseServiceImpl;

/**
 * categoryServiceImpl
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 20:54:42
 */
@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category, CategoryVo> implements CategoryService {

}