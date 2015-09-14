package com.bob.biz.demoLock.model;

import java.util.List;

import com.bob.biz.demoKey.model.DemoKey;

/**
 * DemoLockVo
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-9-11 14:54:56
 */
public class DemoLockVo extends DemoLock {

    private List<DemoKey> demoKeyList;

    public List<DemoKey> getDemoKeyList() {
        return demoKeyList;
    }

    public void setDemoKeyList(List<DemoKey> demoKeyList) {
        this.demoKeyList = demoKeyList;
    }

}
