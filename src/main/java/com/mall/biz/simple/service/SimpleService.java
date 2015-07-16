package com.mall.biz.simple.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.mall.biz.simple.model.Simple;

/**
 * demo
 */
public interface SimpleService {

    public String add(Simple model);

    public Simple getById(String id);

    public List<Simple> getList(Simple Demoodel);

    public PageInfo<Simple> getPage(int pageNum, int pageSize, Simple model);

    public void updateById(Simple model);

    public boolean delById(String id);

}