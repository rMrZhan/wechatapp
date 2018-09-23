package top.jianghuling.wechatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BriefOrder {
    @JsonIgnore
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @JsonIgnore
    private Date starttime;
    @JsonIgnore
    private Date deadline;
    private String destination;//送达地点
    private String note;//备注
    private String takeAddress;//取货地点
    private String goodsWeight;
    private float reward;
    @JsonIgnore
    private Date releaseTime;//发布时间
    private String expressType;//快递品牌
    private String orderId;


    private String tRelease;
    private String tStart;
    private String tEnd;

//    public void convertTimeFormat(){
//
//        tRelease = sdf.format(releaseTime);
//        tStart = sdf.format(starttime);
//        tEnd  = sdf.format(deadline);
//    }


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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
        tStart = sdf.format(starttime).substring(11,16);
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        tEnd = sdf.format(deadline).substring(11,16);
        this.deadline = deadline;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTakeAddress() {
        return takeAddress;
    }

    public void setTakeAddress(String takeAddress) {
        this.takeAddress = takeAddress;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public float getReward() {
        return reward;
    }

    public void setReward(float reward) {
        this.reward = reward;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
        tRelease = sdf.format(releaseTime);
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }
}
