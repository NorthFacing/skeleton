package com.bob.modules.weChat.utils;

//import com.bob.core.utils.JsonUtil;
//import com.bob.core.utils.http.HttpClientUtil;
//import com.bob.core.utils.http.HttpRequest;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WxUtil {

  /**
   * 保存openId到cookie
   *
   * @author Bob
   * @created 2015年8月18日 下午2:42:04
   */
  public static boolean setOpenIdToCookie(HttpServletRequest request, HttpServletResponse response) {
    String openId = getOpenId(request);
    if (openId == null) {
      return false;
    }
    Cookie cookie = new Cookie("openId", openId);
    cookie.setMaxAge(3600 * 24);
    response.addCookie(cookie);
    return true;
  }

  /**
   * 检查openId是否为空
   *
   * @author Bob
   * @created 2015年8月19日 下午4:24:58
   * @since v0.1
   */
  public static boolean checkOpenId(HttpServletRequest request) {
    boolean exist = false;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("openId")) {
          exist = true;
          break;
        }
      }
    }
    return exist;
  }

  /**
   * 接收微信服务器请求，获取openId
   *
   * @author Bob
   * @created 2015年8月18日 下午2:57:39
   */
  public static String getOpenId(HttpServletRequest request) {
    String code = request.getParameter("code");
    if (code == null) {
      return null;
    }
    String openId = getOpenId(code);
    return openId;
  }

  public static String getOpenId(String code) {
    String url = "https://api.weixin.qq.com/sns/oauth2/access_token"
            + "?appid=wx9384e04d490c6dce&secret=24b5879f0a654926bcd8745b2114968e&code=" + code
            + "&grant_type=authorization_code";
//    String sd = HttpClientUtil.httpsPost(url, null);
//    Map<String, Object> m = JsonUtil.getMap4Json(sd);
//    return (String) m.get("openid");
    return null;
  }

  public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
    Map<String, String> map = new HashMap<String, String>();
    InputStream inputStream = request.getInputStream();
    SAXReader reader = new SAXReader();
    Document document = reader.read(inputStream);
    Element root = document.getRootElement();
    @SuppressWarnings("unchecked")
    List<Element> elementList = root.elements();
    for (Element e : elementList) {
      map.put(e.getName(), e.getText());
    }
    inputStream.close();
    inputStream = null;
    return map;
  }

}
