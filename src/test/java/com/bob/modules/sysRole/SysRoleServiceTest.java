package com.bob.modules.sysRole;

import com.bob.core.base.BaseServiceTest;
import com.bob.modules.sysRole.entity.SysRole;
import com.bob.modules.sysRole.entity.SysRoleVo;
import com.bob.modules.sysRole.service.SysRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Bob on 2016/1/4.
 */
public class SysRoleServiceTest extends BaseServiceTest {
  @Autowired
  private SysRoleService sysRoleService;

  private SysRole getSysRole() {
    SysRole entity = new SysRole();
    entity.setName("name");
    return entity;
  }

  private SysRoleVo getSysRoleVo() {
    SysRoleVo entity = new SysRoleVo();
    entity.setName("name");
    return entity;
  }

  @Test
  public void saveTest() {

  }

}
