package top.jianghuling.wechatapp.results;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class ResultMessage {


    @JsonIgnore
    @Value("${Constants.OrderState.ORDER_INAIR}")
    private Byte ORDER_INAIR;//订单刚刚发布
    @JsonIgnore
    @Value("${Constants.OrderState.ORDER_SUCCESS}")
    private Byte ORDER_SUCCESS;//订单完成
    @JsonIgnore
    @Value("${Constants.OrderState.ORDER_ONGOING}")
    private Byte ORDER_ONGOING;//任务在进行中
    @JsonIgnore
    @Value("${Constants.OrderState.ORDER_CANCEL}")
    private Byte ORDER_RELEASER_CANCEL;//订单被发布者取消
    @JsonIgnore
    @Value("${Constants.OrderState.ORDER_FAIL}")
    private Byte ORDER_FAIL;//订单失败（过期未拿到）
    //如果订单被执行者主动取消，则将订单重新发布
    @JsonIgnore
    @Value("${Constants.OrderState.ORDER_CONFIRM_FINISH}")
    private  Byte ORDER_CONFIRM_FINISH;
    @JsonIgnore
    @Value("${Constants.OrderState.ORDER_ABANDON}")
    private Byte ORDER_ABANDON;
    @JsonIgnore
    @Value("${Constants.Operate.SUCCESS}")
    private Byte OPERATE_SUCCESS;
    @JsonIgnore
    @Value("${Constants.Operate.FAIL}")
    private Byte OPERATE_FAIL;


    private int code;
    private String message;

    public ResultMessage setInfo(int code, String message){
        this.code = code;
        this.message = message;
        return this;
    }


    public ResultMessage setInfo(int resultCode){
        if(resultCode==ORDER_ABANDON)
            message="任务已被接单者放弃";
        else if(resultCode==ORDER_CONFIRM_FINISH)
            message = "快件已被送达，请确认";
        else if(resultCode==ORDER_FAIL)
            message = "任务失败";
        else if(resultCode==ORDER_INAIR)
            message = "订单未被接单";
        else if(resultCode==ORDER_ONGOING)
            message="订单正在进行中";
        else if (resultCode==ORDER_RELEASER_CANCEL)
            message="订单已被发单者取消";
        else if(resultCode==ORDER_SUCCESS)
            message="订单完成";
        else if(resultCode==OPERATE_SUCCESS)
            message="操作成功";
        else if(resultCode==OPERATE_FAIL)
            message="操作失败";

        return this;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
