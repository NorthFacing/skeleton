package com.bob.biz.simple.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.bob.biz.simple.mapper.SimpleMapper;
import com.bob.biz.simple.model.Simple;
import com.bob.biz.simple.model.SimpleVo;
import com.bob.biz.simple.service.SimpleService;

@Service
public class SimpleServiceImpl implements SimpleService {

    @Autowired
    private SimpleMapper demoMapper;

    @Override
    public String add(Simple model) {
        demoMapper.insert(model);
        return model.getId();
    }

    @Override
    public Simple getById(String id) {
        return demoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Simple> getList(Simple model) {
        return demoMapper.select(model);
    }

    @Override
    public PageInfo<Simple> getPage(SimpleVo modelVo) {
        List<Simple> list = demoMapper.select(modelVo);
        return new PageInfo<Simple>(list);
    }

    @Override
    public void updateById(Simple model) {
        demoMapper.updateByPrimaryKey(model);
    }

    @Override
    public boolean delById(String id) {
        demoMapper.deleteByPrimaryKey(id);
        return true;
    }

}