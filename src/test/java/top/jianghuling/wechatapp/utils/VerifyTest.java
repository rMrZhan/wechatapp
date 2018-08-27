package top.jianghuling.wechatapp.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VerifyTest {

    @Autowired
    Verify verify;

    @Test
    public void testVerifyStuId(){
       System.out.println(verify.verifyStuId("2016141463082","16117"));

    }


}