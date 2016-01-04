package com.bob.core.base.service.impl;

import com.bob.core.base.entity.BaseEntity;
import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.base.service.BaseService;
import com.bob.core.utils.page.BaseQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bob on 2016/1/3.
 */
@Service("baseService")
public abstract class BaseServiceImpl<T extends BaseEntity, V extends T, Q extends BaseQuery>
        implements BaseService<T, V, Q> {

    public abstract T getEntity();

    public abstract BaseMapper<T, V, Q> getBaseMapper();

    @Override
    public String insert(T entity) {
        getBaseMapper().insert(entity);
        return entity.getId();
    }

    @Override
    public void update(T entity) {
        getBaseMapper().update(entity);
    }

    @Override
    public String save(T entity) {
        if (null == entity.getId()) {
            getBaseMapper().insert(entity);
        } else {
            getBaseMapper().update(entity);
        }
        return entity.getId();
    }

    @Override
    public void delete(T entity) {
        getBaseMapper().delete(entity);
    }

    @Override
    public T select(T entity) {
        return (T) getBaseMapper().select(entity);
    }

    @Override
    public V selectVo(T entity) {
        return (V) getBaseMapper().selectVo(entity);
    }

    @Override
    public List<T> selectList(T entity) {
        return getBaseMapper().selectList(entity);
    }

    @Override
    public List<V> selectVoList(T entity) {
        return getBaseMapper().selectVoList(entity);
    }

    @Override
    public Q pageData(Q query) {
        Long count = getBaseMapper().count(query);
        query.setTotal(count);
        List list = getBaseMapper().query(query);
        query.setResult(list);
        return query;
    }

}
