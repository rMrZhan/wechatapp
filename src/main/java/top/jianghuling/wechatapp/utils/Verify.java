package top.jianghuling.wechatapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.jianghuling.wechatapp.dao.SmsCodeMapper;
import top.jianghuling.wechatapp.entity.SmsCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class Verify {

    @Value("${Constants.AcademicOfficeUrl}")
    private  String AcademicOfficeUrl;
    @Autowired
    private SmsCodeMapper smsCodeMapper;
    @Value("${Constants.CodeInvalidTime}")
    private long codeInvalidTime;
    @Value("${Constants.WeChatAppId}")
    private String appID;
    @Value("${Constants.WeChatSecret}")
    private String secret;

    public int verifyStuId(String stuId, String password){
        String param = "j_username="+stuId+"&j_password="+password;
        PrintWriter out = null;
        BufferedReader in = null;
        String result=null;

        try{
            URL url = new URL(AcademicOfficeUrl);
            try{
                String line;
                int countLine=10;
                URLConnection connection = url.openConnection();
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("Accept-Charset", "UTF-8");
                connection.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                out = new PrintWriter(connection.getOutputStream());
                //发送请求参数
                out.print(param);
                out.flush();
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream(),"gb2312"));
                //读取获取到的网页的前10行
                while ((line = in.readLine()) != null && countLine>0) {
                    countLine--;
                    result += line;
                }
                if(result.contains("URP综合教务系统首页"))
                    return Result.SUCCESS;
                else return Result.WRONG_STUID;
            }catch (IOException ioe){
                ioe.printStackTrace();
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        }finally {
            try{
                if(out!=null)
                    out.close();
                if(in!=null)
                    in.close();
            }catch (IOException i){
                i.printStackTrace();
            }

        }
        return Result.SERVER_BUSY;

    }
    public int verifyPhone(String phone,String verifyCode){
        SmsCode smsCode = smsCodeMapper.selectByPrimaryKey(phone);
        if(smsCode ==null)//没获取验证码
            return Result.NO_SMS_CODE;
        else{
            Date createTime = smsCode.getCreateTime();
            if((System.currentTimeMillis()-createTime.getTime())<codeInvalidTime*60*1000){
                if(verifyCode.equals(smsCode.getCode()))
                    return Result.SUCCESS;
                else return Result.WRONG_SMS_CODE;//验证码输入错误
            }else return Result.SMS_CODE_EXPIRY;//验证码过期失效

        }
    }
    public String getOpenID(String resCode) {
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
        } catch (Exception e) {
            System.out.println("获取openid请求异常");
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                System.out.println("关闭in异常");
                e.printStackTrace();
            }
        }
        return result;
    }


}
