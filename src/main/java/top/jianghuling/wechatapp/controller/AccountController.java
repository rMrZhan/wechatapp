package top.jianghuling.wechatapp.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jianghuling.wechatapp.results.ResultMessage;
import top.jianghuling.wechatapp.service.AccountService;
import top.jianghuling.wechatapp.service.OrderService;
import top.jianghuling.wechatapp.utils.SecurityUtil;
import top.jianghuling.wechatapp.utils.Verify;

@Slf4j
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Value("${Constants.LoginState.EXPIRE}")
    private int EXPIRE;

    @Autowired
    private ResultMessage resultMessage;

    @ResponseBody
    @RequestMapping("/sms")
    public ResultMessage getSmsCode(String phone){
        log.info("logback 的测试，手机号为"+phone);
        return accountService.getMessageCode(phone);
    }

    @ResponseBody
    @RequestMapping("/login")
    public ResultMessage login(String jscode){

        return accountService.login(jscode);
    }

    @RequestMapping(value = "/bondphone")
    @ResponseBody
    public ResultMessage bondPhone(String secretId, String phone,String vCode){
        String userId = SecurityUtil.getUserId(secretId);
        if(userId==null) return resultMessage.setInfo(EXPIRE,"身份验证过期，请重新登录");
        return accountService.bondPhone(userId,phone,vCode);
    }


    @RequestMapping("/bondStu")
    @ResponseBody
    public ResultMessage bondStuId(String secretId,String stuId,String stuPsd){
        String userId = SecurityUtil.getUserId(secretId);
        if(userId==null) return resultMessage.setInfo(EXPIRE,"身份验证过期，请重新登录");
        return accountService.bondStuId(userId,stuId,stuPsd);
    }




}
