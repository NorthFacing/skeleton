package com.mall.biz.user.service.impl;

import org.springframework.stereotype.Service;

import com.mall.biz.user.model.User;
import com.mall.biz.user.model.UserVo;
import com.mall.biz.user.service.UserService;
import com.mall.core.base.service.impl.BaseServiceImpl;

/**
 * userServiceImpl
 * @since v0.0.1
 * @author Bob
 * @Date 2015-7-25 13:22:46
 */
@Service("userService")
public  class UserServiceImpl extends BaseServiceImpl<User, UserVo> implements UserService {

}