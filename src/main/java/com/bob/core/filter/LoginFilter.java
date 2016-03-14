package com.bob.core.filter;

import com.bob.core.contants.Constants;
import com.bob.core.contants.ResultCode;
import com.bob.core.session.LoginUser;
import com.bob.core.session.UserSession;
import com.bob.core.utils.ApplicationContextUtil;
import com.bob.core.utils.web.CookieUtils;
import com.bob.core.utils.web.RequestContext;
import com.bob.core.utils.web.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @登录检验过滤器
 */
public class LoginFilter implements Filter {

    @SuppressWarnings("unused")
    private static Logger log = Logger.getLogger(LoginFilter.class);
    private static Map<String, String> noFilterMap = new HashMap<String, String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String noFilter = filterConfig.getInitParameter("noFilter");
        if (StringUtils.isNotBlank(noFilter)) {
            String[] split = noFilter.split(",");
            for (String s : split) {
                noFilterMap.put(s, s);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        requestURI = requestURI.substring(contextPath.length(), requestURI.length());
        requestURI = requestURI.split("\\?")[0];

        if (noFilterMap.containsKey(requestURI)) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        Set<String> keySet = noFilterMap.keySet();
        for (String key : keySet) {
            boolean startsWith = requestURI.startsWith(key);
            if (startsWith) {
                chain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        // 获取用户cookie
        Cookie userCookie = CookieUtils.getCookie(request, Constants.SESSSION_ID_KEY);
        if (userCookie == null) {
            redirectLogin(request, response);
            return;
        }
        // 获取session
        WebApplicationContext context = ApplicationContextUtil.getContext();
        RedisCacheService redis = (RedisCacheService) context.getBean("cacheService");
        UserSession<LoginUser> session = (UserSession<LoginUser>) redis.get(userCookie.getValue());
        if (session == null) {
            redirectLogin(request, response);
            return;
        }
        /*
         * 更新缓存
         */
        CookieUtils.addCookie(request, response, Constants.SESSSION_ID_KEY, session.getSessionId(), BizCons.USER_SESSION_TIMEOUT,BizCons.APP_DOMAIN, "/");
        redis.set(session.getSessionId(), session, BizCons.USER_SESSION_TIMEOUT);

        // TODO
        RequestContext.setLoginUser(session);
        RequestContext.setLoginUserId(session.getUserId());
        RequestContext.setLoginUserName(session.getLoginName());

        chain.doFilter(servletRequest, servletResponse);
    }

    public void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws IOException,
        ServletException {
        String requestedWith = request.getHeader("x-requested-with");
        // ajax请求
        if (requestedWith != null && "XMLHttpRequest".equals(requestedWith)) {
            PrintWriter out = response.getWriter();
            out.print(Result.fail(ResultCode.SESSION_EXPIRE));
            out.flush();
            return;
        } else {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/login");
            return;
        }
    }

    @Override
    public void destroy() {
    }
}
