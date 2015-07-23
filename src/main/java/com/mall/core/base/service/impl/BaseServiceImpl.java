package com.mall.core.base.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.core.base.mapper.BaseMapper;
import com.mall.core.base.model.BaseModel;
import com.mall.core.base.service.BaseService;

public class BaseServiceImpl<M extends BaseModel, VO extends M> implements BaseService<M, VO> {

    @Autowired
    private BaseMapper<M, VO> baseMapper;

    @Override
    public String edit(M model) {
        Validate.notNull(model);
        if (StringUtils.isBlank(model.getId())) {
            baseMapper.insert(model);
        } else {
            baseMapper.updateByPrimaryKeySelective(model);
        }
        return model.getId();
    }

    @Override
    public String add(M model) {
        Validate.notNull(model);
        baseMapper.insert(model);
        return model.getId();
    }

    @Override
    public M getById(String id) {
        Validate.notNull(id);
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<M> getList(M model) {
        return baseMapper.select(model);
    }

    @Override
    public PageInfo<M> getPage(VO modelVo) {
        PageHelper.startPage(modelVo.getPage(), modelVo.getRows());
        List<M> list = baseMapper.select(modelVo);
        return new PageInfo<M>(list);
    }

    /**
     * 此方法值只更新字段不为空的值，如果想要圈子段更新，请使用updateByPrimaryKey
     * 
     * @since v0.0.1
     * @author Bob
     * @created 2015年7月23日 上午9:59:23
     */
    @Override
    public void updateById(M model) {
        Validate.notNull(model);
        Validate.notNull(model.getId());
        baseMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public boolean delById(String id) {
        Validate.notNull(id);
        baseMapper.deleteByPrimaryKey(id);
        return true;
    }

}
