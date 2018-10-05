package top.jianghuling.wechatapp.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import top.jianghuling.wechatapp.dao.RedisDao;

/**
 * @aurhor Jason
 */
@Repository
public class SecurityUtil {


    private static RedisDao redisDao;

    @Autowired
    public void setRedisDao(RedisDao redisDao){
        this.redisDao =redisDao;
    }


    public static String md5(String text) throws Exception {
        //加密后的字符串
        return DigestUtils.md5Hex(text);

    }

    @Transactional   //这里一定要加Transactional
    public  String getUserId(String secret){
        try{
            return redisDao.get(secret).toString();
        }catch (Exception e){
            return null;
        }


    }
}
