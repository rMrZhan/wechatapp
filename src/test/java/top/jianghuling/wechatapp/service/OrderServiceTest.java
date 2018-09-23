package top.jianghuling.wechatapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.jianghuling.wechatapp.model.BriefOrder;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void releaseOrderTest() {
        Date dateTimeStart = new Date();
        dateTimeStart.setHours(22);
        dateTimeStart.setMinutes(Integer.parseInt("02"));
        dateTimeStart.setSeconds(0);
        Date dateTimeEnd = new Date();
        dateTimeEnd.setHours(23);
        dateTimeEnd.setMinutes(Integer.parseInt("32"));
        dateTimeEnd.setSeconds(0);
        orderService.releaseNewOrder("eaefeeawfreeagf323fazgnfrvv","99--754","没啥",2.5f,"小马","17711388724",
                "西南门","西元66舍","10千克",dateTimeStart,dateTimeEnd,"顺丰");
    }

    @Test
    public void acceptOrderTest(){
        System.out.println(orderService.takeMission("fieaofeiamfafrmlereaofmeao","69e9adcb0f24104d070a1f507ba2aee6d108"));
    }

    @Test
    public void missionSuccessTest(){
        System.out.println(orderService.missionSuccess("e1b7bbf40cb290406f09b1d01611d3fc947f"));
    }


    @Test
    public void cancelOrderByHostTest(){
        System.out.println(orderService.cancelOrderByHost("46e4b1b27b1a6a174196cdebfc0c5711"));
    }

    @Test
    public void abandonMissionTest(){
        System.out.println(orderService.abandonMission("bbae54590c4aca74b1025c1b8f1675f4"));

    }

    @Test
    public void browseReleaseOrdersTest(){
        List<BriefOrder> briefOrders = orderService.browseReleaseOrders(0,5);
        System.out.println(briefOrders.get(0).gettEnd());
    }
    @Test
    public void browseMyReleaseOrderTest(){
        System.out.println(orderService.browseMyReleaseOrder("eaefeeawfreeagf323fazgnfrvv",0,5).get(1).getOrderId());

    }

    @Test
    public void browseMyMissionRecordsTest(){
        System.out.println(orderService.browseMyMissionRecords("fieaofeiamfafrmlereaofmeao",1,10).size());
    }

    @Test
    public void confirmMissionFinishTest(){
        orderService.confirmFinishMission("e1b7bbf40cb290406f09b1d01611d3fc947f");
    }

    @Test
    public void missionFailTest(){
        orderService.missionFail("69e9adcb0f24104d070a1f507ba2aee6d108");
    }

    @Test
    public void cancelOrderTest(){
        System.out.println(orderService.preCancelOrderByHost("b6e4b1990a68304a5e085a20d71908572688"));
        System.out.println(orderService.cancelOrderByHost("b6e4b1990a68304a5e085a20d71908572688"));
    }







}