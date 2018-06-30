package com.paishop.entity;

import java.util.Date;

public class Order {
    private Integer id;

    private Integer pId;

    private Integer uId;

    private Float marketPrice;

    private Float buyPrice;

    private Integer oStatus;

    private String oAddress;

    private String oSpec;

    private String userRemark;

    private String oExpress;

    private Float expressFee;

    private String uComment;

    private Integer afferSaleType;

    private Integer afterSaleStatus;

    private Date oCreatetime;

    private Date oModifytime;

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

    public Float getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Float marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getoStatus() {
        return oStatus;
    }

    public void setoStatus(Integer oStatus) {
        this.oStatus = oStatus;
    }

    public String getoAddress() {
        return oAddress;
    }

    public void setoAddress(String oAddress) {
        this.oAddress = oAddress == null ? null : oAddress.trim();
    }

    public String getoSpec() {
        return oSpec;
    }

    public void setoSpec(String oSpec) {
        this.oSpec = oSpec == null ? null : oSpec.trim();
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark == null ? null : userRemark.trim();
    }

    public String getoExpress() {
        return oExpress;
    }

    public void setoExpress(String oExpress) {
        this.oExpress = oExpress == null ? null : oExpress.trim();
    }

    public Float getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(Float expressFee) {
        this.expressFee = expressFee;
    }

    public String getuComment() {
        return uComment;
    }

    public void setuComment(String uComment) {
        this.uComment = uComment == null ? null : uComment.trim();
    }

    public Integer getAfferSaleType() {
        return afferSaleType;
    }

    public void setAfferSaleType(Integer afferSaleType) {
        this.afferSaleType = afferSaleType;
    }

    public Integer getAfterSaleStatus() {
        return afterSaleStatus;
    }

    public void setAfterSaleStatus(Integer afterSaleStatus) {
        this.afterSaleStatus = afterSaleStatus;
    }

    public Date getoCreatetime() {
        return oCreatetime;
    }

    public void setoCreatetime(Date oCreatetime) {
        this.oCreatetime = oCreatetime;
    }

    public Date getoModifytime() {
        return oModifytime;
    }

    public void setoModifytime(Date oModifytime) {
        this.oModifytime = oModifytime;
    }
}