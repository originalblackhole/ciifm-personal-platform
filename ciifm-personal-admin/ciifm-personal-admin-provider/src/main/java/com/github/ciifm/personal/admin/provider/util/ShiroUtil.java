package com.github.ciifm.personal.admin.provider.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/22 0022 19:28
 */
public class ShiroUtil {

    /*public static void main(String[] args){
        String password = ShiroUtil.encrypt("123456","MD5","admin",2);
        System.out.println(password);
    }*/

    /**
     *
     * @param password 密码明文
     * @param hashAlgorithName 哈希算法名称
     * @param salt 加密盐
     * @param hashIterations 迭代数次
     * @return
     */
    public static String encrypt(String password,String hashAlgorithName,String salt,int hashIterations){
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        Object obj = new SimpleHash(hashAlgorithName, password,
                credentialsSalt, hashIterations);
        return obj.toString();
    }

}
