package top.jianghuling.wechatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import top.jianghuling.wechatapp.dao.MissionMapper;
import top.jianghuling.wechatapp.dao.OrderMapper;
import top.jianghuling.wechatapp.entity.*;
import top.jianghuling.wechatapp.results.BriefOrder;
import top.jianghuling.wechatapp.utils.SecurityUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Value("${Constants.MissionState.MISSION_SUCCESS")
    private Byte MISSION_SUCCESS;
    @Value("${Constants.MissionState.MISSION_FAIL")
    private Byte MISSION_FAIL;
    @Value("${Constants.MissionState.MISSION_ONGOING")
    private Byte MISSION_ONGOING;
    @Value("${Constants.MissionState.MISSION_CANCLE")
    private Byte MISSION_CANCLE;
    @Value("${Constants.OrderState.ORDER_INAIR}")
    private Byte ORDER_INAIR;//订单刚刚发布
    @Value("${Constants.OrderState.ORDER_SUCCESS}")
    private Byte ORDER_SUCCESS;//订单完成
    @Value("${Constants.OrderState.ORDER_ONGOING}")
    private Byte ORDER_ONGOING;//任务在进行中
    @Value("${Constants.OrderState.ORDER_CANCLE}")
    private Byte ORDER_RELEASER_CANCLE;//订单被发布者取消
    @Value("${Constants.OrderState.ORDER_FAIL}")
    private Byte ORDER_FAIL;//订单失败（过期未拿到）
    //如果订单被执行者主动取消，则将订单重新发布


    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MissionMapper missionMapper;

    //发布订单
    @Transactional
    public boolean releaseNewOrder(String releaserId,String goodsCode, String note, float reward, String hostName, String hostPhone,
                               String takeAddress, String destination, String goodsWeight,
                               Date starttime, Date deadline,String expressType){
        try{
            String orderId = SecurityUtil.md5(hostPhone+goodsCode+(new Date()));

                Order order = new Order();

                order.setOrderState(ORDER_INAIR);
                order.setReleaserId(releaserId);
                order.setDeadline(deadline);
                order.setDestination(destination);
                order.setGoodsCode(goodsCode);
                order.setGoodsWeight(goodsWeight);
                order.setHostName(hostName);
                order.setNote(note);
                order.setOrderId(orderId);
                order.setHostPhone(hostPhone);
                order.setExpressType(expressType);
                order.setTakeAddress(takeAddress);
                order.setReward(reward);
                order.setStarttime(starttime);
                Date date = new Date();
                Timestamp releaseTime = new Timestamp(date.getTime());
                order.setReleaseTime(releaseTime);
                orderMapper.insert(order);
                return true;

        }catch (Exception e){
            return false;
        }
    }

    //接单
    @Transactional
    public boolean takeMission(String takerId,String orderId){

        Date date = new Date();
        Timestamp acceptTime = new Timestamp(date.getTime());
        Mission mission = new Mission();
        mission.setAcceptTime(acceptTime);
        mission.setOrderId(orderId);
        Order acceptOrder = orderMapper.selectByPrimaryKey(orderId);
        if(acceptOrder.getOrderState()!=ORDER_INAIR){//被其他人接单或被发布者取消
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚
            return false;
        }

        else{
            acceptOrder.setOrderState(ORDER_ONGOING);
            mission.setMissionState(MISSION_ONGOING);
            return true;
        }

    }

    //发布者取消订单
    public boolean cancleOrderByHost(String orderId){
       Order order = orderMapper.selectByPrimaryKey(orderId);
       if(order.getOrderState()==ORDER_ONGOING){
           Mission mission = missionMapper.findMissionByOrderId(orderId);
           mission.setMissionState(MISSION_CANCLE);
           //TODO：扣费？
       }
        order.setOrderState(ORDER_RELEASER_CANCLE);
       return true;

    }

    //任务接收者取消订单
    public boolean abandonMission(String orderId){
        try{
            //TODO：惩罚
            missionMapper.findMissionByOrderId(orderId).setMissionState(MISSION_FAIL);
            orderMapper.selectByPrimaryKey(orderId).setOrderState(ORDER_INAIR);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    //任务成功
    public boolean missionSuccess(String orderId){
        orderMapper.selectByPrimaryKey(orderId).setOrderState(ORDER_SUCCESS);
        Mission mission=missionMapper.findMissionByOrderId(orderId);
        mission.setMissionState(MISSION_SUCCESS);
        mission.setFinishTime(new Timestamp(new Date().getTime()));
        //TODO:支付
        return true;

    }


    //任务失败
    public boolean missionFailed(String orderId){
        Mission mission = missionMapper.findMissionByOrderId(orderId);
        mission.setMissionState(MISSION_FAIL);
        mission.setFinishTime(new Timestamp(new Date().getTime()));
        //TODO:扣费
        return true;

    }

    //浏览未接单的订单
    public List<BriefOrder> browseReleaseOrders(int pageNum, int pageSize){
       return  orderMapper.selectBriefOrderByPage(pageNum,pageSize,ORDER_INAIR);
    }

    //浏览我接单的任务（包括历史任务，正在进行，取消的任务）
    public List<Order> browseMyMissionRecords(String takerId, int pageNum, int pageSize){
        MissionExample me = new MissionExample();
        MissionExample.Criteria meCriteria= me.createCriteria();
        meCriteria.andTakerIdEqualTo(takerId);
        List<Mission> missionList=missionMapper.selectByExample(me);
        List<String> orderIds = new ArrayList<>();
        for(Mission ms : missionList){
            orderIds.add(ms.getOrderId());
        }

        OrderExample oe = new OrderExample();
        OrderExample.Criteria oeCriteria = oe.createCriteria();
        oeCriteria.andOrderIdIn(orderIds);
        return orderMapper.selectByExample(oe).subList(pageNum*pageSize,pageSize);

    }


    //浏览我发布的
    public List<OrderLinkMission> browseMyReleaseOrder(String userId, int pageNum, int pageSize){
        return orderMapper.selectOrderMission(userId,pageNum*pageSize,pageSize);
    }

}

