package top.jianghuling.wechatapp.utils;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.jianghuling.wechatapp.dao.RedisDao;
import top.jianghuling.wechatapp.results.ResultMessage;
@Component
public class Verify {


    @Value("${Constants.SMS.CodeInvalidTime}")
    private long codeInvalidTime;


//    @Autowired
//    private RedisDao redisDao;

    public boolean verifyStuId(String stuId, String password){
        // 登陆 Url
        String loginUrl = "http://zhjw.scu.edu.cn/j_spring_security_check";
        // 需登陆后访问的 Url
        String dataUrl = "http://zhjw.scu.edu.cn/index.jsp";
        HttpClient httpClient = new HttpClient();

        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        PostMethod postMethod = new PostMethod(loginUrl);

        // 设置登陆时要求的信息，用户名和密码
        NameValuePair[] data = { new NameValuePair("j_username", stuId), new NameValuePair("j_password", password) ,new NameValuePair("j_captcha1", "error")};
        postMethod.setRequestBody(data);
        try {
            // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            int statusCode=httpClient.executeMethod(postMethod);

            // 获得登陆后的 Cookie
            Cookie[] cookies = httpClient.getState().getCookies();
            StringBuffer tmpcookies = new StringBuffer();
            for (Cookie c : cookies) {
                tmpcookies.append(c.toString() + ";");
                System.out.println("cookies = "+c.toString());
            }
            if(statusCode==302){//重定向到新的URL
                // 进行登陆后的操作
                GetMethod getMethod = new GetMethod(dataUrl);
                // 每次访问需授权的网址时需带上前面的 cookie 作为通行证
                getMethod.setRequestHeader("cookie", tmpcookies.toString());
                // 你还可以通过 PostMethod/GetMethod 设置更多的请求后数据
                postMethod.setRequestHeader("Referer", "http://jwc.scu.edu.cn/");
                postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
                httpClient.executeMethod(getMethod);
                // 打印出返回数据，检验一下是否成功
                String text = getMethod.getResponseBodyAsString();
                if(text.contains("URP综合教务系统首页"))
                    return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


//    @Transactional
//    public boolean verifyPhone(String phone,String vCode){
//        if(redisDao==null)
//            System.out.println("空的 ");
//        if (redisDao.get(phone)==null)
//            System.out.println("手机号空");
//        if(redisDao.get("314fea5e924e8b3bfbc8b82078357686")==null)
//            System.out.println("ye俄式空");
//        else System.out.println(redisDao.get("314fea5e924e8b3bfbc8b82078357686"));
//        return redisDao.get(phone).toString().equals(vCode);
//    }

}

