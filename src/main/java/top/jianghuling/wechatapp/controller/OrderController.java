package top.jianghuling.wechatapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jianghuling.wechatapp.entity.Order;
import top.jianghuling.wechatapp.entity.OrderLinkMission;
import top.jianghuling.wechatapp.results.BriefOrder;
import top.jianghuling.wechatapp.results.ResultMessage;
import top.jianghuling.wechatapp.service.OrderService;
import top.jianghuling.wechatapp.utils.SecurityUtil;

import java.util.Date;
import java.util.List;


@RequestMapping("/order")
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Value("${Constants.LoginState.EXPIRE}")
    private int EXPIRE;



    @ResponseBody
    @RequestMapping(value="/abandon")
    public ResultMessage abandonMission(String orderId){

        return new ResultMessage(orderService.abandonMission(orderId));
    }

    @RequestMapping(value="lookmission")
    @ResponseBody
    public List<Order> browseMyMissionRecords(String secretId, int pageNum, int pageSize){
        String takerId = SecurityUtil.getUserId(secretId);
        if(takerId==null) return null;
        return orderService.browseMyMissionRecords(takerId,pageNum,pageSize);
    }

    @RequestMapping(value="lookmo")
    @ResponseBody
    public List<OrderLinkMission> browseMyResleaseOrder(String secretId, int pageNum, int pageSize){

        String takerId = SecurityUtil.getUserId(secretId);
        if(takerId==null) return null;

        return browseMyResleaseOrder(takerId,pageNum,pageSize);
    }

    @RequestMapping(value="lookissue")
    @ResponseBody
    public List<BriefOrder> browseReleaseOrders(int pageNum, int pageSize){
        return  orderService.browseReleaseOrders(pageNum,pageSize);
    }


    @RequestMapping(value="cancel")
    @ResponseBody
    public ResultMessage cancelOrderByHost(String secretId){
        String hostId = SecurityUtil.getUserId(secretId);
        if(hostId==null) return new ResultMessage(EXPIRE,"身份验证过期，请重新登录");
        return new ResultMessage(orderService.cancelOrderByHost(hostId));

    }

    @RequestMapping(value="confirm")
    @ResponseBody
    public ResultMessage confirmFinishMission(String  orderId){
        return new ResultMessage(orderService.confirmFinishMission(orderId));
    }


    @RequestMapping(value="mfail")
    @ResponseBody
    public ResultMessage missionFail(String orderId){
        return new ResultMessage(orderService.missionFail(orderId));
    }

    @RequestMapping(value="msuccess")
    @ResponseBody
    public ResultMessage missionSuccess(String orderId){
        return new ResultMessage(orderService.missionSuccess(orderId));
    }

    @RequestMapping(value="precancel")
    @ResponseBody
    public ResultMessage preCancelOrderByHost(String orderId){
        return new ResultMessage(orderService.preCancelOrderByHost(orderId));
    }

    @RequestMapping(value="issue")
    @ResponseBody
    public ResultMessage releaseNewOrder(String secretId, String goodsCode, String note, float reward, String hostName, String hostPhone,
                                         String takeAddress, String destination, String goodsWeight,
                                         Date starttime, Date deadline, String expressType ){
        String releaserId = SecurityUtil.getUserId(secretId);
        if(releaserId==null) return new ResultMessage(EXPIRE,"身份验证过期，请重新登录");
        return new ResultMessage(orderService.releaseNewOrder( releaserId,  goodsCode,  note,  reward,  hostName,  hostPhone,
                 takeAddress,  destination,  goodsWeight,
                 starttime,  deadline,  expressType));
    }


    @RequestMapping(value="take")
    @ResponseBody
    public ResultMessage takeMission(String secretId,String orderId){
        String takerId = SecurityUtil.getUserId(secretId);
        if(takerId==null) return new ResultMessage(EXPIRE,"身份验证过期，请重新登录");
        return new ResultMessage(orderService.takeMission(takerId,orderId));
    }


}
