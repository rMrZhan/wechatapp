package top.jianghuling.wechatapp.service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.apache.catalina.User;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.jianghuling.wechatapp.dao.RedisDao;
import top.jianghuling.wechatapp.dao.UserInfoMapper;
import top.jianghuling.wechatapp.model.UserInfo;
import top.jianghuling.wechatapp.results.LoginResultMessage;
import top.jianghuling.wechatapp.results.ResultMessage;
import top.jianghuling.wechatapp.utils.SecurityUtil;
import top.jianghuling.wechatapp.utils.Verify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


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
    @Value("${Constants.Operate.SUCCESS}")
    private Byte OPERATE_SUCCESS;
    @Value("${Constants.Operate.FAIL}")
    private Byte OPERATE_FAIL;
    @Value("${Constants.LoginState.FULL}")
    private int FULL;
    @Value("${Constants.LoginState.LACK_PHONE}")
    private int LACK_PHONE;
    @Value("${Constants.LoginState.LACK_STUID}")
    private int LACK_STUID;
    @Value("${Constants.LoginState.LACK_BOTH}")
    private int LACK_BOTH;

    @Autowired
    private Verify verify;
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private LoginResultMessage loginResultMessage;

    @Transactional
    public ResultMessage bondPhone(String userId,String phone,String vCode)throws Exception{

            UserInfo user = userInfoMapper.selectByPrimaryKey(userId);
            if(redisDao.get(phone).toString().equals(vCode)){
                user.setPhone(phone);
                userInfoMapper.updateByPrimaryKey(user);
                return resultMessage.setInfo(OPERATE_SUCCESS,"成功绑定手机");
            }else
                return resultMessage.setInfo(OPERATE_FAIL,"验证码或手机号错误");


    }



    /**获取验证码的同时将手机号对应的验证码和时间插入到redis**/
    @Transactional
    public ResultMessage getMessageCode(String phoneNumber){
        try {
            String verifyCode = String.valueOf((int)((Math.random()*9+1)*1000));
            String[] params = {verifyCode,codeInvalidTime};
            SmsSingleSender ssender = new SmsSingleSender(SMSAppid, SMSAppkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
                    SMSTemplateId, params, SMSSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信

            if (result.result==0){
                redisDao.set(phoneNumber,verifyCode,300);
                return  resultMessage.setInfo(OPERATE_SUCCESS,"发送验证码成功");
            }else{
                return  resultMessage.setInfo(OPERATE_FAIL,"发送验证码失败");
            }

        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return resultMessage.setInfo(OPERATE_FAIL,"异常错误");
    }

    /**
     * 用户打开小程序下后执行login获取唯一id以及手机号，学生账号
     * 下单前，检查账户信息是否完善，检查缓存中的账户状态，如果缺某一项则继续执行login
     * 如果没有手机号或者学生号，提醒用户绑定。
     */

    @Transactional
    public ResultMessage login(String jsCode){
        String result="";
        try{
            result = getOpenidAndSessionKey(jsCode);
            JSONObject jsonObject = JSON.parseObject(result);
            String openid = jsonObject.getString("openid");
            String sessionKey = jsonObject.getString("session_key");
            String thirdSessionId = SecurityUtil.md5(sessionKey);
            redisDao.set(thirdSessionId,openid,redis3SKExpireTime);//相同的key会覆写
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(openid);
            if(userInfo==null){ //新用户第一次登录
                userInfo = new UserInfo();
                userInfo.setUserId(openid);
                userInfoMapper.insert(userInfo);
                return loginResultMessage.setInfo(LACK_BOTH,thirdSessionId,"","");
            }else{
                if(userInfo.getPhone()==null&&userInfo.getStuId()==null){
                    return loginResultMessage.setInfo(LACK_BOTH,thirdSessionId,"","");
                }else if(userInfo.getPhone()==null){
                    return loginResultMessage.setInfo(LACK_PHONE,thirdSessionId,"",userInfo.getStuId());
                }else if(userInfo.getStuId()==null){
                    return loginResultMessage.setInfo(LACK_STUID,thirdSessionId,userInfo.getPhone(),"");
                }else{
                    return loginResultMessage.setInfo(FULL,thirdSessionId,userInfo.getPhone(),userInfo.getStuId());
                }
            }

        }catch (Exception e){
            e.printStackTrace();

            return loginResultMessage.setInfo(OPERATE_FAIL,"微信服务器异常,请重新登录","","");
        }

    }
    @Transactional
    public ResultMessage bondStuId(String userId,String stuId, String stuPsd){
        UserInfo user = userInfoMapper.selectByPrimaryKey(userId);
        if(verify.verifyStuId(stuId,stuPsd)) {
            user.setStuId(stuId);
            user.setStuPassword(stuPsd);
            userInfoMapper.updateByPrimaryKey(user);
            return resultMessage.setInfo(OPERATE_SUCCESS,"成功绑定学号");
        }

        else return resultMessage.setInfo(OPERATE_FAIL,"绑定学号失败");
    }

    @Transactional
    public ResultMessage bondGender(String userId, byte gender){
        try{
            UserInfo user = new UserInfo();
            user.setUserId(userId);
            user.setGender(gender);
            userInfoMapper.updateByPrimaryKeySelective(user);
            return resultMessage.setInfo(OPERATE_SUCCESS);
        }catch (Exception e){
            return resultMessage.setInfo(OPERATE_FAIL);
        }
    }

    @Transactional
    public ResultMessage getGender(String userId){
        try{
            return resultMessage.setInfo(OPERATE_SUCCESS,userInfoMapper.selectByPrimaryKey(userId).getGender().toString());
        }
        catch (Exception e){
            return resultMessage.setInfo(OPERATE_FAIL,"1");//失败默认返回"男"
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
