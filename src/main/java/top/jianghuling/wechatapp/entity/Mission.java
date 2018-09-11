package top.jianghuling.wechatapp.entity;

import java.util.Date;

public class Mission {
    private String orderId;

    private String takerId;

    private Date acceptTime;

    private Byte missionState;

    private Date finishTime;

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
    }

    public Byte getMissionState() {
        return missionState;
    }

    public void setMissionState(Byte missionState) {
        this.missionState = missionState;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}