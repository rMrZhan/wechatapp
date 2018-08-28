package top.jianghuling.wechatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.jianghuling.wechatapp.dao.OrderAcceptMapper;
import top.jianghuling.wechatapp.dao.OrderReleaseMapper;
import top.jianghuling.wechatapp.entity.OrderAccept;
import top.jianghuling.wechatapp.entity.OrderAcceptExample;
import top.jianghuling.wechatapp.entity.OrderRelease;
import top.jianghuling.wechatapp.entity.OrderReleaseExample;
import top.jianghuling.wechatapp.results.Order;
import top.jianghuling.wechatapp.utils.Result;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Value("${Constants.ORDER_INAIR}")
    private String ORDER_INAIR;
    @Value("${Constants.ORDER_SUCCESS}")
    private String ORDER_SUCCESS;
    @Value("${Constants.ORDER_ONGOING}")
    private String ORDER_ONGOING;
    @Value("${Constants.ORDER_CANCLE}")
    private String ORDER_CANCLE;
    @Value("${Constants.ORDER_FAIL}")
    private String ORDER_FAIL;


    @Autowired
    private OrderReleaseMapper orderReleaseMapper;
    @Autowired
    private OrderAcceptMapper orderAcceptMapper;

    //发布订单
    public int releaseOrder(String goodsCode, String note, float reward, String hostName, String hostPhone,
                               String takeAddress, String destination, String goodsWeight, String goodsVolume,
                               Date starttime, Date deadline,String expressType){
        try{
            String orderId = hostPhone+goodsCode+(new Date())+expressType;
            if(orderReleaseMapper.selectByPrimaryKey(orderId)!=null){
                OrderRelease order = new OrderRelease();
                order.setDeadline(deadline);
                order.setDestination(destination);
                order.setGoodsCode(goodsCode);
                order.setGoodsWeight(goodsWeight);
                order.setHostName(hostName);
                order.setNote(note);
                order.setOrderId(orderId);
                order.setHostPhone(hostPhone);
                order.setExpressType(expressType);
                order.setGoodsVolume(goodsVolume);
                order.setTakeAddress(takeAddress);
                order.setReward(reward);
                order.setStarttime(starttime);
                Date date = new Date();
                Timestamp releaseTime = new Timestamp(date.getTime());
                order.setReleaseTime(releaseTime);
                orderReleaseMapper.insert(order);
                return Result.SUCCESS;
            }else{
                return Result.DUPLICATE_ORDER;
            }

        }catch (Exception e){
            e.printStackTrace();
            return Result.WRONG_ORDER_INFO;

        }
    }

    //接单
    public int acceptOrder(String takerPhone,String orderId){
        OrderRelease orderRelease = orderReleaseMapper.selectByPrimaryKey(orderId);
        OrderAccept orderAccept = new OrderAccept();
        Date date = new Date();
        Timestamp acceptTime = new Timestamp(date.getTime());

        orderAccept.setOrderId(orderId);
        orderAccept.setAcceptTime(acceptTime);
        orderAccept.setDestination(orderRelease.getDestination());
        orderAccept.setGoodsCode(orderRelease.getGoodsCode());
        orderAccept.setGoodsVolume(orderRelease.getGoodsVolume());
        orderAccept.setGoodsWeight(orderRelease.getGoodsWeight());
        orderAccept.setHostName(orderRelease.getHostName());
        orderAccept.setNote(orderRelease.getNote());
        orderAccept.setHostPhone(orderRelease.getHostPhone());
        orderAccept.setExpressType(orderRelease.getExpressType());
        orderAccept.setIsfinished(new Byte(ORDER_ONGOING));
        orderAccept.setTakeAddress(orderRelease.getTakeAddress());
        orderAccept.setTakerPhone(takerPhone);
        orderAccept.setStarttime(orderRelease.getStarttime());
        orderAccept.setDeadline(orderRelease.getDeadline());
        orderAccept.setReward(orderRelease.getReward());
        orderAccept.setReleaseTime(orderRelease.getReleaseTime());

        orderAcceptMapper.insert(orderAccept);
        orderReleaseMapper.deleteByPrimaryKey(orderId);
        return Result.SUCCESS;

    }

    //发布者取消订单
    public int cancleOrderByHost(String orderId){
        if(orderReleaseMapper.selectByPrimaryKey(orderId)!=null){
            orderReleaseMapper.deleteByPrimaryKey(orderId);
            return Result.SUCCESS;
        }else{
            //TODO:订单已经被接收情况
            return Result.SUCCESS;
        }
    }

    //任务接收者取消订单
    public int cancleOrderByTaker(String orderId){
        //TODO：惩罚
        orderAcceptMapper.deleteByPrimaryKey(orderId);
        return Result.SUCCESS;
    }

    //任务成功
    public int missionSuccess(String orderId){
        OrderAccept successOrder = new OrderAccept();
        successOrder.setOrderId(orderId);
        successOrder.setIsfinished(new Byte(ORDER_SUCCESS));
        orderAcceptMapper.updateByPrimaryKeySelective(successOrder);
        //TODO: 支付
        return Result.SUCCESS;
    }


    //任务失败
    public int missionFailed(String orderId){
        OrderAccept failOrder = new OrderAccept();
        failOrder.setOrderId(orderId);
        failOrder.setIsfinished(new Byte(ORDER_FAIL));
        orderAcceptMapper.updateByPrimaryKeySelective(failOrder);
        //TODO:支付
        return Result.SUCCESS;
    }

    //浏览未接单的订单
    public List<Order> browseReleaseOrders(int indexStart, int offset){
        List<OrderRelease> orderReleases= orderReleaseMapper.selectByLimit(indexStart,offset);
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        for(OrderRelease orderRelease : orderReleases){
            order.setDeadline(orderRelease.getDeadline());
            order.setDestination(orderRelease.getDestination());
            order.setExpressType(orderRelease.getExpressType());
            order.setGoodsVolume(orderRelease.getGoodsVolume());
            order.setGoodsWeight(orderRelease.getGoodsWeight());
            order.setReward(orderRelease.getReward());
            order.setStarttime(orderRelease.getStarttime());
            orders.add(order);
        }
        return orders;
    }

    //浏览我接单的任务（包括历史任务，正在进行，取消的任务）
    public List<OrderAccept> browseMyMissionRecords(String phone,int startIndex, int offset){
        return orderAcceptMapper.selectByTakerPhoneLimit(phone,startIndex,offset);
    }


    //浏览我发布的
    public List<OrderAccept> browseMyReleaseOrder(String phone,int startIndex, int offset){
        OrderReleaseExample orderReleaseExample = new OrderReleaseExample();
        OrderReleaseExample.Criteria criteriaRel = orderReleaseExample.createCriteria();
        criteriaRel.andHostPhoneEqualTo(phone);
        orderReleaseExample.setOrderByClause("release_time");
        List<OrderRelease> releaseList = orderReleaseMapper.selectByExample(orderReleaseExample);
        List<OrderAccept> list = new ArrayList<>();//统一格式，客户端接收到的都是OrderAccept
        OrderAccept orderAccept = new OrderAccept();

            for(OrderRelease orderRelease:releaseList){
                orderAccept.setOrderId(orderRelease.getOrderId());
                orderAccept.setAcceptTime(null);
                orderAccept.setDestination(orderRelease.getDestination());
                orderAccept.setGoodsCode(orderRelease.getGoodsCode());
                orderAccept.setGoodsVolume(orderRelease.getGoodsVolume());
                orderAccept.setGoodsWeight(orderRelease.getGoodsWeight());
                orderAccept.setHostName(orderRelease.getHostName());
                orderAccept.setNote(orderRelease.getNote());
                orderAccept.setHostPhone(orderRelease.getHostPhone());
                orderAccept.setExpressType(orderRelease.getExpressType());
                orderAccept.setIsfinished(new Byte(ORDER_INAIR));
                orderAccept.setTakeAddress(orderRelease.getTakeAddress());
                orderAccept.setTakerPhone(null);
                orderAccept.setStarttime(orderRelease.getStarttime());
                orderAccept.setDeadline(orderRelease.getDeadline());
                orderAccept.setReward(orderRelease.getReward());
                orderAccept.setReleaseTime(orderRelease.getReleaseTime());
                list.add(orderAccept);
            }

            OrderAcceptExample orderAcceptExample = new OrderAcceptExample();
            OrderAcceptExample.Criteria criteriaAcp=orderAcceptExample.createCriteria();
            criteriaAcp.andHostPhoneEqualTo(phone);
            orderAcceptExample.setOrderByClause("release_time");//注意：根据发布时间排序
            List<OrderAccept> orderAccepts = orderAcceptMapper.selectByExample(orderAcceptExample);
            for(OrderAccept o: orderAccepts){
                list.add(o);
            }
            return list.subList(startIndex,offset);

    }

}

