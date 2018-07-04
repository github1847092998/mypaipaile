package com.paishop.entity;

import java.util.Date;

public class Auction {
    private Integer id;

    private Integer pId;

    private Integer uId;
    
    private String pPic;
    
    private String pName;
    
    private float pPrice;
    
    private String auctionName;

    private Float auctionPrice;
    
    private String coupon;
    
    private Integer auctionUv;

    private Integer autionPv;
    
    private Integer status;
    
    private Integer auctionStage;

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

	public String getpPic() {
		return pPic;
	}

	public void setpPic(String pPic) {
		this.pPic = pPic;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public void setpPrice(float pPrice) {
		this.pPrice = pPrice;
	}

	public Integer getAuctionUv() {
        return auctionUv;
    }

    public void setAuctionUv(Integer auctionUv) {
        this.auctionUv = auctionUv;
    }

    public Integer getAutionPv() {
        return autionPv;
    }

    public void setAutionPv(Integer autionPv) {
        this.autionPv = autionPv;
    }

    public Integer getAuctionStage() {
        return auctionStage;
    }

    public void setAuctionStage(Integer auctionStage) {
        this.auctionStage = auctionStage;
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

    public float getpPrice() {
		return pPrice;
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