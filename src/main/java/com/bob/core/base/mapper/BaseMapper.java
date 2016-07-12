package com.bob.core.base.mapper;

import com.bob.core.base.entity.BaseEntity;
import com.bob.core.utils.myBatis.CRUDTemplate;
import com.bob.core.utils.page.BaseQuery;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface BaseMapper<T extends BaseEntity> {

  @InsertProvider(type = CRUDTemplate.class, method = "insert")
  Integer insert(T entity);

  @UpdateProvider(type = CRUDTemplate.class, method = "update")
  Integer update(T entity);

  @DeleteProvider(type = CRUDTemplate.class, method = "delete")
  Integer delete(T entity);

  // 以下方法需要在各自的Mapper中覆写实现才可以使用，因为在BaseMapper中类型转换异常
  T select(T entity);

  <V extends T> V selectVo(T entity);

  List<T> selectList(T entity);

  <V extends T> List<V> selectVoList(T entity);

  // 分页查询没有使用Java模板，需要在XML中进行配置，xml中配置不会有类型转换异常的问题，不需要覆写
  <Q extends BaseQuery> Long count(Q query);

  <Q extends BaseQuery> List query(Q query);

}
