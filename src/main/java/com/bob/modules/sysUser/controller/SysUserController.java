package com.bob.modules.sysUser.controller;

import com.bob.core.base.controller.BaseController;
import com.bob.core.utils.Result;
import com.bob.core.utils.page.PageUtil;
import com.bob.modules.sysUser.entity.SysUser;
import com.bob.modules.sysUser.entity.SysUserQuery;
import com.bob.modules.sysUser.entity.SysUserVo;
import com.bob.modules.sysUser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * SysUserController
 *
 * @author Bob
 * @Date 2016-1-3 22:44:45
 * @since v0.0.1
 */
@Controller
@RequestMapping("/admin")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/sysUser/save")
    public Result save(SysUser entity){
        Result result = Result.fail();

        return result;
    }

    @RequestMapping(value = "/sysUser/list")
    public String list() {
        return "/sysUser/list";
    }

    @RequestMapping(value = "/sysUser/pageData")
    public Map<String, Object> pageData(SysUserQuery query) {
        SysUser entity = new SysUser();
        entity.setUserName("Bob");
        sysUserService.save(entity);

        SysUser param1 = new SysUser();
        param1.setId(entity.getId());
        SysUser result1 = sysUserService.select(param1);
        System.out.println(result1);
        SysUserVo result2 = sysUserService.selectVo(param1);
        System.out.println(result2);
        List<SysUser> result3 = sysUserService.selectList(param1);
        System.out.println(result3);
        List<SysUserVo> result4 = sysUserService.selectVoList(param1);
        System.out.println(result4);

        query = sysUserService.pageData(query);
        return PageUtil.convertPage(query);
    }

}

