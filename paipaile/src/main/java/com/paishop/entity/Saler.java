package com.paishop.entity;

import java.util.Date;
import java.util.List;

public class Saler {
    private Integer sId;

    private String wxOpenid;

    private String sTelephone;

    private Float sMoney;

    private Date createTime;

    private Date modifyTime;

  
    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
    }

    public String getsTelephone() {
        return sTelephone;
    }

    public void setsTelephone(String sTelephone) {
        this.sTelephone = sTelephone == null ? null : sTelephone.trim();
    }

    public Float getsMoney() {
        return sMoney;
    }

    public void setsMoney(Float sMoney) {
        this.sMoney = sMoney;
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