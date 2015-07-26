package com.bob.biz.parameter.service.impl;

import org.springframework.stereotype.Service;

import com.bob.biz.parameter.model.Parameter;
import com.bob.biz.parameter.model.ParameterVo;
import com.bob.biz.parameter.service.ParameterService;
import com.bob.core.base.service.impl.BaseServiceImpl;

/**
 * parameterServiceImpl
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-24 15:19:21
 */
@Service("parameterService")
public class ParameterServiceImpl extends BaseServiceImpl<Parameter, ParameterVo> implements ParameterService {

}