package top.jianghuling.wechatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mission {
    @JsonIgnore
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String missionId;

    private String orderId;

    private String takerId;
    @JsonIgnore
    private Date acceptTime;
    @JsonIgnore
    private Date finishTime;

    public String gettAccept() {
        return tAccept;
    }

    public void settAccept(String tAccept) {
        this.tAccept = tAccept;
    }

    public String gettFinish() {
        return tFinish;
    }

    public void settFinish(String tFinish) {
        this.tFinish = tFinish;
    }

    private String tAccept;

    private String tFinish;


    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId == null ? null : missionId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getTakerId() {
        return takerId;
    }

    public void setTakerId(String takerId) {
        this.takerId = takerId == null ? null : takerId.trim();
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
        tAccept = sdf.format(acceptTime);
    }

    public Date getFinishTime() {
        return finishTime;

    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
        tFinish = sdf.format(finishTime);
    }
}