package top.jianghuling.wechatapp.results;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResultMessage {
    @JsonIgnore
    public static final int SUCCESS = 1;
    @JsonIgnore
    public static final int FAIL = 0;
    @JsonIgnore
    public static final int UNKNOWN_FAILURE = 100; //未知错误
    @JsonIgnore
    public static final int WRONG_STUID = 301;//教务处账号或密码错误
    @JsonIgnore
    public static final int NO_SMS_CODE = 401;//未获取验证码
    @JsonIgnore
    public static final int WRONG_SMS_CODE = 402;//验证码错误
    @JsonIgnore
    public static final int SMS_CODE_EXPIRY = 403;//验证码过期失效
    @JsonIgnore
    public static final int SERVER_BUSY = 500;//服务器繁忙
    @JsonIgnore
    public static final int WRONG_ORDER_INFO = 601;//订单填写信息有误
    @JsonIgnore
    public static final int DUPLICATE_ORDER = 602;//订单已经存在



    private int code;
    private String message;
    public ResultMessage(int resultCode, String resultMessage){
        this.code = resultCode;
        this.message=resultMessage;
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
