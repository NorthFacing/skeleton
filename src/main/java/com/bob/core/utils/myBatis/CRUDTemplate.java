package com.bob.core.utils.myBatis;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
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

    public String delete(Object obj) throws Exception {
        EntityUtil.perpareTableEntity(obj);
        String idname = EntityUtil.getPrimaryKey(obj);

        BEGIN();
        DELETE_FROM(EntityUtil.getTableName(obj));
        WHERE(idname + "=#{" + idname + "}");
        return SQL();
    }

    public String get(Object obj) throws Exception {
        EntityUtil.perpareTableEntity(obj);

        BEGIN();
        SELECT(EntityUtil.getSelectColumnStr(obj));
        FROM(EntityUtil.getTableName(obj));
        WHERE(EntityUtil.getWhereDefine(obj));
        return SQL();
    }

    public String findByEq(Object obj) throws Exception {
//        Map map = (Map) obj;
//        Object entity = map.get("0");
//        EntityUtil.caculatePropertyList(entity);
//        BEGIN(); // Clears ThreadLocal variable
//        SELECT(EntityUtil.returnSelectColumnsName(entity));
//        FROM(EntityUtil.getTableName(entity));
//        String paramString = EntityUtil.getWhereDefine(entity);
//        if (StringUtils.isNotEmpty(paramString)) {
//            WHERE(paramString);
//        }
//        Object orderby = map.get("1");
//        if (orderby != null) {
//            ORDER_BY((String) orderby);
//        }
//        return SQL();
        return null;
    }

    public String findBy(Object obj) throws Exception {
//        Map map = (Map) obj;
//        Object entity = map.get("0");
//        EntityUtil.caculatePropertyList(entity);
//        SelectBuilder.BEGIN(); // Clears ThreadLocal variable
//        SelectBuilder.SELECT(EntityUtil.returnSelectColumnsName(entity));
//        SelectBuilder.FROM(EntityUtil.getTableName(entity));
//
//        List<SearchFilter> searchFilterList = (List<SearchFilter>) map.get("1");
//        String paramString = EntityUtil.getWhereDefine(entity,
//                searchFilterList);
//        if (StringUtils.isNotEmpty(paramString)) {
//            SelectBuilder.WHERE(paramString);
//        }
//        String sql = SelectBuilder.SQL();
//        Object orderby = map.get("4");
//        if (StringUtils.isNotEmpty(orderby)) {
//            sql = sql + " order by " + orderby;
//        }
//
//        Object start = map.get("2");
//        Object pageSize = map.get("3");
//        if (start != null && pageSize != null && !start.equals(0)
//                && !pageSize.equals(0)) {
//            int startInt = (Integer) start;
//            int pageSizeInt = (Integer) pageSize;
//            sql = "select * from ( " + sql + " ) o limit "
//                    + (startInt - 1) + "," + pageSizeInt;
//        }
//        return sql;
        return null;
    }

    public String count(Object obj) throws Exception {
//        Map map = (Map) obj;
//        Object entity = map.get("0");
//        EntityUtil.caculatePropertyList(entity);
//        SelectBuilder.BEGIN(); // Clears ThreadLocal variable
//
//        String idname = EntityUtil.id(entity);
//
//        SelectBuilder.SELECT("count(" + idname + ")");
//        SelectBuilder.FROM(EntityUtil.getTableName(entity));
//
//        List<SearchFilter> searchFilterList = (List<SearchFilter>) map.get("1");
//        String paramString = EntityUtil.getWhereDefine(entity,
//                searchFilterList);
//        if (StringUtils.isNotEmpty(paramString)) {
//            SelectBuilder.WHERE(paramString);
//        }
//        return SelectBuilder.SQL();
        return null;
    }

}
