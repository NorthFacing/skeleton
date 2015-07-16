package com.mall.core.base.service;

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

    public Integer add(M model);

    public M getById(Integer id);

    public List<M> getList(M model);

    public PageInfo<M> getPage(int pageNum, int pageSize, M model);

    public void updateById(M model);

    public boolean delById(Integer id);

}
