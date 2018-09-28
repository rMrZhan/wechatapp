package top.jianghuling.wechatapp.results;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class LoginResultMessage extends ResultMessage{

    private String phone;
    private String stuId;
    public LoginResultMessage setInfo(int code, String message, String phone, String stuId){
        setCode(code);
        setMessage(message);
        this.phone = phone;
        this.stuId = stuId;
        return this;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
}
