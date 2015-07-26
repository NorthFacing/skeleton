package com.bob.biz.demo.service.impl;

import org.springframework.stereotype.Service;

import com.bob.biz.demo.model.Demo;
import com.bob.biz.demo.model.DemoVo;
import com.bob.biz.demo.service.DemoService;
import com.bob.core.base.service.impl.BaseServiceImpl;

@Service("demoService")
public class DemoServiceImpl extends BaseServiceImpl<Demo, DemoVo> implements DemoService {

}
