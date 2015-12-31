package com.bob.core.utils.myBatis;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bob.core.base.entity.BaseEntity;

/**
 * 解析POJO的类
 *
 * @author henliqi
 */
public class EntityUtil {

    public static final Logger logger = LoggerFactory.getLogger(EntityUtil.class);

    // 用于存放POJO的列信息
    private static Map<Class, List<PropertyDescriptor>> propertyListMap = new HashMap<>();
    private static Map<Class, PropertyDescriptor> primaryKeyMap = new HashMap<>();

    /**
     * @param obj POJO实例
     * @Descreption 获取POJO对应的数据库表名
     * 1. 如果 POJO中定义了@Table(name)，获取定义的名称
     * 2. 如果没有指定，则默认按照驼峰类名转换 为 下划线表名
     * @Author Bob
     * @Date 2015-12-31 12:57:00
     */
    public static String tablename(Object obj) throws Exception {
        if (null == obj) {
            throw new Exception("To get tableName, POJO instance can not be null ! ");
        }
        String tablename = null;
        Class<?> clazz = obj.getClass();
        Table table = clazz.getAnnotation(Table.class);
        if (table != null) {
            tablename = table.name();
        } else {
            String pojoName = clazz.getSimpleName();
            tablename = camelCase2UnderScore(pojoName);
        }
        logger.info(tablename);
        return tablename;
    }

    /**
     * @param obj POJO实例
     * @Descreption 获取实例中所有属性字段
     * @Author Bob
     * @Date 2015-12-31 14:26:00
     */
    public static void caculatePropertyList(Object obj) {
        if (propertyListMap.containsKey(obj.getClass())) {
            return;
        }
        BeanInfo intro = null;
        try {
            intro = Introspector.getBeanInfo(obj.getClass());
        } catch (IntrospectionException e) {
            logger.error(e.getMessage(), e);
        }

        PropertyDescriptor[] propertyDescriptors = intro.getPropertyDescriptors();
        List<PropertyDescriptor> columnList = new ArrayList<>(propertyDescriptors.length);
        for (PropertyDescriptor p : propertyDescriptors) {
            // 如果是Class 属性，则舍弃
            Class<?> clazz = p.getPropertyType();
            if (clazz.isInstance(Class.class)) {
                continue;
            }
            // 如果使用@Transient注解，则舍弃该字段
            Method method = p.getReadMethod();
            if (method.isAnnotationPresent(Transient.class)) {
                continue;
            }


            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation anno : annotations) {
                System.out.println(anno);
            }

            // 增加字段
            columnList.add(p);

        }
        propertyListMap.put(obj.getClass(), columnList);
    }

    /**
     * @param p POJO实例属性
     * @Descreption 获得列Column的数字库列名
     * @Author Bob
     * @Date 2015-12-31 14:28:00
     */
    public static String getPropertyColumnName(PropertyDescriptor p) {
        String columnName = null;
        Method method = p.getReadMethod();
        Annotation columnAnnotation = method.getAnnotation(Column.class);
        // 如果有指定的columnName，按照指定的字段拼接；否认，按照属性名称驼峰转下划线
        if (null != columnAnnotation) {
            Column column = (Column) columnAnnotation;
            String name = column.name();
            if (StringUtils.isNotBlank(name)) {
                columnName = name;
            }
        } else {
            columnName = camelCase2UnderScore(p.getName());
        }
        logger.info(columnName);
        return columnName;
    }

    /**
     * 获取用于WHERE的 有值字段表
     *
     * @return
     */
    public static List<WhereColumn> returnWhereColumnsName(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        List<WhereColumn> columnList = new ArrayList<>(fields.length);
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class) && !isNull(obj, field)) {
                columnList.add(new WhereColumn(field.getName(), field.getGenericType().equals(String.class)));
            }
        }
        return columnList;
    }

    /**
     * Where条件信息
     *
     * @author henliqi
     */
    public static class WhereColumn {
        public String name;
        public boolean isString;

        public WhereColumn(String name, boolean isString) {
            this.name = name;
            this.isString = isString;
        }
    }

    /**
     * 用于获取Select的字段映射
     *
     * @param obj
     * @return
     */
    public static String returnSelectColumnsName(Object obj) {
        StringBuilder sb = new StringBuilder();

        List<PropertyDescriptor> propertyDescriptorList = propertyListMap.get(obj.getClass());

        for (int i = 0; i < propertyDescriptorList.size(); i++) {
            PropertyDescriptor p = propertyDescriptorList.get(i);
            sb.append(getPropertyColumnName(p));
            sb.append(" as ");
            sb.append(p.getName());
            if (i != propertyDescriptorList.size() - 1) {
                sb.append(" , ");
            }
        }
        return sb.toString();
    }

    /**
     * @param obj POJO实例
     * @Descreption 用于获取Insert的字段累加
     * @Author Bob
     * @Date 2015-12-31 14:28:00
     */
    public static String returnInsertColumnsName(Object obj) throws Exception {
        StringBuilder sb = new StringBuilder();

        List<PropertyDescriptor> propertyDescriptorList = propertyListMap.get(obj.getClass());
        int i = 0;

        // TODO 主键自增的时候，需要将主键剔除
//        String idName = getIdName(obj);

        for (PropertyDescriptor p : propertyDescriptorList) {
            if (isNull(obj, p)) {
                continue;
            }
            if (i++ != 0) {
                sb.append(',');
            }
            sb.append(getPropertyColumnName(p));
        }
        logger.info(sb.toString());
        return sb.toString();
    }

    /**
     * @param obj POJO实例
     * @Descreption 用于获取Insert的字段累加
     * @Author Bob
     * @Date 2015-12-31 14:28:00
     */
    public static String returnInsertColumnsDefine(Object obj) {
        StringBuilder sb = new StringBuilder();

        List<PropertyDescriptor> propertyDescriptorList = propertyListMap.get(obj.getClass());
        int i = 0;
        for (PropertyDescriptor p : propertyDescriptorList) {
            String column = p.getName();
            if (isNull(obj, p)) {
                continue;
            }
            if (i++ != 0) {
                sb.append(',');
            }
            sb.append("#{").append(column).append('}');
        }
        logger.info(sb.toString());
        return sb.toString();
    }

