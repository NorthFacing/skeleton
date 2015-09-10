package com.bob.core.base.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bob.core.base.mapper.BaseMapper;
import com.bob.core.base.model.BaseModel;
import com.bob.core.base.service.BaseService;

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
    public VO getVoById(String id){
        Validate.notNull(id);
        return baseMapper.getVoById(id);
    }
    
    @Override
    public List<M> getList(VO modelVo) {
        return baseMapper.getList(modelVo);
    }

    @Override
    public List<VO> getVoList(VO modelVo) {
        return baseMapper.getVoList(modelVo);
    }

    @Override
    public PageInfo<M> getPage(int pageNum, int pageSize, VO modelVo) {
        PageHelper.startPage(pageNum, pageSize);
        List<M> list = baseMapper.getList(modelVo);
        return new PageInfo<M>(list);
    }

    @Override
    public PageInfo<VO> getVoPage(int pageNum, int pageSize, VO modelVo) {
        PageHelper.startPage(pageNum, pageSize);
        List<VO> list = baseMapper.getVoList(modelVo);
        return new PageInfo<VO>(list);
    }

    /**
     * 此方法值只更新字段不为空的值，如果想要全字段更新，请使用updateByPrimaryKey
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
