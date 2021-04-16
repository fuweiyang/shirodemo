package com.imcode.common.util;

import com.imcode.sys.shiro.ShirioRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {

    /**
     * 初始话shiro的运行环境
     */
    static {
        //1.初始化shiro的安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //2.设置用户的权限信息到安全管理器
        //Realm realm = new IniRealm("classpath:shiro.ini");
        Realm realm = new ShirioRealm();
        securityManager.setRealm(realm);


        //3.使用SecruityUtils将secruityManager设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);
    }

    public static Subject login(String username, String password){
       //1.创建Subject实例
        Subject subject = SecurityUtils.getSubject();
        //2.创建用于认证的token，记录用户认证的身份信息和凭证 即账号和秘密
        AuthenticationToken token =new UsernamePasswordToken(username,password);

        subject.login(token);
        System.out.println("用户认证状态:"+subject.isAuthenticated());
        return subject;

    }



}
