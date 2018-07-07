package com.paishop.entity;

import java.util.Date;

public class Auction {
    private Integer id;

    private Integer pId;

    private Integer uId;

    private Integer autionPv;

    private Float auctionPrice;

    private Integer status;

    private String auctionName;

    private Float pPrice;

    private Date startTime;

    private Date auctionTime;

    private Date endTime;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getAutionPv() {
        return autionPv;
    }

    public void setAutionPv(Integer autionPv) {
        this.autionPv = autionPv;
    }

    public Float getAuctionPrice() {
        return auctionPrice;
    }

    public void setAuctionPrice(Float auctionPrice) {
        this.auctionPrice = auctionPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName == null ? null : auctionName.trim();
    }

    public Float getpPrice() {
        return pPrice;
    }

    public void setpPrice(Float pPrice) {
        this.pPrice = pPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getAuctionTime() {
        return auctionTime;
    }

    public void setAuctionTime(Date auctionTime) {
        this.auctionTime = auctionTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}