//    public static String returnWhereDefine(Object obj,
//                                           List<SearchFilter> searchFilterList) {
//        StringBuilder sb = new StringBuilder();
//        // sb.append("name like '%$param2[0].value$%'");
//        List<PropertyDescriptor> propertyDescriptorList = propertyListMap.get(obj
//                .getClass());
//
//        for (int i = 0; i < searchFilterList.size(); i++) {
//            SearchFilter searchFilter = searchFilterList.get(i);
//
//            for (int j = 0; j < propertyDescriptorList.size(); j++) {
//                PropertyDescriptor p = propertyDescriptorList.get(j);
//                if (p.getName().equals(searchFilter.fieldName)) {
//                    sb.append(getPropertyColumnName(p));
//                    break;
//                }
//            }
//            switch (searchFilter.operator) {
//                case EQ:
//                    sb.append(" = ");
//                    sb.append("#{param2[" + i + "].value}");
//                    break;
//                case LIKE:
//                    sb.append(" like ");
//                    // searchFilter.value = "%" + searchFilter.value + "%";
//                    sb.append("concat(concat('%',#{param2[" + i + "].value}),'%')");
//                    // sb.append("#{param2["+i+"].value}");
//                    break;
//                case GT:
//                    sb.append(" > ");
//                    sb.append("#{param2[" + i + "].value}");
//                    break;
//                case LT:
//                    sb.append(" < ");
//                    sb.append("#{param2[" + i + "].value}");
//                    break;
//                case GTE:
//                    sb.append(" >= ");
//                    sb.append("#{param2[" + i + "].value}");
//                    break;
//                case LTE:
//                    sb.append(" <= ");
//                    sb.append("#{param2[" + i + "].value}");
//                    break;
//            }
//
//            if (i != searchFilterList.size() - 1)
//                sb.append(" and ");
//        }
//        return sb.toString();
//    }

    /**
     * 用于获取Update Set的字段累加
     *
     * @return
     */

    public static String returnSetDefine(Object obj) {
        StringBuilder sb = new StringBuilder();

        List<PropertyDescriptor> propertyDescriptorList = propertyListMap.get(obj.getClass());
        int i = 0;
        for (PropertyDescriptor p : propertyDescriptorList) {
            if (isNull(obj, p)) {
                continue;
            }

            Class propertyTypeClass = p.getPropertyType();

            String column = p.getName();

            // 不为基本数据类型。为其它的映射bean
            if (!(propertyTypeClass == Byte.class
                    || propertyTypeClass == Character.class
                    || propertyTypeClass == Boolean.class
                    || propertyTypeClass == Integer.class
                    || propertyTypeClass == Long.class
                    || propertyTypeClass == Float.class
                    || propertyTypeClass == Double.class
                    || propertyTypeClass == String.class
                    || propertyTypeClass == Date.class)
                    || propertyTypeClass == LocalDateTime.class) {
                try {
                    BaseEntity o = (BaseEntity) p.getReadMethod().invoke(obj);
                    column += "." + getIdName(o);
                } catch (IllegalArgumentException e) {
                    logger.error(e.getMessage(), e);
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(), e);
                } catch (InvocationTargetException e) {
                    logger.error(e.getMessage(), e);
                }
            }

            if (i++ != 0) {
                sb.append(',');
            }
            sb.append(getPropertyColumnName(p));
            sb.append("=#{").append(column).append('}');
        }
        return sb.toString();
    }

    /**
     * @param obj POJO实例
     * @Descreption 获取POJO中的主键字段名
     * @Author Bob
     * @Date 2015-12-31 14:28:00
     */
    public static String getIdName(Object obj) {
        String idName = "";
        try {
            BeanInfo intro = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = intro.getPropertyDescriptors();
            for (PropertyDescriptor p : propertyDescriptors) {
                Method method = p.getReadMethod();
                if (method.isAnnotationPresent(Id.class)) {
                    idName = getPropertyColumnName(p);
                    if (null == idName) {
                        idName = p.getName();
                    }
                    break;
                }
            }
            // 如果没有指定，默认使用 “id” 作为主键
            if ("" == idName) {
                idName = "id";
            }
        } catch (IntrospectionException e) {
            throw new RuntimeException("Get idName error... ");
        }
        return idName;
    }

    private static boolean isNull(Object obj, PropertyDescriptor propertyDescriptor) {
        try {
            return null == propertyDescriptor.getReadMethod().invoke(obj);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        }
        return true;
    }

    private static boolean isNotNull(Object obj, PropertyDescriptor propertyDescriptor) {
        return !isNull(obj, propertyDescriptor);
    }

    private static boolean isNull(Object obj, Field field) {
        try {
            field.setAccessible(true);
            return field.get(obj) == null;
        } catch (SecurityException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    private static boolean isNull(Object obj, String fieldname) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldname);
            return isNull(obj, field);
        } catch (SecurityException e) {
            logger.error(e.getMessage(), e);
        } catch (NoSuchFieldException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
        }

        return false;
    }

    /**
     * 用于获取where的字段累加
     *
     * @return
     */
    public static String returnWhereDefine(Object obj) {
        StringBuilder sb = new StringBuilder();

        List<PropertyDescriptor> propertyDescriptorList = propertyListMap.get(obj.getClass());

        int i = 0;
        for (PropertyDescriptor p : propertyDescriptorList) {
            if (isNull(obj, p)) {
                continue;
            }
            Class propertyTypeClass = p.getPropertyType();

            String column = p.getName();

            // 不为基本数据类型。为其它的映射bean
            if (!(propertyTypeClass == Byte.class
                    || propertyTypeClass == Character.class
                    || propertyTypeClass == Boolean.class
                    || propertyTypeClass == Integer.class
                    || propertyTypeClass == Long.class
                    || propertyTypeClass == Float.class
                    || propertyTypeClass == Double.class
                    || propertyTypeClass == String.class
                    || propertyTypeClass == Date.class
                    || propertyTypeClass == LocalDateTime.class)) {
                try {
                    BaseEntity o = (BaseEntity) p.getReadMethod().invoke(obj);
                    column += "." + getIdName(o);
                } catch (IllegalArgumentException e) {
                    logger.error(e.getMessage(), e);
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(), e);
                } catch (InvocationTargetException e) {
                    logger.error(e.getMessage(), e);
                }
            }

            if (i++ != 0) {
                sb.append(" and ");
            }
            sb.append(getPropertyColumnName(p));
            sb.append("=#{").append("param1." + column).append("}");
        }
        return sb.toString();
    }


    /**
     * @param name 待转化字符串
     * @description 下划线格式字符串转换成驼峰格式字符串
     * eg: player_id -> playerId;
     * player_name -> playerName;
     * @author Bob
     * @Date 2015-12-31 12:57:00
     */
    public static String underScore2CamelCase(String name) {
        if (null == name || name.equals("")) {
            return "";
        }
        String[] elems = name.split("_");
        for (int i = 0; i < elems.length; i++) {
            elems[i] = elems[i].toLowerCase();
            if (i != 0) {
                String elem = elems[i];
                char first = elem.toCharArray()[0];
                elems[i] = "" + (char) (first - 32) + elem.substring(1);
            }
        }
        for (String e : elems) {
            System.out.print(e);
        }
        return elems.toString();
    }

    /**
     * @param name 待转换字符串
     * @description 驼峰格式字符串 转换成 下划线格式字符串
     * eg: playerId -> player_id;
     * playerName -> player_name;
     * @author Bob
     * @Date 2015-12-31 12:57:00
     */
    public static String camelCase2UnderScore(String name) {
        if (null == name || name.equals("")) {
            return "";
        }
        Pattern p = Pattern.compile("[A-Z]");
        StringBuilder builder = new StringBuilder(name);
        Matcher mc = p.matcher(name);
        int i = 0;
        while (mc.find()) {
            builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
            i++;
        }
        if ('_' == builder.charAt(0)) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

}
