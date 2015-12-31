package com.bob.core.base.mapper;

import java.util.List;

import com.bob.core.base.entity.BaseEntity;
import com.bob.core.utils.myBatis.CRUDTemplate;
import com.bob.core.utils.page.BaseQuery;
import org.apache.ibatis.annotations.InsertProvider;

public interface BaseMapper<E extends BaseEntity, VO> {

    @InsertProvider(type = CRUDTemplate.class, method = "insert")
    void insert(E entity);

//    @InsertProvider(type = CRUDTemplate.class, method = "update")
    void update(E entity);

    E getById(String id);

    List<E> getList(Object obj);

    List<E> getPage(BaseQuery query);


    // 以下是需要在xml中自己实现的方法

    VO getVoById(String id);

    List<VO> getVoList(Object obj);

    List<VO> getVoPage(BaseQuery query);

}
