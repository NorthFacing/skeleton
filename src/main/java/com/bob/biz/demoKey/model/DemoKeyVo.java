package com.bob.biz.demoKey.model;

import com.bob.biz.demoLock.model.DemoLock;

/**
 * DemoKeyVo
 * 
 * @since v0.0.1
 * @author Bob
 * @Date 2015-9-11 14:54:56
 */
public class DemoKeyVo extends DemoKey {

    private DemoLock demoLock;

    public DemoLock getDemoLock() {
        return demoLock;
    }

    public void setDemoLock(DemoLock demoLock) {
        this.demoLock = demoLock;
    }

}
