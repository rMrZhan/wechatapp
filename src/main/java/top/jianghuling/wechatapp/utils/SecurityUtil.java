package top.jianghuling.wechatapp.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.jianghuling.wechatapp.dao.RedisDao;


public class SecurityUtil {

    @Autowired
    private static RedisDao redisDao;

    public static String md5(String text) throws Exception {
        //加密后的字符串
        String encodeStr= DigestUtils.md5Hex(text);
        return encodeStr;
    }


    public static String getUserId(String secret){
        if(redisDao.get(secret)!=null) return redisDao.get(secret).toString();
        else return null;
    }
}
