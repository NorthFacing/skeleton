package com.bob.modules.sysLog;

import com.alibaba.fastjson.JSONObject;
import com.bob.core.base.BaseMockTest;
import com.bob.core.utils.ResultEnums;
import com.bob.modules.sysLoginLog.entity.SysLoginLog;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Bob on 2016/1/13.
 */
public class SysLogMockTest extends BaseMockTest {

  @Test
  public void saveTest() throws Exception {
    SysLoginLog sysLoginLog = new SysLoginLog();
    sysLoginLog.setUserName("mockTest");
    JSONObject result = restJsonPost("/admin/sysLoginLog/save", sysLoginLog);
    Assert.assertTrue(ResultEnums.SUCCESS.getCode() == result.get("code"));
  }

}
