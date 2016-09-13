package com.bob.core.utils.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
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

  public static JSONObject httpPost(String url, String params) throws Exception {
    return httpPost(url, null, params);
  }

  public static JSONObject httpPost(String url, String headerParams, String params) throws Exception {

    if (url != null && url.startsWith("https")) {
      ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
      Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
    }

    JSONObject jsonObject = null;
    PostMethod method = new PostMethod(url);
    method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
    try {
      // 设置header参数
      setHeaderParam(method, headerParams);
      // 设置param参数
      setParam(method, params);

      int respCode = client.executeMethod(method);
      String response = method.getResponseBodyAsString();
      jsonObject = (JSONObject) JSONObject.parse(response);
      if (jsonObject == null)
        jsonObject = new JSONObject();
      jsonObject.put("respCode", respCode);
    } catch (IOException e) {
      logger.error("httpPost方法出错：{}", e);
      throw new IOException(e);
    } catch (Exception e) {
      logger.error("httpPost方法出错：{}", e);
      throw new Exception(e);
    } finally {
      method.releaseConnection();
    }
    return jsonObject;
  }

  public static JSONObject httpGet(String url, String params) throws Exception {
    return httpGet(url, null, params);
  }

  public static JSONObject httpGet(String url, String headerParams, String params) throws Exception {
    if (url != null && url.startsWith("https")) {
      ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
      Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
    }

    JSONObject jsonObject = null;
    // 拼装param参数
    url = getParams(url, params);
    GetMethod method = new GetMethod(url);
    try {
      // 设置header参数
      setHeaderParam(method, headerParams);
      int respCode = client.executeMethod(method);
      String response = method.getResponseBodyAsString();
      jsonObject = (JSONObject) JSONObject.parse(response);
      if (jsonObject == null)
        jsonObject = new JSONObject();
      jsonObject.put("respCode", respCode);
    } catch (IOException e) {
      logger.error("httpGet方法出错：{}", e);
      throw new IOException(e);
    } catch (Exception e) {
      logger.error("httpGet方法出错：{}", e);
      throw new Exception(e);
    } finally {
      method.releaseConnection();
    }
    return jsonObject;
  }

  /**
   * GET请求方式参数的拼装
   *
   * @param url    拼装之前的url地址
   * @param params 需要拼装的参数
   * @return 拼装之后的URL地址
   */
  private static String getParams(String url, String params) {
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

  /**
   * 设置Header参数
   *
   * @param method       Method对象
   * @param headerParams header参数
   */
  private static void setHeaderParam(HttpMethodBase method, String headerParams) {
    if (StringUtils.isNotEmpty(headerParams)) {
      JSONObject param = (JSONObject) JSONObject.parse(headerParams);
      Set<String> keys = param.keySet();
      if (CollectionUtils.isNotEmpty(keys)) {
        for (String key : keys) {
          method.addRequestHeader(key, param.getString(key));
        }
      }
    }
  }

  /**
   * 设置POST请求方式的参数
   *
   * @param method Method对象
   * @param params param参数
   */
  private static void setParam(PostMethod method, String params) {
    if (StringUtils.isNotEmpty(params)) {
      JSONObject param = (JSONObject) JSONObject.parse(params);
      Set<String> keys = param.keySet();
      if (CollectionUtils.isNotEmpty(keys)) {
        for (String key : keys) {
          method.addParameter(key, param.getString(key));
        }
      }
    }
  }

}
