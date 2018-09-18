package top.jianghuling.wechatapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jianghuling.wechatapp.entity.Order;
import top.jianghuling.wechatapp.entity.OrderLinkMission;
import top.jianghuling.wechatapp.results.BriefOrder;
import top.jianghuling.wechatapp.results.ResultMessage;
import top.jianghuling.wechatapp.service.OrderService;

import java.util.Date;
import java.util.List;


@RequestMapping("/order")
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("abandon")
    @ResponseBody
    public ResultMessage abandonMission(String orderId){
        return new ResultMessage(orderService.abandonMission(orderId));
    }

    @RequestMapping("lookmission")
    @ResponseBody
    public List<Order> browseMyMissionRecords(String takerId, int pageNum, int pageSize){
        return orderService.browseMyMissionRecords(takerId,pageNum,pageSize);
    }

    @RequestMapping("lookmy")
    @ResponseBody
    public List<OrderLinkMission> browseMyResleaseOrder(String userId, int pageNum, int pageSize){
        return browseMyResleaseOrder(userId,pageNum,pageSize);
    }

    @RequestMapping("lookorder")
    @ResponseBody
    public List<BriefOrder> browseReleaseOrders(int pageNum, int pageSize){
        return  orderService.browseReleaseOrders(pageNum,pageSize);
    }


    @RequestMapping("cancel")
    @ResponseBody
    public ResultMessage cancelOrderByHost(String hostId){
        return new ResultMessage(orderService.cancelOrderByHost(hostId));

    }

    @RequestMapping("abandon")
    @ResponseBody
    public ResultMessage confirmFinishMission(String  orderId){
        return new ResultMessage(orderService.confirmFinishMission(orderId));
    }


    @RequestMapping("fail")
    @ResponseBody
    public ResultMessage missionFail(String orderId){
        return new ResultMessage(orderService.missionFail(orderId));
    }

    @RequestMapping("success")
    @ResponseBody
    public ResultMessage missionSuccess(String orderId){
        return new ResultMessage(orderService.missionSuccess(orderId));
    }

    @RequestMapping("precancel")
    @ResponseBody
    public ResultMessage preCancelOrderByHost(String orderId){
        return new ResultMessage(orderService.preCancelOrderByHost(orderId));
    }

    @RequestMapping("issue")
    @ResponseBody
    public ResultMessage releaseNewOrder(String releaserId, String goodsCode, String note, float reward, String hostName, String hostPhone,
                                         String takeAddress, String destination, String goodsWeight,
                                         Date starttime, Date deadline, String expressType ){
        return new ResultMessage(orderService.releaseNewOrder( releaserId,  goodsCode,  note,  reward,  hostName,  hostPhone,
                 takeAddress,  destination,  goodsWeight,
                 starttime,  deadline,  expressType));
    }


    @RequestMapping("take")
    @ResponseBody
    public ResultMessage takeMission(String takerId,String orderId){
        return new ResultMessage(orderService.takeMission(takerId,orderId));
    }




}
