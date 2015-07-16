package com.mall.biz.simple.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.biz.simple.mapper.SimpleMapper;
import com.mall.biz.simple.model.SimpleModel;
import com.mall.biz.simple.service.SimpleService;

@Service
public class SimpleServiceImpl implements SimpleService {

    @Autowired
    private SimpleMapper demoMapper;
    
    @Override
    public String add(SimpleModel model) {
        demoMapper.insert(model);
        return model.getId();
    }

    @Override
    public SimpleModel getById(String id) {
        return demoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SimpleModel> getList(SimpleModel model) {
        return demoMapper.select(model);
    }

    @Override
    public PageInfo<SimpleModel> getPage(int pageNum, int pageSize, SimpleModel model) {
        PageHelper.startPage(pageNum, pageSize);
        List<SimpleModel> list = demoMapper.select(model);
        return new PageInfo<SimpleModel>(list);
    }

    @Override
    public void updateById(SimpleModel model) {
        demoMapper.updateByPrimaryKey(model);
    }

    @Override
    public boolean delById(String id) {
        demoMapper.deleteByPrimaryKey(id);
        return true;
    }

}