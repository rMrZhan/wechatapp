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
    private  Byte ORDER_CONFIRM_FINISH;
    @Value("${Constants.OrderState.ORDER_ABANDON}")
    private Byte ORDER_ABANDON;
    @Value("${Constants.Operate.SUCCESS}")
    private Byte OPERATE_SUCCESS;
    @Value("${Constants.Operate.FAIL}")
    private Byte OPERATE_FAIL;

    private final int initOrderVsn = 0;


    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MissionMapper missionMapper;

    /**
     *  ModifyDate: 2018/9/15
     * Description: 发布订单
     * Concurrency：无
     * Test：
     * @author  Jason
     */
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
                order.setVersion(initOrderVsn);
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

    /**
     *
     * ModifyDate: 2018/9/17/16
     * Description: 接受订单（任务）
     * Concurrency：①用户发布订单之前就被接单了
     *              ②多用户同时发送了下单请求到服务器，出现竞争问题
     * Test：
     * @author  Jason
     */
    @Transactional
    public int takeMission(String takerId,String orderId) {
         Date date = new Date();

        Timestamp acceptTime = new Timestamp(date.getTime());

        Mission mission = new Mission();
        mission.setMissionId(UUID.randomUUID().toString());
        mission.setAcceptTime(acceptTime);
        mission.setOrderId(orderId);
        mission.setTakerId(takerId);

        Order acceptOrder = orderMapper.selectByPrimaryKey(orderId);
        if (acceptOrder.getOrderState() == ORDER_RELEASER_CANCEL) {//订单被取消
            return ORDER_RELEASER_CANCEL;
        } else if (acceptOrder.getOrderState() == ORDER_ONGOING) {//订单被其他人抢到
            return ORDER_ONGOING;
        } else {
            if (orderMapper.updateStateLock(orderId, ORDER_ONGOING, acceptOrder.getVersion()) != 0) {//第二遍筛选，活锁，检查version
                missionMapper.insert(mission);
                return OPERATE_SUCCESS;
            } else {
                return ORDER_ONGOING;//在一瞬间被别人抢到;
            }
        }
    }


    /**
     * ModifyDate: 2018/9/17/19
     * Description: 发布者取消自己发布的订单预处理
     * Condition：接单/未接单
     * Concurrency：①发单人取消订单的的瞬间之前被接单了
     *              ②发布者要取消已经被接单的单子却被在瞬间之前被接单人放弃任务了
     *              ③如果接单人已经把快递送达，发单人却要取消单子：在用户点取消订单的时候查一下订单是否被接单的人确认完成工作
     * Test：
     * @author  Jason
     */
    @Transactional
    public int preCancelOrderByHost(String orderId) {
        Order targetOrder = orderMapper.selectByPrimaryKey(orderId);
        Byte currentOrderState = targetOrder.getOrderState();
        if (currentOrderState == ORDER_CONFIRM_FINISH) {
            return ORDER_CONFIRM_FINISH;//提醒用户：接单者已经确认送达，请刷新列表
        } else if (currentOrderState == ORDER_ABANDON) {
            return ORDER_ABANDON;//任务被放弃;
        } else {
            if (orderMapper.updateStateLock(orderId, ORDER_RELEASER_CANCEL, targetOrder.getVersion()) != 0) {
                return OPERATE_SUCCESS;
            } else {
                return orderMapper.selectByPrimaryKey(orderId).getOrderState();//返回值一般为ORDER_ONGOIG或者是ORDER_CONFIRM_FINISH,即被人接单了，提醒用户是否依然取消
            }
        }
    }


    /**
     * 2018/9/17/19
     * Description: 即使被接单，依然取消
     * Condition：接单
     * Test：
     * @author  Jason
     */
    @Transactional
    public int cancelOrderByHost(String orderId){
        Date date = new Date();
        if(orderMapper.updateStateLock(orderId,ORDER_RELEASER_CANCEL,orderMapper.selectByPrimaryKey(orderId).getVersion())!=0){
            Mission mission = missionMapper.selectByOrderId(orderId);
            mission.setFinishTime(new Timestamp(date.getTime()));
            missionMapper.updateByPrimaryKeySelective(mission);
            return OPERATE_SUCCESS;
        }else return OPERATE_FAIL;//出现并发，让用户刷新

    }


    /**
     * ModifyDate: 2018/9/17/19
     * Description：接单人放弃任务
     * Condition：
     * Concurrency：①放弃的任务的前一瞬间，任务被发布者自己取消
     *              ②放弃任务的前一瞬间，用户自己确认订单完成
     * Test：
     * @author  Jason
     */
    @Transactional
    public int abandonMission(String orderId){
        Order targetOrder = orderMapper.selectByPrimaryKey(orderId);
        Byte currentOrderState=targetOrder.getOrderState();
        if(currentOrderState==ORDER_RELEASER_CANCEL)
            return ORDER_RELEASER_CANCEL;
        else if(currentOrderState==ORDER_SUCCESS)
            return ORDER_SUCCESS;
        else if(orderMapper.updateStateLock(orderId,ORDER_ABANDON,targetOrder.getVersion())!=0){
            return OPERATE_SUCCESS;
        }else{
            return orderMapper.selectByPrimaryKey(orderId).getOrderState(); //两种并发在前一瞬间发生
        }
    }


    /**
     * ModifyDate: 2018/9/17/19
     * Description：任务成功
     * Condition：
     * Concurrency：①确认任务成功的前一刻，收件人放弃订单
     *
     * Test：
     * @author  Jason
     */
    @Transactional
    public int missionSuccess(String orderId){

        Order targetOrder = orderMapper.selectByPrimaryKey(orderId);
        Byte currentState = targetOrder.getOrderState();
        if(currentState==ORDER_ABANDON){
            //TODO:钱转入公司账户
            return OPERATE_SUCCESS;
        }else{
            if(orderMapper.updateStateLock(orderId,ORDER_SUCCESS,targetOrder.getVersion())!=0){//如果瞬间前收件人放弃订单
                orderMapper.updateStateLock(orderId,ORDER_SUCCESS,targetOrder.getVersion()+1);
                //TODO:钱转入公司账户
            }
            return OPERATE_SUCCESS;
        }
    }


    /**
     * ModifyDate: 2018/9/17 23:12
     * Description：任务失败,
     * Condition：
     * Concurrency：
     * Test：
     * @author  Jason
     */
    @Transactional
    public int missionFail(String orderId){
        orderMapper.updateStateLock(orderId,ORDER_FAIL,orderMapper.selectByPrimaryKey(orderId).getOrderState());
        return OPERATE_SUCCESS;
    }

    /**
     * ModifyDate: 2018/9/17 23:29
     * Description: 浏览未接单的任务
     * Test:
     *
     * @author Jason
    * */
    @Transactional
    public List<BriefOrder> browseReleaseOrders(int pageNum, int pageSize){

       return  orderMapper.selectBriefOrderByPage(pageNum*pageSize,pageSize,ORDER_INAIR);
    }

    /**
     * ModifyDate: 2018/9/17 23:29
     * Description: 浏览我接单的任务（包括历史任务，正在进行，放弃的任务）
     * Test:
     * @author Jason
     * */
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



    /**
     * ModifyDate: 2018/9/17 23:46
     * Description: 浏览我发布的
     * Test:
     * @author Jason
     * */
    @Transactional
    public List<OrderLinkMission> browseMyReleaseOrder(String userId, int pageNum, int pageSize){
        return orderMapper.selectOrderMission(userId,pageNum*pageSize,pageSize);//已经根据发布时间排序过
    }


    /**
     * Description: 送件人拿到快递之后确认送达，三天之内，如果收件人对订单没有认证任务失败的操作，则完成交易。收件人也可以主动确认收件，完成交易
     *
     * */
    @Transactional
    public void confirmFinishMission(String orderId){
        orderMapper.selectByPrimaryKey(orderId).setOrderState(ORDER_CONFIRM_FINISH);
    }

}

