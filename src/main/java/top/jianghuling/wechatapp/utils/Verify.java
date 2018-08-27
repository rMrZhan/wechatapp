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


    public boolean verifyStuId(String stuId, String password){
        String param = "zjh="+stuId+"&mm="+password;
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
                if(result.contains("学分制综合教务"))
                    return true;
                else return false;
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
        return false;

    }
    public boolean verifyPhone(String phone,String verifyCode){
        SmsCode smsCode = smsCodeMapper.selectByPrimaryKey(phone);
        if(smsCode ==null)//没获取验证码
            return false;
        else{
            Date createTime = smsCode.getCreateTime();
            if((System.currentTimeMillis()-createTime.getTime())<codeInvalidTime*60*1000){
                if(verifyCode.equals(smsCode.getCode()))
                    return true;
                else return false;//验证码输入错误
            }else return false;//验证码过期失效

        }
    }


}
