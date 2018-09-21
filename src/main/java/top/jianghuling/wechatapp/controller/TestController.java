package top.jianghuling.wechatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/welcome")
    @ResponseBody
    public String Welcome() {
        return "hello world";
    }
}
