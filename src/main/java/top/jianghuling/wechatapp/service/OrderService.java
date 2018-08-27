package top.jianghuling.wechatapp.service;

import org.springframework.stereotype.Service;
import top.jianghuling.wechatapp.entity.OrderRelease;

import java.sql.Time;
import java.util.Date;

@Service
public class OrderService {

    //发布订单
    public void releaseOrder(String goodsCode, String note, float reward, String hostName, String hostPhone,
                               String takeAddress, String destination, String goodsWeight, String goodsVolume,
                               Date starttime, Date deadline){
        String orderId = hostPhone+System.currentTimeMillis();
        OrderRelease order = new OrderRelease();
        order.setDeadline(deadline);
        order.setDestination(destination);
        order.setGoodsCode(goodsCode);
        order.setGoodsWeight(goodsWeight);
        order.setHostName(hostName);
        order.setNote(note);
        order.setOrderId(orderId);
        order.setHostPhone(hostPhone);
        order.setGoodsVolume(goodsVolume);
        order.setTakeAddress(takeAddress);
        order.setReward(reward);
        order.setStarttime(starttime);


    }
}
