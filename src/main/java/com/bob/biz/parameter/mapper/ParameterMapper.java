package com.bob.biz.parameter.mapper;

import java.util.List;

import com.bob.biz.parameter.model.Parameter;
import com.bob.biz.parameter.model.ParameterVo;
import com.bob.core.base.mapper.BaseMapper;

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