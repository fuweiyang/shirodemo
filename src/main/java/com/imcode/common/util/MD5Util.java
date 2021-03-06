package com.imcode.common.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Util {
    private static int hashIterations =3;
    private static String salt = "ak47";

    /**
     * Md5 加密工具
     * @param source
     * @param salt
     * @return
     */
    public static String md5(String source,String salt){
        return new Md5Hash(source,salt,hashIterations).toString();

    }
    public static String md5_salt(String source){
        return new Md5Hash(source,salt,hashIterations).toString();

    }
}
