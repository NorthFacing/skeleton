package com.bob.core.utils.myBatis;

import org.apache.commons.lang.StringUtils;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

/**
 *
 */
public class CRUDTemplate {

    public String insert(Object obj) throws Exception {
        EntityUtil.perpareTableEntity(obj);

        BEGIN();
        INSERT_INTO(EntityUtil.getTableName(obj));
        VALUES(EntityUtil.getInsertColumnsName(obj), EntityUtil.getInsertColumnsDefine(obj));
        return SQL();
    }

    public String update(Object obj) throws Exception {
        EntityUtil.perpareTableEntity(obj);
        String idName = EntityUtil.getPrimaryKey(obj);

        BEGIN();
        UPDATE(EntityUtil.getTableName(obj));
        SET(EntityUtil.getSetDefine(obj));
        WHERE(idName + "=#{" + idName + "}");
        return SQL();
    }

    public String select(Object obj) throws Exception {
        EntityUtil.perpareTableEntity(obj);

        BEGIN();
        SELECT(EntityUtil.getSelectColumnStr(obj));
        FROM(EntityUtil.getTableName(obj));
        String whereDefine = EntityUtil.getWhereDefine(obj);
        if (StringUtils.isNotEmpty(whereDefine)) {
            WHERE(whereDefine);
        }
        String orderBy = EntityUtil.getOrderBy(obj);
        if (StringUtils.isNotEmpty(orderBy)) {
            ORDER_BY(orderBy);
        }
        return SQL();
    }

//分页相关的查询一般情况下查询条件都不是相等处理，故暂时不做封装
//    public String count(Object obj) throws Exception {
//        EntityUtil.perpareTableEntity(obj);
//
//        BEGIN();
//        SELECT("count(*)");
//        FROM(EntityUtil.getTableName(obj));
//        String whereDefine = EntityUtil.getWhereDefine(obj);
//        if (StringUtils.isNotEmpty(whereDefine)) {
//            WHERE(whereDefine);
//        }
//        return SQL();
//    }
//
//    public String query(Object obj){
//        return null;
//    }

    public String delete(Object obj) throws Exception {
        EntityUtil.perpareTableEntity(obj);
        String idname = EntityUtil.getPrimaryKey(obj);

        BEGIN();
        DELETE_FROM(EntityUtil.getTableName(obj));
        WHERE(idname + "=#{" + idname + "}");
        return SQL();
    }

}
