package top.jianghuling.wechatapp.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.jianghuling.wechatapp.entity.SmsCode;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SmsCodeMapperTest {

    @Autowired
    private SmsCodeMapper smsCodeMapper;
    @Test
    public void insert() {
        SmsCode smsCode = new SmsCode();
        smsCode.setPhone("11111111111");
        smsCode.setCode("9898");
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        smsCode.setCreateTime(timeStamp);
        smsCodeMapper.insert(smsCode);
    }

    @Test
    public void selectByPrimaryKey() {
        SmsCode smsCode = smsCodeMapper.selectByPrimaryKey("11111111111");
        if(smsCode==null) System.out.println("null");
        Date createTime = smsCode.getCreateTime();
        System.out.println(createTime.getTime());
        System.out.println(System.currentTimeMillis());
    }
}