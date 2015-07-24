package com.mall.biz.parameter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.biz.parameter.mapper.ParameterMapper;
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

    @Autowired
    private ParameterMapper parameterMapper;

    @Override
    public PageInfo<Parameter> getPage(int pageNum, int pageSize, ParameterVo parameterVo) {
        PageHelper.startPage(pageNum, pageSize);
        List<Parameter> list = parameterMapper.select(parameterVo);
        return new PageInfo<Parameter>(list);
    }

}