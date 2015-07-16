package com.mall.biz.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.biz.demo.mappers.DemoMapper;
import com.mall.biz.demo.model.DemoModel;
import com.mall.biz.demo.model.DemoModelVo;
import com.mall.biz.demo.service.DemoService;
import com.mall.core.base.service.impl.BaseServiceImpl;

@Service("demoService")
public class DemoServiceImpl extends BaseServiceImpl<DemoModel, DemoModelVo> implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

}
