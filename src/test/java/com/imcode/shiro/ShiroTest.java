package com.imcode.shiro;

import com.imcode.common.util.MD5Util;
import com.imcode.common.util.ShiroUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class ShiroTest {
    @Test
    public void test(){
        //1.初始化shiro的安全管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    }

    @Test
    public void test02(){
        //登录
        Subject subject = ShiroUtil.login("admin", "123456");

        //授权资源的检查

        //模拟当前用户点击了《新增用户按钮》 检查该用户是否拥有权限
        System.out.println("检查该用户是否拥有新增用户权限："+subject.isPermitted("sys:user:create"));
        System.out.println("检查该用户是否拥有新增角色权限："+subject.isPermitted("sys:role:create"));



        System.out.println("检查该用户是否是系统管理员角色："+subject.hasRole("系统管理员"));


        //退出系统
        subject.logout();
    }

    /**
     * 加密
     */
    @Test
    public void test03(){

     /*   Md5Hash md5Hash = new Md5Hash("11111");
        System.out.println(md5Hash);
        Md5Hash md5Hash_salt = new Md5Hash("11111","ak47");
        System.out.println(md5Hash_salt);
        //Md5Hash(Md5Hash("11111"))
        Md5Hash md5Hash_salt_num = new Md5Hash("11111","ak47",2);
        System.out.println(md5Hash_salt_num);*/

        //SimpleHash




        String username ="admin";
        String password = "123456";
        String email = "admin@126.com";
        String mobile = "13801012233";
        String str = username+password;
        //生成盐值

        String salt = MD5Util.md5(str,"ak47");
        System.out.println(salt);
        password = MD5Util.md5(password,salt);
        System.out.println(password);
        }


    @Test

    public void test04(){

        //模拟从客户端接收到的用户名和密码（明文）
        String username = "admin";
        String password = "123456";
        String salt = MD5Util.md5_salt(username+password);
        password = MD5Util.md5(password,salt);
        //登录认证
        ShiroUtil.login(username,password);
    }

}
