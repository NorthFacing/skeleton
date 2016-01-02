package com.bob.core.base.service.impl;

import com.bob.core.base.entity.BaseEntity;
import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.base.service.BaseService;
import com.bob.core.utils.page.BaseQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bob on 2016/1/3.
 */
@Service("baseService")
public class BaseServiceImpl<T extends BaseEntity, V extends T> implements BaseService<T, V> {

    @Autowired
    private BaseMapper baseMapper;

    @Override
    public void insert(BaseEntity entity) {
        baseMapper.insert(entity);
    }

    @Override
    public void update(BaseEntity entity) {
        baseMapper.update(entity);
    }

    @Override
    public void delete(BaseEntity entity) {
        baseMapper.delete(entity);
    }

    @Override
    public T select(BaseEntity entity) {
        return (T) baseMapper.select(entity);
    }

    @Override
    public V selectVo(BaseEntity entity) {
        return (V) baseMapper.selectVo(entity);
    }

    @Override
    public List<T> selectList(BaseEntity entity) {
        return baseMapper.selectList(entity);
    }

    @Override
    public List<V> selectVoList(BaseEntity entity) {
        return baseMapper.selectVoList(entity);
    }

    @Override
    public Long count(BaseQuery query) {
        return baseMapper.count(query);
    }

    @Override
    public List query(BaseQuery query) {
        return baseMapper.query(query);
    }

}
