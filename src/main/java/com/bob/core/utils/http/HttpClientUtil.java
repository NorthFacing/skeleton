package com.bob.core.utils.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Set;

/**
 * Created by Bob on 2016/7/29.
 */
public class HttpClientUtil {

  private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

  static HttpClient client = new HttpClient();

  public static JSONObject httpPost(String url, String params) {
    JSONObject jsonObject = null;
    PostMethod method = new PostMethod(url);
    try {
      JSONObject param = (JSONObject) JSONObject.parse(params);
      Set<String> keys = param.keySet();
      if (CollectionUtils.isNotEmpty(keys)) {
        for (String key : keys) {
          method.addParameter(key, param.getString(key));
        }
      }
      client.executeMethod(method);
      String response = method.getResponseBodyAsString();
      jsonObject = (JSONObject) JSONObject.parse(response);
    } catch (IOException e) {
      logger.error("httpPost方法出错：{}", e);
    } catch (Exception e) {
      logger.error("httpPost方法出错：{}", e);
    }
    return jsonObject;
  }

  public static JSONObject httpGet(String url, String params) {
    JSONObject jsonObject = null;
    url = getParams(url, params);
    GetMethod method = new GetMethod(url);
    try {
      client.executeMethod(method);
      String response = method.getResponseBodyAsString();
      jsonObject = (JSONObject) JSONObject.parse(response);
    } catch (IOException e) {
      logger.error("httpPost方法出错：{}", e);
    } catch (Exception e) {
      logger.error("httpPost方法出错：{}", e);
    }
    return jsonObject;
  }

  public static String getParams(String url, String params) {
    if (StringUtils.isEmpty(params)) {
      return url;
    }
    StringBuilder sb = new StringBuilder(url);
    if (url.indexOf("?") == -1) {
      sb.append("?");
    } else {
      sb.append("&");
    }
    boolean first = true;
    JSONObject param = (JSONObject) JSONObject.parse(params);
    Set<String> keys = param.keySet();
    if (CollectionUtils.isNotEmpty(keys)) {
      for (String key : keys) {
        if (first) {
          first = false;
        } else {
          sb.append("&");
        }
        String value = param.getString(key);
        sb.append(key).append("=");
        if (StringUtils.isNotEmpty(value)) {
          try {
            sb.append(URLEncoder.encode(value, "UTF-8"));
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return sb.toString();
  }
}
