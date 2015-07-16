package com.mall.core.base.service.impl;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.core.base.mappers.BaseMapper;
import com.mall.core.base.model.BaseModel;
import com.mall.core.base.service.BaseService;

public class BaseServiceImpl<M extends BaseModel, VO> implements BaseService<M, VO> {

    @Autowired
    private BaseMapper<M, VO> baseMapper;

    @Override
    public String add(M model) {
        Validate.notNull(model);
        baseMapper.add(model);
        return model.getId();
    }

    @Override
    public <E> E getById(String id) {
        Validate.notNull(id);
        return baseMapper.getById(id);
    }

    @Override
    public <E> List<E> getList(VO vo) {
        return baseMapper.getList(vo);
    }

    @Override
    public <E> PageInfo<E> getPage(int pageNum, int pageSize, VO vo) {
        Validate.notNull(pageNum);
        Validate.notNull(pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<E> list = baseMapper.getList(vo);
        return new PageInfo<E>(list);
    }

    @Override
    public void updateById(M model) {
        Validate.notNull(model);
        Validate.notNull(model.getId());
        baseMapper.updateById(model);
    }

    @Override
    public boolean delById(String id) {
        Validate.notNull(id);
        baseMapper.delById(id);
        return true;
    }

}
