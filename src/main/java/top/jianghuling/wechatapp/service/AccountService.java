package top.jianghuling.wechatapp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.jianghuling.wechatapp.dao.UserMapper;
import top.jianghuling.wechatapp.entity.User;
import top.jianghuling.wechatapp.utils.Verify;

@Service
public class AccountService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Verify verify;
    @Transactional
    public String addNewUser(String phone, String address, String sutId, String stuPassword){

        if(verify.verifyStuId(sutId,stuPassword)){
            User user = new User();
            user.setAddress(address);
            user.setPhone(phone);
            user.setStuId(sutId);
            user.setStuPassword(stuPassword);
            userMapper.insert(user);
            return "新建账号成功";
        }
        return "新建账号失败";

    }


}
