package top.jianghuling.wechatapp.results;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

public class LoginState extends ResultMessage{

    @JsonIgnore
    @Value("${Constants.LoginState.FULL}")
    private int full;
    @JsonIgnore
    @Value("${Constants.LoginState.LACK_PHONE}")
    private int lackPhone;
    @JsonIgnore
    @Value("${Constants.LoginState.LACK_STUID}")
    private int lackStuId;

}
