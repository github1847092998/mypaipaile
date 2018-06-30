package com.paishop.entity;

import java.util.Date;

public class Saler {
    private Integer id;

    private String sUsername;

    private String sPassword;

    private String sTelephone;

    private String sEmail;

    private String sAddress;

    private Float sMoney;

    private String expressTemple;

    private Date createTime;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getsUsername() {
        return sUsername;
    }

    public void setsUsername(String sUsername) {
        this.sUsername = sUsername == null ? null : sUsername.trim();
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword == null ? null : sPassword.trim();
    }

    public String getsTelephone() {
        return sTelephone;
    }

    public void setsTelephone(String sTelephone) {
        this.sTelephone = sTelephone == null ? null : sTelephone.trim();
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail == null ? null : sEmail.trim();
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress == null ? null : sAddress.trim();
    }

    public Float getsMoney() {
        return sMoney;
    }

    public void setsMoney(Float sMoney) {
        this.sMoney = sMoney;
    }

    public String getExpressTemple() {
        return expressTemple;
    }

    public void setExpressTemple(String expressTemple) {
        this.expressTemple = expressTemple == null ? null : expressTemple.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}