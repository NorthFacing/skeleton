package com.joyoung.core.base.service;

import com.joyoung.core.base.entity.BaseEntity;
import com.joyoung.core.utils.page.PageInfo;

import java.util.List;

/**
 * Created by Bob on 2015/12/30.
 */
public interface BaseService<T extends BaseEntity, V extends T, Q extends PageInfo> {

  String insert(T entity);

  Integer update(T entity);

  String save(T entity);

  Integer delete(T entity);

  Integer deleteById(String id);

  T select(T entity);

  T selectById(String id);

  V selectVo(T entity);

  V selectVoById(String id);

  List<T> selectList(T entity);

  List<V> selectVoList(T entity);

  Q pageData(Q query);

}
