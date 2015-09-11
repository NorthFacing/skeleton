package com.bob.biz.demoLock.service.impl;

import org.springframework.stereotype.Service;

import com.bob.biz.demoLock.model.DemoLock;
import com.bob.biz.demoLock.model.DemoLockVo;
import com.bob.biz.demoLock.service.DemoLockService;
import com.bob.core.base.service.impl.BaseServiceImpl;

/**
 * DemoLockServiceImpl
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-9-11 14:54:56
 */
@Service("demoLockService")
public class DemoLockServiceImpl extends BaseServiceImpl<DemoLock, DemoLockVo> implements DemoLockService {

}