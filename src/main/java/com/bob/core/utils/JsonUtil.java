package com.bob.core.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonUtil {

  public static Map<String, Object> getMap4Json(String jsonString) {
    if (jsonString == null) {
      jsonString = "{}";
    }
    Map<String, Object> valueMap = new HashMap<String, Object>();
    JSONObject jsonObject = JSONObject.parseObject(jsonString);
    Iterator<String> keyIter = jsonObject.keySet().iterator();
    String key;
    Object value;
    while (keyIter.hasNext()) {
      key = keyIter.next();
      value = jsonObject.get(key);
      valueMap.put(key, value);
    }
    return valueMap;
  }

  public static void main(String[] args) {
    try {
      Map<String, Object> m = JsonUtil.getMap4Json(null);
      System.out.println(m.get("userName"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
