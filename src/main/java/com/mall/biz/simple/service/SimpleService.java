package com.mall.biz.simple.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.mall.biz.simple.model.SimpleModel;

/**
 * demo
 */
public interface SimpleService {

    public String add(SimpleModel model);

    public SimpleModel getById(String id);

    public List<SimpleModel> getList(SimpleModel Demoodel);

    public PageInfo<SimpleModel> getPage(int pageNum, int pageSize, SimpleModel model);

    public void updateById(SimpleModel model);

    public boolean delById(String id);

}