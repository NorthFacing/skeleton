package com.mall.biz.parameter.mapper;

import java.util.List;

import com.mall.biz.parameter.model.Parameter;
import com.mall.biz.parameter.model.ParameterVo;
import com.mall.core.base.mapper.BaseMapper;

/**
 * parameterMapper
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-24 15:19:21
 */
public interface ParameterMapper extends BaseMapper<Parameter, ParameterVo> {

    List<Parameter> select(Parameter Parameter);

}