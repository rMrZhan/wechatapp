package top.jianghuling.wechatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderLinkMission {

    @JsonIgnore
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String orderId;
    private String goodsCode;

    private String note;

    private Float reward;

    private String takerPhone;

    private String hostName;

    private String takeAddress;

    private String destination;

    private String goodsWeight;
    @JsonIgnore
    private Date starttime;
    @JsonIgnore
    private Date deadline;
    @JsonIgnore
    private Date releaseTime;
    @JsonIgnore
    private Date finishTime;
    @JsonIgnore
    private Date acceptTime;

    private String expressType;

    private String hostPhone;

    private Byte orderState;

    @JsonIgnore
    private String missionId;

    private String tRelease;
    private String tStart;
    private String tEnd;
    private String tFinish;
    private String tAccept;

    public String gettAccept() {
        return tAccept;
    }

    public void settAccept(String tAccept) {
        this.tAccept = tAccept;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        tAccept =sdf.format(acceptTime);
        this.acceptTime = acceptTime;
    }

    public String gettRelease() {
        return tRelease;
    }

    public void settRelease(String tRelease) {
        this.tRelease = tRelease;
    }

    public String gettStart() {
        return tStart;
    }

    public void settStart(String tStart) {
        this.tStart = tStart;
    }

    public String gettEnd() {
        return tEnd;
    }

    public void settEnd(String tEnd) {
        this.tEnd = tEnd;
    }

    public String gettFinish() {
        return tFinish;
    }

    public void settFinish(String tFinish) {
        this.tFinish = tFinish;
    }
    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Float getReward() {
        return reward;
    }

    public void setReward(Float reward) {
        this.reward = reward;
    }

    public String getTakerPhone() {
        return takerPhone;
    }

    public void setTakerPhone(String takerPhone) {
        this.takerPhone = takerPhone;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getTakeAddress() {
        return takeAddress;
    }

    public void setTakeAddress(String takeAddress) {
        this.takeAddress = takeAddress;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        tStart = sdf.format(starttime).substring(11,16);
        this.starttime = starttime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        tEnd = sdf.format(deadline).substring(11,16);
        this.deadline = deadline;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        tRelease =sdf.format(releaseTime);
        this.releaseTime = releaseTime;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }


    public String getHostPhone() {
        return hostPhone;
    }

    public void setHostPhone(String hostPhone) {
        this.hostPhone = hostPhone;
    }

    public Byte getOrderState() {
        return orderState;
    }

    public void setOrderState(Byte orderState) {
        this.orderState = orderState;
    }

    public Date getFinishTime() {

        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        tFinish = sdf.format(finishTime);
        this.finishTime = finishTime;
    }
}
