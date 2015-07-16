package com.mall.biz.simple.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.biz.simple.mapper.SimpleMapper;
import com.mall.biz.simple.model.Simple;
import com.mall.biz.simple.service.SimpleService;

@Service
public class SimpleServiceImpl implements SimpleService {

    @Autowired
    private SimpleMapper demoMapper;

    @Override
    public Integer add(Simple model) {
        demoMapper.insert(model);
        return model.getId();
    }

    @Override
    public Simple getById(Integer id) {
        return demoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Simple> getList(Simple model) {
        return demoMapper.select(model);
    }

    @Override
    public PageInfo<Simple> getPage(int pageNum, int pageSize, Simple model) {
        PageHelper.startPage(pageNum, pageSize);
        List<Simple> list = demoMapper.select(model);
        return new PageInfo<Simple>(list);
    }

    @Override
    public void updateById(Simple model) {
        demoMapper.updateByPrimaryKey(model);
    }

    @Override
    public boolean delById(Integer id) {
        demoMapper.deleteByPrimaryKey(id);
        return true;
    }

}