package com.bob.core.base.service;

import com.bob.core.base.entity.BaseEntity;
import com.bob.core.utils.page.BaseQuery;

import java.util.List;

/**
 * Created by Bob on 2015/12/30.
 */
public interface BaseService<T extends BaseEntity, V extends T, Q extends BaseQuery> {

    void insert(BaseEntity entity);

    void update(BaseEntity entity);

    void delete(BaseEntity obj);

    T select(BaseEntity entity);

    V selectVo(BaseEntity entity);

    List<T> selectList(BaseEntity entity);

    List<V> selectVoList(BaseEntity entity);

    Long count(Q query);

    Q query(Q query);

}
