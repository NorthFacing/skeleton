package com.bob.core.base.service.impl;

import com.bob.core.base.entity.BaseEntity;
import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.base.service.BaseService;
import com.bob.core.utils.page.BaseQuery;
import com.bob.core.utils.web.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bob on 2016/1/3.
 */
@Service("baseService")
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

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
  public abstract BaseMapper<T> getMapper();

  @Override
  public Result<String> insert(T entity) {
    getMapper().insert(entity);
    return Result.success(entity.getId());
  }

  @Override
  public Result<String> update(T entity) {
    getMapper().update(entity);
    return Result.success();
  }

  @Override
  public Result<String> save(T entity) {
    if (StringUtils.isEmpty(entity.getId())) {
      getMapper().insert(entity);
    } else {
      getMapper().update(entity);
    }
    return Result.success(entity.getId());
  }

  @Override
  public Result<String> delete(T entity) {
    getMapper().delete(entity);
    return Result.success();
  }

  @Override
  public Result<String> deleteById(String id) {
    T entity = getEntity();
    entity.setId(id);
    this.delete(entity);
    return Result.success();
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
  public <V extends T> V selectVo(T entity) {
    return getMapper().selectVo(entity);
  }

  @Override
  public <V extends T> V selectVoById(String id) {
    T entity = getEntity();
    entity.setId(id);
    return this.selectVo(entity);
  }

  @Override
  public List<T> selectList(T entity) {
    return getMapper().selectList(entity);
  }

  @Override
  public <V extends T> List<V> selectVoList(T entity) {
    return getMapper().selectVoList(entity);
  }

  @Override
  public <Q extends BaseQuery> Q pageData(Q query) {
    Long count = getMapper().count(query);
    query.setTotalCount(count);
    List list = getMapper().query(query);
    query.setResult(list);
    return query;
  }

}
