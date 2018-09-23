package top.jianghuling.wechatapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.jianghuling.wechatapp.dao.RedisDao;
import top.jianghuling.wechatapp.dao.UserInfoMapper;
import top.jianghuling.wechatapp.model.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisDao redisDao;
    @Test
    public void getSmsCode(){
        accountService.getMessageCode("17711388724");
    }

    @Test
    public void testnull(){
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey("lkkkk");
        if(userInfo==null)
        System.out.println("不存在");
        //System.out.println("账号："+userInfo.getUserId());

    }
    @Test
    public void getOpenid(){
        try{
            System.out.println(accountService.getOpenidAndSessionKey("0334rkKt0Notrc1ZHdNt06YkKt04rkKw"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void login() {
        System.out.println(redisDao.get("ef1a6a947a5e723c4b64299c274d6008"));
    }
}