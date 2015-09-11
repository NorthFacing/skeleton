package com.bob.biz.demoKey.service.impl;

import org.springframework.stereotype.Service;

import com.bob.biz.demoKey.model.DemoKey;
import com.bob.biz.demoKey.model.DemoKeyVo;
import com.bob.biz.demoKey.service.DemoKeyService;
import com.bob.core.base.service.impl.BaseServiceImpl;

/**
 * DemoKeyServiceImpl
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-9-11 14:54:56
 */
@Service("demoKeyService")
public class DemoKeyServiceImpl extends BaseServiceImpl<DemoKey, DemoKeyVo> implements DemoKeyService {

}