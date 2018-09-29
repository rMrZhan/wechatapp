package top.jianghuling.wechatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jianghuling.wechatapp.results.LoginResultMessage;
import top.jianghuling.wechatapp.results.ResultMessage;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/welcome")
    @ResponseBody
    public String Welcome() {
        return "hello world";
    }

    @RequestMapping("/testfilter")
    @ResponseBody
    public ResultMessage testFilter(String secretId,String userId){

        System.out.println(userId);
        return new ResultMessage().setInfo(0,userId);
    }


}
