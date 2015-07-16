package com.mall.core.base.mappers;

import java.util.List;

public interface BaseMapper<M, VO> {

    public void add(M model);

    public <E> E getById(String id);

    public <E> List<E> getList(VO vo);

    public void updateById(M model);

    public void delById(String id);
}
