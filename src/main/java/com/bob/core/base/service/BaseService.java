package com.bob.core.base.service;

import com.bob.core.base.entity.BaseEntity;
import com.bob.core.utils.page.BaseQuery;
import com.bob.core.utils.web.Result;

import java.util.List;

/**
 * Created by Bob on 2015/12/30.
 */
public interface BaseService<T extends BaseEntity> {

  Result<String> insert(T entity);

  Result<String> update(T entity);

  Result<String> save(T entity);

  Result<String> delete(T entity);

  Result<String> deleteById(String id);

  T select(T entity);

  T selectById(String id);

  <V extends T> V selectVo(T entity);

  <V extends T> V selectVoById(String id);

  List<T> selectList(T entity);

  <V extends T> List<V> selectVoList(T entity);

  <Q extends BaseQuery> Q pageData(Q query);

}
