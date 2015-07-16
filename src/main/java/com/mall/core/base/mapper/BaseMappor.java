package com.mall.core.base.mapper;

import com.mall.core.base.model.BaseModel;

import tk.mybatis.mapper.common.BaseMapper;

public interface BaseMappor<M extends BaseModel, VO> extends BaseMapper<M> {

}
