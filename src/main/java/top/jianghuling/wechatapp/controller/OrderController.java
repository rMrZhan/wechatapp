package top.jianghuling.wechatapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jianghuling.wechatapp.entity.Order;
import top.jianghuling.wechatapp.entity.OrderLinkMission;
import top.jianghuling.wechatapp.results.BriefOrder;
import top.jianghuling.wechatapp.results.ResultMessage;
import top.jianghuling.wechatapp.service.OrderService;
import top.jianghuling.wechatapp.utils.MyTimeUtil;
import top.jianghuling.wechatapp.utils.SecurityUtil;

import java.text.ParseException;
import java.util.List;


@RequestMapping("/order")
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Value("${Constants.Operate.FAIL}")
    private int OPERATE_FAIL;
    @Value("${Constants.LoginState.EXPIRE}")
    private int EXPIRE;



    @ResponseBody
    @RequestMapping("/abandon")
    public ResultMessage abandonMission(String orderId){

        return new ResultMessage(orderService.abandonMission(orderId));
    }

    @RequestMapping("/lookmission")
    @ResponseBody
    public List<Order> browseMyMissionRecords(String secretId, int pageNum, int pageSize){
        String takerId = SecurityUtil.getUserId(secretId);
        if(takerId==null) return null;
        List <Order> orders = orderService.browseMyMissionRecords(takerId,pageNum,pageSize);
        for(Order o: orders){
            o.setReleaserId("");
        }
        return orders;
    }

    @RequestMapping("/lookmo")
    @ResponseBody
    public List<OrderLinkMission> browseMyResleaseOrder(String secretId, int pageNum, int pageSize){

        String takerId = SecurityUtil.getUserId(secretId);
        if(takerId==null) return null;

        return browseMyResleaseOrder(takerId,pageNum,pageSize);
    }

    @RequestMapping("/lookissue")
    @ResponseBody
    public List<BriefOrder> browseReleaseOrders(int pageNum, int pageSize){
        return  orderService.browseReleaseOrders(pageNum,pageSize);
    }


    @RequestMapping("/cancel")
    @ResponseBody
    public ResultMessage cancelOrderByHost(String secretId){
        String hostId = SecurityUtil.getUserId(secretId);
        if(hostId==null) return new ResultMessage(EXPIRE,"身份验证过期，请重新登录");
        return new ResultMessage(orderService.cancelOrderByHost(hostId));

    }

    @RequestMapping("/confirm")
    @ResponseBody
    public ResultMessage confirmFinishMission(String  orderId){
        return new ResultMessage(orderService.confirmFinishMission(orderId));
    }


    @RequestMapping("/mfail")
    @ResponseBody
    public ResultMessage missionFail(String orderId){
        return new ResultMessage(orderService.missionFail(orderId));
    }

    @RequestMapping("/msuccess")
    @ResponseBody
    public ResultMessage missionSuccess(String orderId){
        return new ResultMessage(orderService.missionSuccess(orderId));
    }

    @RequestMapping("/precancel")
    @ResponseBody
    public ResultMessage preCancelOrderByHost(String orderId){
        return new ResultMessage(orderService.preCancelOrderByHost(orderId));
    }

    @RequestMapping("/issue")
    @ResponseBody
    public ResultMessage releaseNewOrder(String secretId, String goodsCode, String note, String reward, String hostName, String hostPhone,
                                         String takeAddress, String destination, String goodsWeight,
                                         String starttime, String deadline, String expressType ){
        try{
            String releaserId = SecurityUtil.getUserId(secretId);
            if(releaserId==null) return new ResultMessage(EXPIRE,"身份验证过期，请重新登录");
            return new ResultMessage(orderService.releaseNewOrder( releaserId,  goodsCode,  note,  Float.parseFloat(reward),  hostName,  hostPhone,
                    takeAddress,  destination,  goodsWeight,
                    MyTimeUtil.parseTime(starttime),  MyTimeUtil.parseTime(deadline),  expressType));
        }catch (ParseException e){
            return new ResultMessage(OPERATE_FAIL,"日期格式错误"+e.getStackTrace());
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMessage(OPERATE_FAIL,e.getMessage());
        }

    }


    @RequestMapping("/take")
    @ResponseBody
    public ResultMessage takeMission(String secretId,String orderId){
        String takerId = SecurityUtil.getUserId(secretId);
        if(takerId==null) return new ResultMessage(EXPIRE,"身份验证过期，请重新登录");
        return new ResultMessage(orderService.takeMission(takerId,orderId));
    }


}
