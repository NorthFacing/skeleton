package com.bob.core.utils.shiro;

import com.bob.core.contants.StatusCode;
import com.bob.modules.sysUser.entity.SysUser;
import com.bob.modules.sysUser.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 可以使用多个Realm对应有多个策略，比如至少一个通过的策略就可以用于 账号/手机号/邮箱 + 密码 至少有一个通过就通过的验证方案
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

//        String username = (String) principals.getPrimaryPrincipal();
//        SysUser user = sysUserService.getByUserName(username);

        Session session = SecurityUtils.getSubject().getSession();
        SysUser user = (SysUser) session.getAttribute("user");

        if (null == user) {                                     // 一般只要登陆成功就不会出现找不到用户的情况，这里只是作为加强验证
            return authorizationInfo;
        } else if ("admin".equals(user.getUserName())) {        // 如果是admin账号，作为超级管理员，拥有所有角色和所有权限
            authorizationInfo.setRoles(sysUserService.getAllRolesName());
            authorizationInfo.setStringPermissions(sysUserService.getAllPermissions());
        } else {                                                // 一般账号，只查询赋予的角色和权限
            authorizationInfo.setRoles(sysUserService.getRolesNameByUserId(user.getId()));
            authorizationInfo.setStringPermissions(sysUserService.getPermissionsNameByUserId(user.getId()));
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();

        SysUser user = sysUserService.getByUserName(username);

        if (user == null || StatusCode.DELETE == user.getStatus()) {
            throw new UnknownAccountException();//没找到帐号或者是删除状态
        }

        // 如果找到用户，就将用户信息添加到session会话
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("user", user);

        if (StatusCode.DISABLE == user.getStatus()) {
            throw new DisabledAccountException(); //帐号禁用（账号是禁用状态）
        }

//        锁定应该是使用缓存自动判断的吧？
//        if (StatusCode.LOCKED == user.getStatus()) {
//            throw new LockedAccountException(); //帐号锁定（登陆错误次数过多导致账号锁定）
//        }

        //交给AuthenticatingRealm 使用 CredentialsMatcher 进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username, // 用户名
                user.getPassWord(), // 密码
                // salt = 系统常量 + username + salt
                ByteSource.Util.bytes(PasswordHelper.ENCRYPT + username + user.getSalt()),
                getName() // realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}