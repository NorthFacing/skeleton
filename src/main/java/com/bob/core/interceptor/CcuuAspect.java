package com.bob.core.interceptor;

import com.bob.core.base.entity.BaseEntity;
import com.bob.core.utils.UuidUtil;
import org.aspectj.lang.JoinPoint;

import java.time.LocalDateTime;
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
        BaseEntity entity = (BaseEntity) obj;
        entity.setId(UuidUtil.getId());
        entity.setCreateTime(LocalDateTime.now());
        entity.setCreateUser("admin");
        entity.setUpdateTime(LocalDateTime.now());
        entity.setUpdateUser("admin");
    }

    public void update(JoinPoint jp) {
        Object obj = jp.getArgs()[0];
        if (obj instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) obj;
            entity.setUpdateTime(LocalDateTime.now());
            entity.setUpdateUser("admin");
        }
    }

}
