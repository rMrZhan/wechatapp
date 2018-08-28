package top.jianghuling.wechatapp.utils;

public class Result {
    public static final int SUCCESS = 200;
    public static final int UNKNOWN_FAILURE = 100; //未知错误
    public static final int WRONG_STUID = 301;//教务处账号或密码错误
    public static final int NO_SMS_CODE = 401;//未获取验证码
    public static final int WRONG_SMS_CODE = 402;//验证码错误
    public static final int SMS_CODE_EXPIRY = 403;//验证码过期失效
    public static final int SERVER_BUSY = 500;//服务器繁忙
    public static final int WRONG_ORDER_INFO = 601;//订单填写信息有误
    public static final int DUPLICATE_ORDER = 602;//订单已经存在
}
