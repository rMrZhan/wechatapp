package top.jianghuling.wechatapp.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.jianghuling.wechatapp.entity.OrderAccept;
import top.jianghuling.wechatapp.entity.OrderRelease;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderReleaseMapperTest {

    @Autowired
    private OrderReleaseMapper orderReleaseMapper;
    @Test
    public void deleteByPrimaryKey() {
        orderReleaseMapper.deleteByPrimaryKey("111");
    }
}