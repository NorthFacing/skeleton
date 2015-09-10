package com.bob.core.base.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * BaseService公共类
 * 
 * @since v0.0.1
 * @author Bob
 * @created 2015年7月3日 下午6:00:51
 */
public interface BaseService<M, VO> {

    public String edit(M model);

    public String add(M model);

    public M getById(String id);

    public VO getVoById(String id);

    public List<M> getList(VO modelVo);

    public List<VO> getVoList(VO modelVo);

    public PageInfo<M> getPage(int pageNum, int pageSize, VO modelVo);

    public PageInfo<VO> getVoPage(int pageNum, int pageSize, VO modelVo);

    public void updateById(M model);

    public boolean delById(String id);

}
