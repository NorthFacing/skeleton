package com.joyoung.core.base.mapper;

import com.joyoung.core.base.entity.BaseEntity;
import com.joyoung.core.utils.page.PageInfo;

import java.util.List;

public interface BaseMapper<T extends BaseEntity, V extends T, Q extends PageInfo> {

  Integer insert(T entity);

  Integer update(T entity);

  Integer delete(T entity);

  T select(T entity);

  V selectVo(T entity);

  List<T> selectList(T entity);

  List<V> selectVoList(T entity);

  Long count(Q query);

  List query(Q query);

}
