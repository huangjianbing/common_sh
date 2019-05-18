package com.dba.utils;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

/**1.对用户密码进行加盐后MD5加密处理
 * Author:huangjb
 * Date:2018/7/25
 * Description:
 */
public class HashUtils {

    private static final HashFunction FUNCTION= Hashing.md5();

    private  static  final String SALT="com.dba";

    public  static String encryPassword(String password){
        HashCode hashCode=FUNCTION.hashString(password+SALT, Charset.forName("UTF-8"));
        return hashCode.toString();
    }
}
