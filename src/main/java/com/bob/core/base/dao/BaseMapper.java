package com.bob.core.base.dao;

import java.util.List;

import com.bob.core.base.entity.BaseEntity;

import tk.mybatis.mapper.common.Mapper;

public interface BaseMapper<E extends BaseEntity, VO> extends Mapper<E> {

    VO getVoById(String id);

    List<E> getList(VO modelVo);

    List<VO> getVoList(VO modelVo);

}
