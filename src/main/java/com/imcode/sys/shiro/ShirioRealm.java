package com.imcode.sys.shiro;

import com.imcode.sys.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;


public class ShirioRealm extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("登录认证");

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //获取用户输入的用户名和秘密
        String ussername = usernamePasswordToken.getUsername();
        String password = new String( usernamePasswordToken.getPassword());

        //根据用户名去DB中查询对应的用户信息
        User user  =new User("admin","3635b070bdf50a94f350c1d050ab1b61",0);
        if(!user.getUsername().equals(ussername)){
            throw new UnknownAccountException("用户不存在");

        }
        if(!user.getPassword().equals(password)){
            throw new CredentialsException("密码错误");
        }
        if(user.getStatus() == 1){
            throw new DisabledAccountException("账号被禁用");
        }
        if(user.getStatus() == 2){
            throw new LockedAccountException("账号被锁定");
        }

        System.out.println("认证成功");
        // 返回为AuthenticationInfo这个类型 所以创建简单认证对象SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),super.getName());

        return info;
    }

    /**
     * 授权
     * 将将认证通过用户信息的角色和权限信息设置到对用的用户主体上
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("开始进行授权");



        //模拟从数据库中获取当前用户的角色  通过用户名查询用户的角色
        String username = principals.getPrimaryPrincipal().toString();
        Set<String> roleNameSet = new HashSet<>();
        roleNameSet.add("系统管理员");
        roleNameSet.add("系统运维");
        //模拟从数据库中获取当前用户的权限 通过用户名查询该用户的权限名称
        Set<String> permissionNameSet = new HashSet<>();
        permissionNameSet.add("sys:user:create");
        permissionNameSet.add("sys:user:list");
        permissionNameSet.add("sys:user:info");

        //简单授权信息，对象中包含用户的角色和权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addRoles(roleNameSet);
        info.addStringPermissions(permissionNameSet);
        System.out.println("授权完成");
        return info;
    }
}
