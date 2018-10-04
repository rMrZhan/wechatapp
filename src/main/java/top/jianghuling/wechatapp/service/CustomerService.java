package top.jianghuling.wechatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.jianghuling.wechatapp.dao.UserCommentMapper;
import top.jianghuling.wechatapp.model.UserComment;
import top.jianghuling.wechatapp.results.ResultMessage;

@Service
public class CustomerService {

    @Value("${Constants.Operate.SUCCESS}")
    private Byte OPERATE_SUCCESS;
    @Value("${Constants.Operate.FAIL}")
    private Byte OPERATE_FAIL;

    @Autowired
    private UserCommentMapper userCommentMapper;
    @Autowired
    private ResultMessage resultMessage;

    @Transactional
    public ResultMessage addComment(String userPhone,String comment){
        try{
            UserComment userComment = new UserComment();
            userComment.setCommentContent(comment);
            userComment.setUserPhone(userPhone);
            userCommentMapper.insertSelective(userComment);
            return resultMessage.setInfo(OPERATE_SUCCESS);
        }catch (Exception e){
            return resultMessage.setInfo(OPERATE_FAIL);
        }


    }

}
