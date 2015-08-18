package com.bob.modules.user.service.impl;

import org.springframework.stereotype.Service;

import com.bob.biz.user.model.User;
import com.bob.biz.user.model.UserVo;
import com.bob.biz.user.service.UserService;
import com.bob.core.base.service.impl.BaseServiceImpl;

/**
 * userServiceImpl
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-25 13:22:46
 */
@Service("userService")
public  class UserServiceImpl extends BaseServiceImpl<User, UserVo> implements UserService {

}