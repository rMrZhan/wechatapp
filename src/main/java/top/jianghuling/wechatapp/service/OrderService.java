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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Value("${Constants.MissionState.MISSION_SUCCESS}")
    private Byte MISSION_SUCCESS;
    @Value("${Constants.MissionState.MISSION_FAIL}")
    private Byte MISSION_FAIL;
    @Value("${Constants.MissionState.MISSION_ONGOING}")
    private Byte MISSION_ONGOING;
    @Value("${Constants.MissionState.MISSION_CANCEL}")
    private Byte MISSION_CANCLE;
    @Value("${Constants.OrderState.ORDER_INAIR}")
    private Byte ORDER_INAIR;//订单刚刚发布
    @Value("${Constants.OrderState.ORDER_SUCCESS}")
    private Byte ORDER_SUCCESS;//订单完成
    @Value("${Constants.OrderState.ORDER_ONGOING}")
    private Byte ORDER_ONGOING;//任务在进行中
    @Value("${Constants.OrderState.ORDER_CANCEL}")
    private Byte ORDER_RELEASER_CANCEL;//订单被发布者取消
    @Value("${Constants.OrderState.ORDER_FAIL}")
    private Byte ORDER_FAIL;//订单失败（过期未拿到）
    //如果订单被执行者主动取消，则将订单重新发布
    @Value("${Constants.OrderState.ORDER_CONFIRM_FINISH}")
    private Byte ORDER_CONFIRM_FINISH;


    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MissionMapper missionMapper;

    //发布订单---测试成功
    @Transactional
    public boolean releaseNewOrder(String releaserId,String goodsCode, String note, float reward, String hostName, String hostPhone,
                               String takeAddress, String destination, String goodsWeight,
                               Date starttime, Date deadline,String expressType){
        try{
            String orderId = UUID.randomUUID().toString();

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

    //接单-----测试成功
    @Transactional
    public boolean takeMission(String takerId,String orderId,int version){

        Date date = new Date();
        Timestamp acceptTime = new Timestamp(date.getTime());

        Mission mission = new Mission();
        mission.setMissionId(UUID.randomUUID().toString());
        mission.setAcceptTime(acceptTime);
        mission.setOrderId(orderId);
        mission.setTakerId(takerId);
        mission.setMissionId(orderId+date.getTime());

        Order acceptOrder = orderMapper.selectByPrimaryKey(orderId);
        acceptOrder.setVersion(version);
        if(acceptOrder.getOrderState()!=ORDER_INAIR){//被其他人接单或被发布者取消
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚
            return false;
        }
        else{
            acceptOrder.setOrderState(ORDER_ONGOING);
            orderMapper.updateByPrimaryKey(acceptOrder);
            mission.setMissionState(MISSION_ONGOING);
            missionMapper.insert(mission);

            return true;
        }

    }

    //发布者取消订单--测试成功
    @Transactional
    public boolean cancelOrderByHost(String orderId,String missionId){

       if(missionId!=null&&missionId!=""){ //任务已经被人接单
           updateMissionState(orderId,MISSION_CANCLE);
       }
       if(orderMapper.selectByPrimaryKey(orderId).getOrderState()==ORDER_ONGOING){
           updateOrderState(orderId,ORDER_RELEASER_CANCEL);
       }
       updateOrderState(orderId,ORDER_RELEASER_CANCEL);
       return true;

    }

    //任务接收者取消订单----测试成功
    @Transactional
    public boolean abandonMission(String orderId){
        try{
            //TODO：惩罚
            updateMissionState(orderId,MISSION_FAIL);
            updateOrderState(orderId,ORDER_INAIR);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    //任务成功-----测试成功
    @Transactional
    public boolean missionSuccess(String orderId){

        updateOrderState(orderId,ORDER_SUCCESS);
        updateMissionState(orderId,MISSION_SUCCESS);
        //TODO:支付
        return true;

    }


    //任务失败
    @Transactional
    public boolean missionFail(String orderId){
        updateMissionState(orderId,MISSION_FAIL);
        //TODO:扣费
        return true;
    }

    //浏览未接单的订单
    @Transactional
    public List<BriefOrder> browseReleaseOrders(int pageNum, int pageSize){

       return  orderMapper.selectBriefOrderByPage(pageNum*pageSize,pageSize,ORDER_INAIR);
    }

    //浏览我接单的任务（包括历史任务，正在进行，取消的任务）
    @Transactional
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
        oe.setOrderByClause("release_time desc");
       return orderMapper.selectByExample(oe).subList(pageNum*pageSize,pageSize);

    }


    //浏览我发布的
    @Transactional
    public List<OrderLinkMission> browseMyReleaseOrder(String userId, int pageNum, int pageSize){
        return orderMapper.selectOrderMission(userId,pageNum*pageSize,pageSize);//已经根据发布时间排序过
    }

    @Transactional
    /*
    * 送件人拿到快递之后确认送达，三天之内，如果收件人对订单没有认证任务失败的操作，
    * 则完成交易。收件人也可以主动确认收件，完成交易
    * */
    public void confirmFinishMission(String orderId){
        orderMapper.selectByPrimaryKey(orderId).setOrderState(ORDER_CONFIRM_FINISH);
    }

    @Transactional
    public void updateMissionState(String orderId,Byte missionState){
        Mission updateMission = missionMapper.findMissionByOrderId(orderId);
        updateMission.setMissionState(missionState);
        MissionExample me = new MissionExample();
        MissionExample.Criteria meCriteria = me.createCriteria();
        meCriteria.andOrderIdEqualTo(updateMission.getOrderId());
        updateMission.setFinishTime(new Timestamp(new Date().getTime()));
        missionMapper.updateByExample(updateMission,me);
    }

    @Transactional
    public void updateOrderState(String orderId,Byte orderState){
        Order updateOrder = orderMapper.selectByPrimaryKey(orderId);
        updateOrder.setOrderState(orderState);
        orderMapper.updateByPrimaryKey(updateOrder);

    }


}

