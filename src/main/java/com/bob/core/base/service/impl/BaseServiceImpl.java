package com.bob.core.base.service.impl;

import com.bob.core.base.entity.BaseEntity;
import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.base.service.BaseService;
import com.bob.core.utils.page.BaseQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bob on 2016/1/3.
 */
@Service("baseService")
public abstract class BaseServiceImpl<T extends BaseEntity, V extends T, Q extends BaseQuery>
    implements BaseService<T, V, Q> {

  /**
   * 获取实际操作的对象方法，方便操作
   *
   * @return entity
   */
  public abstract T getEntity();

  /**
   * 获取当前操作的mapper的实例对象，方便通用方法中调用，而不会出现类型转换异常的问题。
   *
   * @return mapper
   */
  public abstract BaseMapper<T, V, Q> getMapper();

  @Override
  public String insert(T entity) {
    getMapper().insert(entity);
    return entity.getId();
  }

  @Override
  public Integer update(T entity) {
    return getMapper().update(entity);
  }

  @Override
  public String save(T entity) {
    if (StringUtils.isEmpty(entity.getId())) {
      getMapper().insert(entity);
    } else {
      getMapper().update(entity);
    }
    return entity.getId();
  }

  @Override
  public Integer delete(T entity) {
    return getMapper().delete(entity);
  }

  @Override
  public Integer deleteById(String id) {
    T entity = getEntity();
    entity.setId(id);
    return this.delete(entity);
  }

  @Override
  public T select(T entity) {
    return getMapper().select(entity);
  }

  @Override
  public T selectById(String id) {
    T entity = getEntity();
    entity.setId(id);
    return this.select(entity);
  }

  @Override
  public V selectVo(T entity) {
    return getMapper().selectVo(entity);
  }

  @Override
  public V selectVoById(String id) {
    T entity = getEntity();
    entity.setId(id);
    return this.selectVo(entity);
  }

  @Override
  public List<T> selectList(T entity) {
    return getMapper().selectList(entity);
  }

  @Override
  public List<V> selectVoList(T entity) {
    return getMapper().selectVoList(entity);
  }

  @Override
  public Q pageData(Q query) {
    Long count = getMapper().count(query);
    query.setTotal(count);
    List list = getMapper().query(query);
    query.setResult(list);
    return query;
  }

}
