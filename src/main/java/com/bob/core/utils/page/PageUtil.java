package com.bob.core.utils.page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bob on 2016/1/4.
 */
public class PageUtil {
    public static Map<String, Object> convertPage(BaseQuery baseQuery) {
        Map<String, Object> result = new HashMap<>();
        result.put("current", baseQuery.getPageNum());
        result.put("rowCount", baseQuery.getPageSize());
        result.put("total", baseQuery.getTotalCount());
        result.put("rows", baseQuery.getResult());
        return result;
    }
}
