package com.bob.core.utils.shiro;

import com.bob.core.contants.StatusCode;
import com.bob.modules.sysUser.entity.SysUser;
import com.bob.modules.sysUser.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(sysUserService.getRolesNameByUserId(username));
        authorizationInfo.setStringPermissions(sysUserService.getResourcesNameByUserId(username));
        return authorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();

        SysUser user = sysUserService.getByUserName(username);

        if (user == null || StatusCode.DELETE == user.getStatus()) {
            throw new UnknownAccountException();//没找到帐号
        }

        if (StatusCode.DISABLE == user.getStatus()) {
            throw new LockedAccountException(); //帐号锁定
        }

        if (StatusCode.DISABLE == user.getStatus()) {
            throw new LockedAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm 使用 CredentialsMatcher 进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username, // 用户名
                user.getPassWord(), // 密码
                // salt = constant + username + salt
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
