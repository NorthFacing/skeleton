package com.mall.biz.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.biz.category.mapper.CategoryMapper;
import com.mall.biz.category.model.Category;
import com.mall.biz.category.model.CategoryVo;
import com.mall.biz.category.service.CategoryService;
import com.mall.core.base.service.impl.BaseServiceImpl;

/**
 * categoryServiceImpl
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-21 20:54:42
 */
@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category, CategoryVo> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageInfo<Category> getPage(int pageNum, int pageSize, CategoryVo categoryVo) {
        PageHelper.startPage(pageNum, pageSize);
        List<Category> list = categoryMapper.select(categoryVo);
        return new PageInfo<Category>(list);
    }
}