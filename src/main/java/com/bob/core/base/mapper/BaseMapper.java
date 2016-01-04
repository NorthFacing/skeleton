package com.bob.core.base.mapper;

import com.bob.core.base.entity.BaseEntity;
import com.bob.core.utils.myBatis.CRUDTemplate;
import com.bob.core.utils.page.BaseQuery;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface BaseMapper<T extends BaseEntity, V extends T, Q extends BaseQuery> {


    @InsertProvider(type = CRUDTemplate.class, method = "insert")
    void insert(T entity);

    @UpdateProvider(type = CRUDTemplate.class, method = "update")
    void update(T entity);

    @DeleteProvider(type = CRUDTemplate.class, method = "delete")
    void delete(T entity);

    // 以下方法需要在各自的Mapper中覆写实现才可以使用，因为在BaseMapper中类型转换异常
    T select(T entity);

    V selectVo(T entity);

    List<T> selectList(T entity);

    List<V> selectVoList(T entity);

    // 分页查询没有使用Java模板，需要在XML中进行配置，xml中配置不会有类型转换异常的问题，不需要覆写
    Long count(Q query);

    List query(Q query);

}
