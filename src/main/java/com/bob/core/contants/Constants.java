package com.bob.core.contants;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * 应用常量封装类
 *
 * @author Bob
 * @created 2015年7月6日 下午1:31:04
 * @since v0.1
 */
public class Constants {


  public static final String PROJECT_NAME = "skeleton";

  /**
   * 默认查询页码
   */
  public static final Integer PAGE_NUM = 1;
  /**
   * 默认每个页面查询数量
   */
  public static final Integer PAGE_SIZE = 10;

  public static final String TEMPLATE_ORDER_BY = "orderBy";

  public static final String SESSSION_ID_KEY = "sessionId";
  public static final String SESSSION_OBJ_KEY = "sessionOdj";

  /**
   * 权限类型
   */
  public static final Integer RES_TYPE_MODULE = 1;// 模块名称
  public static final Integer RES_TYPE_MENU = 2;// 菜单
  public static final Integer RES_TYPE_PERMISSION = 3;// 权限


  public static final int SECOND = 1;
  public static final int MINUTE = 60 * SECOND;
  public static final int HOUR = 60 * MINUTE;
  public static final int DAY = 24 * HOUR;
  public static final int WEEK = 7 * DAY;

  public static final HashMap<String, JSONObject> logMap = new HashMap<>();

}
