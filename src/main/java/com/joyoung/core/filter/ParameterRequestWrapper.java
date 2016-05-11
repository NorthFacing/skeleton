package com.joyoung.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 请求参数的处理
 *
 * @author Bob
 * @created 2015年7月3日 下午9:27:21
 * @since v0.1
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private final Map<String, String[]> params = new HashMap<String, String[]>();

    /**
     * 参数进行trim处理
     *
     * @param request
     * @author Bob
     * @created 2015年7月3日 下午9:27:21
     * @since v0.1
     */
    public ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        this.params.putAll(request.getParameterMap());
        this.trimParameterValues();
    }

    /**
     * 参数扩展
     *
     * @param request
     * @param extendParams
     * @author Bob
     * @created 2015年7月3日 下午9:29:32
     * @since v0.1
     */
    public ParameterRequestWrapper(HttpServletRequest request, Map<String, Object> extendParams) {
        this(request);
        this.addAllParameters(extendParams);
    }

    // 将parameter的值去除空格后重写回去
    private void trimParameterValues() {
        Set<String> set = params.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = it.next();
            String[] values = params.get(key);
            values[0] = values[0].trim();
            params.put(key, values);
        }
    }

    // 增加多个参数
    private void addAllParameters(Map<String, Object> otherParams) {
        for (Map.Entry<String, Object> entry : otherParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }

    // 增加参数
    private void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }

    // 重写getParameter，代表参数从当前类中的map获取
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    // 同上
    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

}
