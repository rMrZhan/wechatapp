package top.jianghuling.wechatapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jianghuling.wechatapp.results.ResultMessage;
import top.jianghuling.wechatapp.service.AccountService;
import top.jianghuling.wechatapp.service.OrderService;
import top.jianghuling.wechatapp.utils.SecurityUtil;
import top.jianghuling.wechatapp.utils.Verify;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Value("${Constants.LoginState.EXPIRE}")
    private int EXPIRE;

    @RequestMapping("/sms")
    @ResponseBody
    public ResultMessage getSmsCode(String phone){
        return accountService.getMessageCode(phone);
    }

    @ResponseBody
    @RequestMapping("/login")
    public ResultMessage login(String jscode){
        return accountService.login(jscode);
    }

    @RequestMapping("/bondphone")
    @ResponseBody
    public ResultMessage bondPhone(String secretId, String phone,String vCode){
        String userId = SecurityUtil.getUserId(secretId);
        if(userId==null) return new ResultMessage(EXPIRE,"身份验证过期，请重新登录");
        return accountService.bondPhone(userId,phone,vCode);
    }


    @RequestMapping("/bondStu")
    @ResponseBody
    public ResultMessage bondStuId(String secretId,String stuId,String stuPsd){
        String userId = SecurityUtil.getUserId(secretId);
        if(userId==null) return new ResultMessage(EXPIRE,"身份验证过期，请重新登录");
        return accountService.bondPhone(userId,stuId,stuPsd);
    }




}
