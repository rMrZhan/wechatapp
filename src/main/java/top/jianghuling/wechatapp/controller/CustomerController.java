package top.jianghuling.wechatapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jianghuling.wechatapp.results.ResultMessage;
import top.jianghuling.wechatapp.service.CustomerService;

@Controller
@RequestMapping("/cst")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @RequestMapping("/comment")
    public ResultMessage submitComment(String phone,String comment,String score){
        return customerService.addComment(phone,comment,Integer.valueOf(score));
    }
}
