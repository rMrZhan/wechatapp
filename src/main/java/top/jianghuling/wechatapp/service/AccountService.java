package top.jianghuling.wechatapp.service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.jianghuling.wechatapp.dao.RedisDao;
import top.jianghuling.wechatapp.results.ResultMessage;
import top.jianghuling.wechatapp.utils.SecurityUtil;
import top.jianghuling.wechatapp.utils.Verify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Date;


@Service
public class AccountService {

    @Value("${Constants.SMS.SMSAppid}")
    private  int SMSAppid ;
    @Value("${Constants.SMS.SMSAppkey}")
    private  String SMSAppkey ;
    @Value("${Constants.SMS.SMSTemplateId}")
    private  int SMSTemplateId ;
    @Value("${Constants.SMS.SMSSign}")
    private  String SMSSign ;
    @Value("${Constants.SMS.CodeInvalidTime}")
    private String codeInvalidTime;
    @Value("${Constants.WeChatLogin.WeChatAppId}")
    private String appID;
    @Value("${Constants.WeChatLogin.WeChatSecret}")
    private String secret;
    @Value("${Constants.WeChatLogin.Redis_3SK_ExpireTime}")
    private long redis3SKExpireTime;


    @Autowired
    private Verify verify;
    @Autowired
    private RedisDao redisDao;

//    /**获取验证码的同时将手机号对应的验证码和时间插入到数据库**/
//    @Transactional
//    public void getMessageCode(String phoneNumber){
//        try {
//            String verifyCode = String.valueOf((int)((Math.random()*9+1)*1000));
//            String[] params = {verifyCode,codeInvalidTime};
//            SmsSingleSender ssender = new SmsSingleSender(SMSAppid, SMSAppkey);
//            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
//                    SMSTemplateId, params, SMSSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
//
//            System.out.println(result);
//            SmsCode smsCode = new SmsCode();
//            smsCode.setPhone(phoneNumber);
//            smsCode.setCode(verifyCode);
//            Date date = new Date();
//            Timestamp timeStamp = new Timestamp(date.getTime());
//            smsCode.setCreateTime(timeStamp);
//            smsCodeMapper.insert(smsCode);
//        } catch (HTTPException e) {
//            // HTTP响应码错误
//            e.printStackTrace();
//        } catch (JSONException e) {
//            // json解析错误
//            e.printStackTrace();
//        } catch (IOException e) {
//            // 网络IO错误
//            e.printStackTrace();
//        }
//    }

    @Transactional
    public ResultMessage login(String jsCode){
        try{
            JSONObject jsonObject = JSON.parseObject(getOpenidAndSessionKey(jsCode));
            String openid = jsonObject.getString("openid");
            String sessionKey = jsonObject.getString("session_key");
            String thirdSessionId = SecurityUtil.md5(sessionKey);
            redisDao.set(thirdSessionId,openid,redis3SKExpireTime);//相同的key会覆写
            return new ResultMessage(ResultMessage.SUCCESS,thirdSessionId);
        }catch (Exception e){
            return new ResultMessage(ResultMessage.FAIL,"微信服务器异常");
        }

    }

    public String getOpenidAndSessionKey(String resCode) throws Exception{
        String result = "";

        BufferedReader in = null;
        try {
            String urlNameString = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appID + "&secret=" + secret + "&js_code=" + resCode + "&grant_type=authorization_code";

            URL url = new URL(urlNameString);
            URLConnection connection = url.openConnection();
            // 设置通用的请求属性

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return result;
    }


}
