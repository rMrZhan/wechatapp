package top.jianghuling.wechatapp.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jianghuling.wechatapp.model.Order;
import top.jianghuling.wechatapp.model.OrderLinkMission;
import top.jianghuling.wechatapp.model.BriefOrder;
import top.jianghuling.wechatapp.results.ResultMessage;
import top.jianghuling.wechatapp.service.OrderService;
import top.jianghuling.wechatapp.utils.MyTimeUtil;
import top.jianghuling.wechatapp.utils.SecurityUtil;

import java.text.ParseException;
import java.util.List;

@Slf4j
@RequestMapping("/order")
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Value("${Constants.Operate.FAIL}")
    private int OPERATE_FAIL;
    @Value("${Constants.LoginState.EXPIRE}")
    private int EXPIRE;

    @Autowired
    public ResultMessage resultMessage;
    @Autowired
    private SecurityUtil securityUtil;



    @ResponseBody
    @RequestMapping("/abandon")
    public ResultMessage abandonMission(String orderId){

        return resultMessage.setInfo(orderService.abandonMission(orderId));
    }

    @RequestMapping("/lookmission")
    @ResponseBody
    public List<OrderLinkMission> browseMyMissionRecords(String secretId, int pageNum, int pageSize){
        String takerId = securityUtil.getUserId(secretId);
        if(takerId==null)
            return null;
        List <OrderLinkMission> orders = orderService.getMyAccept(takerId,pageNum,pageSize);
        return orders;
    }

    @RequestMapping("/lookmo")
    @ResponseBody
    public List<OrderLinkMission> browseMyResleaseOrder(String secretId, int pageNum, int pageSize){

        String userId = securityUtil.getUserId(secretId);
        if(userId==null)
            return null;

        return orderService.getMyRelease(userId,pageNum,pageSize);
    }

    @RequestMapping("/lookissue")
    @ResponseBody
    public List<BriefOrder> browseReleaseOrders(String secretId, int pageNum, int pageSize){
        String userId = securityUtil.getUserId(secretId);
        if(userId==null) return null;
        return  orderService.browseReleaseOrders(secretId,pageNum,pageSize);
    }


    @RequestMapping("/cancel")
    @ResponseBody
    public ResultMessage cancelOrderByHost(String secretId){
        String hostId = securityUtil.getUserId(secretId);
        if(hostId==null) return resultMessage.setInfo(EXPIRE,"身份验证过期，请重新登录");
        return resultMessage.setInfo(orderService.cancelOrderByHost(hostId));

    }

    @RequestMapping("/confirm")
    @ResponseBody
    public ResultMessage confirmFinishMission(String  orderId){
        return  resultMessage.setInfo(orderService.confirmFinishMission(orderId));
    }


    @RequestMapping("/mfail")
    @ResponseBody
    public ResultMessage missionFail(String orderId){
        return resultMessage.setInfo(orderService.missionFail(orderId));
    }

    @RequestMapping("/msuccess")
    @ResponseBody
    public ResultMessage missionSuccess(String orderId){
        return resultMessage.setInfo(orderService.missionSuccess(orderId));
    }

    @RequestMapping("/precancel")
    @ResponseBody
    public ResultMessage preCancelOrderByHost(String orderId){
        return resultMessage.setInfo(orderService.preCancelOrderByHost(orderId));
    }

    @RequestMapping("/issue")
    @ResponseBody
    public ResultMessage releaseNewOrder(String secretId, String goodsCode, String note, String reward, String hostName, String hostPhone,
                                         String takeAddress, String destination, String goodsWeight,
                                         String starttime, String deadline, String expressType ){
        try{
            String releaserId = securityUtil.getUserId(secretId);
            if(releaserId==null)
                return resultMessage.setInfo(EXPIRE,"身份验证过期，请重新登录");
            return resultMessage.setInfo(orderService.releaseNewOrder( releaserId,  goodsCode,  note,  Float.parseFloat(reward),  hostName,  hostPhone,
                    takeAddress,  destination,  goodsWeight,
                    MyTimeUtil.parseTime(starttime),  MyTimeUtil.parseTime(deadline),  expressType));
        }catch (ParseException e){
            return resultMessage.setInfo(OPERATE_FAIL,"日期格式错误"+e.getStackTrace());
        }catch (Exception e){
            e.printStackTrace();
            return resultMessage.setInfo(OPERATE_FAIL,e.getMessage());
        }

    }


    @RequestMapping("/take")
    @ResponseBody
    public ResultMessage takeMission(String secretId,String orderId){
        String takerId = securityUtil.getUserId(secretId);
        if(takerId==null) return resultMessage.setInfo(EXPIRE,"身份验证过期，请重新登录");
        return resultMessage.setInfo(orderService.takeMission(takerId,orderId));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultMessage delete(String orderId, String role){
        return resultMessage.setInfo(orderService.deleteOrder(orderId,Byte.valueOf(role)));
    }


}
