package com.paishop.entity;

import java.util.Date;

public class Order {
    private String id;

    private Integer pId;

    private Integer uId;

    private Float marketPrice;

    private Float buyPrice;

    private Integer oStatus;

    private String oAddress;

    private String userRemark;

    private Date oCreatetime;

    private Date oModifytime;
    
    private Product product;
    
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark == null ? null : userRemark.trim();
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
    
    
}