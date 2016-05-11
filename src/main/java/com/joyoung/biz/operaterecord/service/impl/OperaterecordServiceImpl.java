package com.joyoung.biz.operaterecord.service.impl;

import com.joyoung.biz.operaterecord.entity.Operaterecord;
import com.joyoung.biz.operaterecord.entity.OperaterecordQuery;
import com.joyoung.biz.operaterecord.entity.OperaterecordVo;
import com.joyoung.biz.operaterecord.mapper.OperaterecordMapper;
import com.joyoung.biz.operaterecord.service.OperaterecordService;
import com.joyoung.core.base.mapper.BaseMapper;
import com.joyoung.core.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OperaterecordService")
public class OperaterecordServiceImpl extends BaseServiceImpl<Operaterecord, OperaterecordVo, OperaterecordQuery> implements OperaterecordService {

  @Autowired
  private OperaterecordMapper operaterecordMapper;

  @Override
  public Operaterecord getEntity() {
    return new Operaterecord();
  }

  @Override
  public BaseMapper<Operaterecord, OperaterecordVo, OperaterecordQuery> getMapper() {
    return operaterecordMapper;
  }
}