package com.bob.core.base.dao;

import java.util.List;

import com.bob.core.base.entity.BaseEntity;

public interface BaseMapper<E extends BaseEntity, VO> {

    E getById(String id);

    VO getVoById(String id);

    List<E> getList(VO modelVo);

    List<VO> getVoList(VO modelVo);



}
