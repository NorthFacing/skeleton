package com.joyoung.core.base.service.impl;

import com.joyoung.core.base.entity.BaseEntity;
import com.joyoung.core.base.mapper.BaseMapper;
import com.joyoung.core.base.service.BaseService;
import com.joyoung.core.utils.page.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("baseService")
public abstract class BaseServiceImpl<T extends BaseEntity, V extends T, Q extends PageInfo>
    implements BaseService<T, V, Q> {

  public abstract T getEntity();

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
    query.setTotalCount(count);
    List list = getMapper().query(query);
    query.setResult(list);
    return query;
  }

}
