package com.mall.core.interceptor;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;

import com.mall.core.base.model.BaseModel;

/**
 * 新增/修改 自动补充用户和时间字段
 * 
 * @since v0.0.1
 * @author Bob
 * @created 2015年7月4日 下午11:37:15
 */
public class OperationAspect {

    public void addOperation(JoinPoint jp) {
        Object obj = jp.getArgs()[0];
        if (obj instanceof BaseModel) {
            BaseModel model = (BaseModel) obj;
            model.setCreateTime(LocalDateTime.now());
            model.setCreateUser("TODO-ADD");
            model.setUpdateTime(LocalDateTime.now());
            model.setUpdateUser("TODO-ADD");
        }
    }

    public void updateOperation(JoinPoint jp) {
        Object obj = jp.getArgs()[0];
        if (obj instanceof BaseModel) {
            BaseModel model = (BaseModel) obj;
            model.setUpdateTime(LocalDateTime.now());
            model.setUpdateUser("TODO-UPDATE");
        }
    }
}
