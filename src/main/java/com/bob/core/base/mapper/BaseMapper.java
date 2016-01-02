package com.bob.core.base.mapper;

import com.bob.core.utils.myBatis.CRUDTemplate;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface BaseMapper {

    @InsertProvider(type = CRUDTemplate.class, method = "insert")
    void insert(Object entity);

    @UpdateProvider(type = CRUDTemplate.class, method = "update")
    void update(Object entity);

    @SelectProvider(type= CRUDTemplate.class, method= "count")
    Integer count(Object obj);

}
