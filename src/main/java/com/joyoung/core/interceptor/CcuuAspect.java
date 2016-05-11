package com.joyoung.core.interceptor;

import com.joyoung.core.base.entity.BaseEntity;
import com.joyoung.core.utils.javaUtil.UuidUtil;
import org.aspectj.lang.JoinPoint;

import java.util.Date;
import java.util.List;

/**
 * 新增/修改 自动补充用户和时间字段
 *
 * @author Bob
 * @created 2015年7月4日 下午11:37:15
 * @since v0.1
 */
public class CcuuAspect {

    public void insert(JoinPoint jp) {
        Object obj = jp.getArgs()[0];
        if (obj instanceof BaseEntity) {
            iccuu(obj);
        }
    }

    @SuppressWarnings("unchecked")
    public void insertBatch(JoinPoint jp) {
        Object obj = jp.getArgs()[0];
        if (obj instanceof List) {
            for (Object model : (List<BaseEntity>) obj) {
                iccuu(model);
            }
        }
    }

    private void iccuu(Object obj) {
        Date date = new Date();
        BaseEntity entity = (BaseEntity) obj;
        entity.setId(UuidUtil.getId());
        entity.setCreateTime(date);
        entity.setCreateUser("admin");
        entity.setUpdateTime(date);
        entity.setUpdateUser("admin");
    }

    public void update(JoinPoint jp) {
        Object obj = jp.getArgs()[0];
        if (obj instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) obj;
            entity.setUpdateTime(new Date());
            entity.setUpdateUser("admin");
        }
    }

}
