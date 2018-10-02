package top.jianghuling.wechatapp.model;

import java.util.Date;

public class UserComment {
    private Integer id;

    private String userPhone;

    private String commentContent;

    private Date submmitDate;

    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public Date getSubmmitDate() {
        return submmitDate;
    }

    public void setSubmmitDate(Date submmitDate) {
        this.submmitDate = submmitDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}