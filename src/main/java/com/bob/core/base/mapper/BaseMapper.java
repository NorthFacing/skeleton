package com.bob.core.base.mapper;

import java.util.List;

import com.bob.core.base.model.BaseModel;

import tk.mybatis.mapper.common.Mapper;

public interface BaseMapper<M extends BaseModel, VO> extends Mapper<M> {

    List<M> getList(VO modelVo);
    
}
