package cn.nick.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class CryptoUtil {

    //需要传入两个参数，第一个是密码，第二个是盐
    public static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString();
    }


}
