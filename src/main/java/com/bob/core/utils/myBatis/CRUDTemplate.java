package com.bob.core.utils.myBatis;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SelectBuilder;

import java.util.Map;

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
        BEGIN();

        INSERT_INTO(EntityUtil.tablename(obj));

        EntityUtil.caculationColumnList(obj);
        VALUES(EntityUtil.returnInsertColumnsName(obj),
                EntityUtil.returnInsertColumnsDefine(obj));
        return SQL();
    }

    public String update(Object obj) throws Exception {
        String idname = EntityUtil.id(obj);

        BEGIN();

        UPDATE(EntityUtil.tablename(obj));
        EntityUtil.caculationColumnList(obj);
        SET(EntityUtil.returnSetDefine(obj));
        WHERE(idname + "=#{" + idname + "}");

        return SQL();
    }

     public String delete(Object obj) throws Exception {
     String idname = EntityUtil.id(obj);

     BEGIN();

     DELETE_FROM(EntityUtil.tablename(obj));
     WHERE(idname + "=#{" + idname + "}");

     return SQL();
     }

    public String findByEq(Object obj) throws Exception {
        Map map = (Map) obj;
        Object entity = map.get("0");
        EntityUtil.caculationColumnList(entity);
        BEGIN(); // Clears ThreadLocal variable
        SELECT(EntityUtil.returnSelectColumnsName(entity));
        FROM(EntityUtil.tablename(entity));
        String paramString = EntityUtil.returnWhereDefine(entity);
        if (StringUtils.isNotEmpty(paramString)) {
            WHERE(paramString);
        }
        Object orderby = map.get("1");
        if (orderby != null) {
            ORDER_BY((String) orderby);
        }
        return SQL();
    }

    public String findBy(Object obj) throws Exception {
//        Map map = (Map) obj;
//        Object entity = map.get("0");
//        EntityUtil.caculationColumnList(entity);
//        SelectBuilder.BEGIN(); // Clears ThreadLocal variable
//        SelectBuilder.SELECT(EntityUtil.returnSelectColumnsName(entity));
//        SelectBuilder.FROM(EntityUtil.tablename(entity));
//
//        List<SearchFilter> searchFilterList = (List<SearchFilter>) map.get("1");
//        String paramString = EntityUtil.returnWhereDefine(entity,
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
//        EntityUtil.caculationColumnList(entity);
//        SelectBuilder.BEGIN(); // Clears ThreadLocal variable
//
//        String idname = EntityUtil.id(entity);
//
//        SelectBuilder.SELECT("count(" + idname + ")");
//        SelectBuilder.FROM(EntityUtil.tablename(entity));
//
//        List<SearchFilter> searchFilterList = (List<SearchFilter>) map.get("1");
//        String paramString = EntityUtil.returnWhereDefine(entity,
//                searchFilterList);
//        if (StringUtils.isNotEmpty(paramString)) {
//            SelectBuilder.WHERE(paramString);
//        }
//        return SelectBuilder.SQL();
        return null;
    }

    public String get(Object obj) throws Exception {
        EntityUtil.caculationColumnList(obj);
        String idname = EntityUtil.id(obj);

        BEGIN();
        SELECT(EntityUtil.returnSelectColumnsName(obj));

        FROM(EntityUtil.tablename(obj));
        WHERE(idname + "=#{" + idname + "}");
        return SelectBuilder.SQL();
    }

}
