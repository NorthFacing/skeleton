package com.mall.biz.simple.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.mall.biz.simple.model.Simple;

/**
 * demo
 */
public interface SimpleService {

    public Integer add(Simple model);

    public Simple getById(Integer id);

    public List<Simple> getList(Simple Demoodel);

    public PageInfo<Simple> getPage(int pageNum, int pageSize, Simple model);

    public void updateById(Simple model);

    public boolean delById(Integer id);

}