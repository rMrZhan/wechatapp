package top.jianghuling.wechatapp.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtil {
    public static String md5(String text) throws Exception {
        //加密后的字符串
        String encodeStr= DigestUtils.md5Hex(text);
        return encodeStr;
    }
}
