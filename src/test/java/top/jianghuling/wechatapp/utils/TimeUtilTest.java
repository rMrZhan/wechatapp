package top.jianghuling.wechatapp.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TimeUtilTest {

    @Test
    public void parseString() {
        try{
            System.out.println(MyTimeUtil.parseTime("2018-09-21 00:00:00"));
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}