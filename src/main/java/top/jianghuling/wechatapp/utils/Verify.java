package top.jianghuling.wechatapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.jianghuling.wechatapp.dao.RedisDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Component
public class Verify {

    @Value("${Constants.AcademicOfficeUrl}")
    private  String AcademicOfficeUrl;
    @Autowired
    private SmsCodeMapper smsCodeMapper;
    @Value("${Constants.CodeInvalidTime}")
    private long codeInvalidTime;


    @Autowired
    private RedisDao redisDao;

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
                    return ResultCode.SUCCESS;
                else return ResultCode.WRONG_STUID;
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
        return ResultCode.SERVER_BUSY;

    }

//    public int verifyPhone(String phone,String verifyCode){
//
//
//
//    }



}
