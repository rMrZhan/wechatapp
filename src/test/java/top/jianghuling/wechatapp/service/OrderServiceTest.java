package top.jianghuling.wechatapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

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
        orderService.releaseOrder("494942","快点送达",2.5f,"刘","17711388724",
                "西南门","西元三舍","1千克","一只手可以拿",dateTimeStart,dateTimeEnd,"百世");
    }

    @Test
    public void acceptOrderTest(){
        System.out.println(orderService.acceptOrder("17711388724","17711388724494942"));

    }

    @Test
    public void missionSuccessTest(){
        System.out.println(orderService.missionSuccess("17711388724494942"));
    }
}