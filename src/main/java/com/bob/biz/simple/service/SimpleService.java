package com.bob.biz.simple.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.bob.biz.simple.model.Simple;
import com.bob.biz.simple.model.SimpleVo;

/**
 * demo
 */
public interface SimpleService {

    public String add(Simple model);

    public Simple getById(String id);

    public List<Simple> getList(Simple Demoodel);

    public PageInfo<Simple> getPage(SimpleVo modelVo);

    public void updateById(Simple model);

    public boolean delById(String id);

}