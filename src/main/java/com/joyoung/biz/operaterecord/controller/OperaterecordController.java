package com.joyoung.biz.operaterecord.controller;

import com.joyoung.biz.operaterecord.entity.Operaterecord;
import com.joyoung.biz.operaterecord.entity.OperaterecordQuery;
import com.joyoung.biz.operaterecord.service.OperaterecordService;
import com.joyoung.core.utils.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class OperaterecordController {

  @Autowired
  private OperaterecordService operaterecordService;

  @RequestMapping(value = "/operaterecord/list")
  public String list() {
    return "/operaterecord/list";
  }

  @ResponseBody
  @RequestMapping(value = "/operaterecord/pageData")
  public Result<OperaterecordQuery> pageData(OperaterecordQuery query) {
    query = operaterecordService.pageData(query);
    return Result.success(query);
  }

}

