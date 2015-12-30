package com.bob.core.base.mapper;

import java.util.List;

import com.bob.core.base.entity.BaseEntity;
import com.bob.core.utils.page.BaseQuery;

public interface BaseMapper<E extends BaseEntity, VO> {

    // 模板
    void insert(E entity);

    // 模板
    void update(E entity);

    // 模板
    E getById(String id);

    VO getVoById(String id);

    // 模板
    List<E> getList(Object obj);

    List<VO> getVoList(Object obj);

    // 模板
    List<E> getPage(BaseQuery query);

    List<VO> getVoPage(BaseQuery query);

}
