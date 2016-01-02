package com.bob.modules.sysResource;

import com.bob.core.base.BaseMapperTest;
import com.bob.modules.sysResource.entity.SysResource;
import com.bob.modules.sysResource.entity.SysResourceQuery;
import com.bob.modules.sysResource.entity.SysResourceVo;
import com.bob.modules.sysResource.mapper.SysResourceMapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Bob on 2015/12/31.
 */
public class SysResourceMapperTest extends BaseMapperTest {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private SysResourceMapper sysResourceMapper;

    private SysResource getSysResource() {
        SysResource sysResource = new SysResource();
        sysResource.setName("name");
        sysResource.setLevel(1);
        return sysResource;
    }

    @Test
    public void insertTest() {
        SysResource sysResource = getSysResource();
        sysResourceMapper.insert(sysResource);
    }

    @Test
    public void selectTest() {
        SysResource sysResource = new SysResource();

        sysResource.setOrderBy("level");

        List<SysResourceVo> url = sysResourceMapper.selectVoList(sysResource);
        System.out.println(url.size());
        System.out.println(url.toString());


        SysResourceQuery sysResourceQuery = new SysResourceQuery();

        Long count = sysResourceMapper.count(sysResourceQuery);
        System.out.println(count);

        sysResourceQuery.setPageNum(2);
        sysResourceQuery.setPageSize(20);
        sysResourceQuery.setTotal(count);

        List<SysResource> query = sysResourceMapper.query(sysResourceQuery);
        System.out.println(query.size());
        System.out.println(query.toString());

        sysResourceQuery.setResult(query);
        System.out.println(sysResourceQuery);
    }

    @Test
    public void updateTest() {
        SysResource sysResource = new SysResource();
        sysResource.setId("9560060BCB2D449FB986FCE320859C0F");
        sysResource.setType(2);
        sysResource.setStatus(2);
        sysResourceMapper.update(sysResource);
    }

    @Test
    public void deleteTest(){
        SysResource sysResource = new SysResource();
        sysResource.setId("9560060BCB2D449FB986FCE320859C0F");
        sysResourceMapper.delete(sysResource);
    }

}
