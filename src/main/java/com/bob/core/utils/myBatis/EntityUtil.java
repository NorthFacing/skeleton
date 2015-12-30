package com.bob.core.utils.myBatis;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析POJO的类
 *
 * @author henliqi
 */
public class EntityUtil {

    public static final Logger logger = LoggerFactory.getLogger(EntityUtil.class);

    /**
     * 获取POJO对应的表名 需要POJO中的属性定义@Table(name)
     *
     * @return
     * @throws Exception
     */
    public static String tablename(Object obj) throws Exception {
        Table table = obj.getClass().getAnnotation(Table.class);
        if (table != null)
            return table.name();
        else
            throw new Exception(
                    "undefine POJO @Table, need Tablename(@Table(name))");
    }

    /**
     * 用于计算类定义 需要POJO中的属性定义@Column(name)
     */
    public static void caculationColumnList(Object obj) {
        if (columnMap.containsKey(obj.getClass()))
            return;

        BeanInfo intro = null;
        try {
            intro = Introspector.getBeanInfo(obj.getClass());
        } catch (IntrospectionException e) {
            logger.error(e.getMessage(),e);
        }
        PropertyDescriptor[] propertyDescriptors = intro.getPropertyDescriptors();
        List<PropertyDescriptor> columnList = new ArrayList<PropertyDescriptor>(propertyDescriptors.length);
        for (PropertyDescriptor p : propertyDescriptors) {
            Method method = p.getReadMethod();
            if (method.isAnnotationPresent(Column.class)) {
                columnList.add(p);
            }
        }
        columnMap.put(obj.getClass(), columnList);
    }

    /**
     * 获取POJO中的主键字段名 需要定义@Id
     *
     * @return
     */
    public static String id(Object obj) {
        String idName = "";
        try {
            BeanInfo intro = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = intro
                    .getPropertyDescriptors();
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
        } catch (IntrospectionException e) {
            throw new RuntimeException("undefine POJO @Id");
        }
        return idName;
    }

    /**
     * 获得列Column的数字库列名
     *
     * @param p
     * @return
     */
    public static String getPropertyColumnName(PropertyDescriptor p) {
        String methodName = null;
        Method method = p.getReadMethod();
        if (method.isAnnotationPresent(Column.class)) {
            Annotation columnAnnotation = method.getAnnotation(Column.class);
            if (null != columnAnnotation) {
                Column column = (Column) columnAnnotation;
                String columnName = column.name();
                if (StringUtils.isNotBlank(columnName)) {
                    methodName = columnName;
                }
            }
            if (StringUtils.isBlank(methodName)) {
                methodName = p.getName();
            }
        }
        return methodName;
    }

    /**
     * 获取用于WHERE的 有值字段表
     *
     * @return
     */
    public static List<WhereColumn> returnWhereColumnsName(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        List<WhereColumn> columnList = new ArrayList<WhereColumn>(fields.length);

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class) && !isNull(obj, field))
                columnList.add(new WhereColumn(field.getName(), field
                        .getGenericType().equals(String.class)));
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

        List<PropertyDescriptor> propertyDescriptorList = columnMap.get(obj
                .getClass());

        for (int i = 0; i < propertyDescriptorList.size(); i++) {
            PropertyDescriptor p = propertyDescriptorList.get(i);
            sb.append(getPropertyColumnName(p));
            sb.append(" as ");
            sb.append(p.getName());
            if (i != propertyDescriptorList.size() - 1) {
                sb.append(" , ");
            }
        }
