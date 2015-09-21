package com.bob.core.base.mapper;

import java.util.List;

import com.bob.core.base.model.BaseModel;

import tk.mybatis.mapper.common.Mapper;

public interface BaseMapper<M extends BaseModel, VO> extends Mapper<M> {

    public VO getVoById(String id);

    public List<M> getList(VO modelVo);

    public List<VO> getVoList(VO modelVo);

}
