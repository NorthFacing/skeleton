package com.bob.core.interceptor;

import com.bob.core.base.entity.BaseEntity;
import com.bob.core.utils.UuidUtil;

import org.aspectj.lang.JoinPoint;

import java.time.LocalDateTime;

/**
 * 新增/修改 自动补充用户和时间字段
 * 
 * @since v0.0.1
 * @author Bob
 * @created 2015年7月4日 下午11:37:15
 */
public class CcuuAspect {

    public void insert(JoinPoint jp) {
        Object obj = jp.getArgs()[0];
        if (obj instanceof BaseEntity) {
            BaseEntity model = (BaseEntity) obj;
            model.setId(UuidUtil.getId());
            model.setCreateTime(LocalDateTime.now());
            model.setCreateUser("TODO-ADD");
            model.setUpdateTime(LocalDateTime.now());
            model.setUpdateUser("TODO-ADD");
        }
    }

    public void update(JoinPoint jp) {
        Object obj = jp.getArgs()[0];
        if (obj instanceof BaseEntity) {
            BaseEntity model = (BaseEntity) obj;
            model.setUpdateTime(LocalDateTime.now());
            model.setUpdateUser("TODO-UPDATE");
        }
    }
}
