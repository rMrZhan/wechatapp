package top.jianghuling.wechatapp.service;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.jianghuling.wechatapp.dao.SmsCodeMapper;
import top.jianghuling.wechatapp.dao.UserMapper;
import top.jianghuling.wechatapp.entity.SmsCode;
import top.jianghuling.wechatapp.entity.User;
import top.jianghuling.wechatapp.utils.Result;
import top.jianghuling.wechatapp.utils.Verify;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


@Service
public class AccountService {

    @Value("${Constants.SMSAppid}")
    private  int SMSAppid ;
    @Value("${Constants.SMSAppkey}")
    private  String SMSAppkey ;
    @Value("${Constants.SMSTemplateId}")
    private  int SMSTemplateId ;
    @Value("${Constants.SMSSign}")
    private  String SMSSign ;
    @Value("${Constants.CodeInvalidTime}")
    private String codeInvalidTime;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Verify verify;
    @Autowired
    private SmsCodeMapper smsCodeMapper;


    @Transactional
    public int addNewUser(String phone, String address, String sutId, String stuPassword,String smsCode){

        int resultOfVerifyPhone = verify.verifyPhone(phone,smsCode);
        if(resultOfVerifyPhone==Result.SUCCESS){
            if(verify.verifyStuId(sutId,stuPassword)==Result.SUCCESS){
                User user = new User();
                user.setAddress(address);
                user.setPhone(phone);
                user.setStuId(sutId);
                user.setStuPassword(stuPassword);
                userMapper.insert(user);
                return Result.SUCCESS;
            }else{
                return Result.WRONG_STUID;
            }
        }else{
            return resultOfVerifyPhone;
        }
    }

    /**获取验证码的同时将手机号对应的验证码和时间插入到数据库**/
    @Transactional
    public void getMessageCode(String phoneNumber){
        try {
            String verifyCode = String.valueOf((int)((Math.random()*9+1)*1000));
            String[] params = {verifyCode,codeInvalidTime};
            SmsSingleSender ssender = new SmsSingleSender(SMSAppid, SMSAppkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
                    SMSTemplateId, params, SMSSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信

            System.out.println(result);
            SmsCode smsCode = new SmsCode();
            smsCode.setPhone(phoneNumber);
            smsCode.setCode(verifyCode);
            Date date = new Date();
            Timestamp timeStamp = new Timestamp(date.getTime());
            smsCode.setCreateTime(timeStamp);
            smsCodeMapper.insert(smsCode);
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
    }

}
