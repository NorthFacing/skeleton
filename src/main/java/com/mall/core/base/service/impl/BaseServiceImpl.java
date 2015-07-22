package com.mall.core.base.service.impl;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.core.base.mapper.BaseMapper;
import com.mall.core.base.model.BaseModel;
import com.mall.core.base.service.BaseService;

public class BaseServiceImpl<M extends BaseModel, VO> implements BaseService<M, VO> {

    @Autowired
    private BaseMapper<M, VO> baseMapper;

    @Override
    public String edit(M model) {
        Validate.notNull(model);
        if (model.getId() != null) {
            baseMapper.updateByPrimaryKey(model);
        } else {
            baseMapper.insert(model);
        }
        return model.getId();
    }

    @Override
    public String add(M model) {
        Validate.notNull(model);
        baseMapper.insert(model);
        return model.getId();
    }

    @Override
    public M getById(String id) {
        Validate.notNull(id);
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<M> getList(M model) {
        return baseMapper.select(model);
    }

    @Override
    public PageInfo<M> getPage(int pageNum, int pageSize, M model) {
        Validate.notNull(pageNum);
        Validate.notNull(pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<M> list = baseMapper.select(model);
        return new PageInfo<M>(list);
    }

    @Override
    public void updateById(M model) {
        Validate.notNull(model);
        Validate.notNull(model.getId());
        baseMapper.updateByPrimaryKey(model);
    }

    @Override
    public boolean delById(String id) {
        Validate.notNull(id);
        baseMapper.deleteByPrimaryKey(id);
        return true;
    }

}
