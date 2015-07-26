package com.mall.biz.parameter.service.impl;

import org.springframework.stereotype.Service;

import com.mall.biz.parameter.model.Parameter;
import com.mall.biz.parameter.model.ParameterVo;
import com.mall.biz.parameter.service.ParameterService;
import com.mall.core.base.service.impl.BaseServiceImpl;

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