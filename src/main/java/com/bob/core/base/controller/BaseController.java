package com.bob.core.base.controller;

import com.bob.core.utils.web.Result;
import com.bob.core.utils.web.ResultEnums;
import com.bob.core.utils.web.ServletUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BaseController公共类
 *
 * @author Bob
 * @created 2015年7月3日 下午6:11:56
 * @since v0.1
 */
@Slf4j
public class BaseController {

  /**
   * 异常处理
   *
   * @throws IOException
   * @author Bob
   * @created 2015年7月3日 下午11:54:10
   * @since v0.1
   */
  public Result handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
    Result result = Result.fail();
    if (e instanceof IllegalArgumentException) {
      log.error(e.getMessage());
      if (ServletUtils.isAjax(request)) {
        result.enumResult(ResultEnums.SUCCESS);
      } else {
        response.sendRedirect("/");
      }
    }
    return result;
  }

}
