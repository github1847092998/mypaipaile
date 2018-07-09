package com.paishop.entity;

import java.util.Date;

public class Product {
    private Integer pId;

    private Integer sId;

    private String pName;

    private Integer pNum;

    private Float pMarketPrice;

    private Float pSalePrice;

    private String mediaMain;

    private String mediaDetail;

    private String pDesc;

    private Integer status;

    private Date saleTime;

    private Integer saleNum;

    private Date createTim;

    private Date modifyTime;
    
    private Auction auction;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public Integer getpNum() {
        return pNum;
    }

    public void setpNum(Integer pNum) {
        this.pNum = pNum;
    }

    public Float getpMarketPrice() {
        return pMarketPrice;
    }

    public void setpMarketPrice(Float pMarketPrice) {
        this.pMarketPrice = pMarketPrice;
    }

    public Float getpSalePrice() {
        return pSalePrice;
    }

    public void setpSalePrice(Float pSalePrice) {
        this.pSalePrice = pSalePrice;
    }

    public String getMediaMain() {
        return mediaMain;
    }

    public void setMediaMain(String mediaMain) {
        this.mediaMain = mediaMain == null ? null : mediaMain.trim();
    }

    public String getMediaDetail() {
        return mediaDetail;
    }

    public void setMediaDetail(String mediaDetail) {
        this.mediaDetail = mediaDetail == null ? null : mediaDetail.trim();
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc == null ? null : pDesc.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Date getCreateTim() {
        return createTim;
    }

    public void setCreateTim(Date createTim) {
        this.createTim = createTim;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}
    
}