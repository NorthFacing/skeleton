package com.bob.core.base.mapper;

import java.util.List;

import com.bob.core.base.entity.BaseEntity;
import com.bob.core.utils.myBatis.CRUDTemplate;
import com.bob.core.utils.page.BaseQuery;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface BaseMapper<E extends BaseEntity, VO> {

    @InsertProvider(type = CRUDTemplate.class, method = "insert")
    void insert(E entity);

    @UpdateProvider(type = CRUDTemplate.class, method = "update")
    void update(E entity);

    Integer count();

    // 查询出来的数据，进行封装的时候，不能绑定到具体的实体类，
    // 只能绑定到BaseEntity，导致数据类型转换错误
    // 所以，目前只能在各个模块的Mapper中进行复写
    @SelectProvider(type = CRUDTemplate.class, method = "select")
    E select(E entity);

    List<E> getList(Object obj);

    List<E> getPage(BaseQuery query);


    // 以下是需要在xml中自己实现的方法

    VO getVoById(String id);

    List<VO> getVoList(Object obj);

    List<VO> getVoPage(BaseQuery query);

}
