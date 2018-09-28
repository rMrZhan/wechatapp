package top.jianghuling.wechatapp.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jianghuling.wechatapp.results.ResultMessage;
import top.jianghuling.wechatapp.service.AccountService;
import top.jianghuling.wechatapp.utils.SecurityUtil;

@Slf4j
@Controller
@RequestMapping("/account")
public class AccountController {


    @Value("${Constants.Operate.SUCCESS}")
    private Byte OPERATE_SUCCESS;
    @Value("${Constants.Operate.FAIL}")
    private Byte OPERATE_FAIL;

    @Autowired
    private AccountService accountService;

    @Value("${Constants.LoginState.EXPIRE}")
    private int EXPIRE;

    @Autowired
    private ResultMessage resultMessage;

    @Autowired
    private SecurityUtil securityUtil;

    @ResponseBody
    @RequestMapping("/sms")
    public ResultMessage getSmsCode(String phone){
        return accountService.getMessageCode(phone);
    }

    @ResponseBody
    @RequestMapping("/login")
    public ResultMessage login(String jscode){

        return accountService.login(jscode);
    }


    /**
     * attention: 在controller 用了redisDao，再在对应的service里用redisDao的话会报错
     * ，除非给controller加上@Transactional，或者给SecurityUtil里用到redisDao的方法加上@Transactional
     */
    @ResponseBody
    @RequestMapping( "/bondPhone")
    public ResultMessage bondPhone(String secretId, String phone,String vCode){

             String userId = securityUtil.getUserId(secretId).replace("\"","");
            if(userId==null) return resultMessage.setInfo(EXPIRE,"身份验证过期，请重新登录");

        //以上代码是为了从secretId转换成userId
        try{
            return accountService.bondPhone(userId,phone,vCode);
        }catch (Exception e){
            return resultMessage.setInfo(OPERATE_FAIL,"手机号或验证码错误");
        }

    }


    @RequestMapping("/bondStu")
    @ResponseBody
    public ResultMessage bondStuId(String secretId,String stuId,String stuPsd){
        String userId = securityUtil.getUserId(secretId);
        if(userId==null)
            return resultMessage.setInfo(EXPIRE,"身份验证过期，请重新登录");
        return accountService.bondStuId(userId,stuId,stuPsd);
    }

}