//      mysql 数据库没有rownum字段
//		sb.append(" , rownum as rn ");

        return sb.toString();
    }

    /**
     * 用于获取Insert的字段累加
     *
     * @return
     * @throws Exception
     */
    public static String returnInsertColumnsName(Object obj) throws Exception {
        StringBuilder sb = new StringBuilder();

        List<PropertyDescriptor> propertyDescriptorList = columnMap.get(obj
                .getClass());
        int i = 0;

        String idName = id(obj);

        for (PropertyDescriptor p : propertyDescriptorList) {
            if (!idName.equals(p.getName()) && isNull(obj, p)) {
                continue;
            }
            if (i++ != 0) {
                sb.append(',');
            }

            sb.append(getPropertyColumnName(p));
        }
        return sb.toString();
    }

    /**
     * 用于获取Insert的字段映射累加
     *
     * @return
     */
    public static String returnInsertColumnsDefine(Object obj) {
        StringBuilder sb = new StringBuilder();

        String idName = id(obj);

        List<PropertyDescriptor> propertyDescriptorList = columnMap.get(obj
                .getClass());
        int i = 0;
        for (PropertyDescriptor p : propertyDescriptorList) {
            String column = p.getName();
            if (!idName.equals(column) && isNull(obj, p)) {
                continue;
            }

            Class propertyTypeClass = p.getPropertyType();
            // 不为基本数据类型。为其它的映射bean
            if (!(propertyTypeClass == Byte.class
                    || propertyTypeClass == Character.class
                    || propertyTypeClass == Boolean.class
                    || propertyTypeClass == Integer.class
                    || propertyTypeClass == Long.class
                    || propertyTypeClass == Float.class
                    || propertyTypeClass == Double.class
                    || propertyTypeClass == String.class || propertyTypeClass == Date.class)) {
                try {
                    IdEntity o = (IdEntity) p.getReadMethod().invoke(obj);
                    column += "." + idBeanName(o);
                } catch (IllegalArgumentException e) {
                    logger.error(e.getMessage(),e);
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(),e);
                } catch (InvocationTargetException e) {
                    logger.error(e.getMessage(),e);
                }
            }
            if (i++ != 0)
                sb.append(',');
            sb.append("#{")
                    .append(column)
                    .append('}');
        }
        return sb.toString();
    }
//
//    public static String returnWhereDefine(Object obj,
//                                           List<SearchFilter> searchFilterList) {
//        StringBuilder sb = new StringBuilder();
//        // sb.append("name like '%$param2[0].value$%'");
//        List<PropertyDescriptor> propertyDescriptorList = columnMap.get(obj
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

        List<PropertyDescriptor> propertyDescriptorList = columnMap.get(obj
                .getClass());
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
                    || propertyTypeClass == String.class || propertyTypeClass == Date.class)) {
                try {
                    IdEntity o = (IdEntity) p.getReadMethod().invoke(obj);
                    column += "." + idBeanName(o);
                } catch (IllegalArgumentException e) {
                    logger.error(e.getMessage(),e);
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(),e);
                } catch (InvocationTargetException e) {
                    logger.error(e.getMessage(),e);
                }
            }

            if (i++ != 0)
                sb.append(',');
            sb.append(getPropertyColumnName(p));
            sb.append("=#{").append(column).append('}');
        }
        return sb.toString();
    }

    public static String idBeanName(IdEntity obj) {
        String idName = "";
        try {
            BeanInfo intro = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = intro
                    .getPropertyDescriptors();
            for (PropertyDescriptor p : propertyDescriptors) {
                Method method = p.getReadMethod();
                if (method.isAnnotationPresent(Id.class)) {
                    idName = p.getName();
                    break;
                }
            }
        } catch (IntrospectionException e) {
            throw new RuntimeException("undefine POJO @Id");
        }
        return idName;
    }

    /**
     * 用于存放POJO的列信息
     */
    private static Map<Class, List<PropertyDescriptor>> columnMap = new HashMap<Class, List<PropertyDescriptor>>();

    private static boolean isNull(Object obj,
                                  PropertyDescriptor propertyDescriptor) {
        try {
            return propertyDescriptor.getReadMethod().invoke(obj) == null;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(),e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(),e);
        }
        return true;
    }

    private static boolean isNotNull(Object obj,
                                     PropertyDescriptor propertyDescriptor) {
        return !isNull(obj, propertyDescriptor);
    }

    private static boolean isNull(Object obj, Field field) {
        try {
            field.setAccessible(true);
            return field.get(obj) == null;
        } catch (SecurityException e) {
            logger.error(e.getMessage(),e);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(),e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(),e);
        }
        return false;
    }

    private static boolean isNull(Object obj, String fieldname) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldname);
            return isNull(obj, field);
        } catch (SecurityException e) {
            logger.error(e.getMessage(),e);
        } catch (NoSuchFieldException e) {
            logger.error(e.getMessage(),e);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(),e);
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

        List<PropertyDescriptor> propertyDescriptorList = columnMap.get(obj.getClass());

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
                    || propertyTypeClass == String.class || propertyTypeClass == Date.class)) {
                try {
                    IdEntity o = (IdEntity) p.getReadMethod().invoke(obj);
                    column += "." + idBeanName(o);
                } catch (IllegalArgumentException e) {
                    logger.error(e.getMessage(),e);
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(),e);
                } catch (InvocationTargetException e) {
                    logger.error(e.getMessage(),e);
                    System.out.println();
                }
            }

            if (i++ != 0)
                sb.append(" and ");
            sb.append(getPropertyColumnName(p));
            sb.append("=#{").append("param1." + column).append("}");
        }
        return sb.toString();
    }

}
