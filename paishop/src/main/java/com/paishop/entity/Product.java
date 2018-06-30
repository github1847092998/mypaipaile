package com.paishop.entity;

import java.util.Date;

public class Product {
    private Integer id;

    private Integer pfOne;

    private Integer sId;

    private String pName;

    private Integer pNum;

    private Integer pAuctionNum;

    private Float pMarketPrice;

    private Float pSalePrice;

    private String mediaMain;

    private String mediaDetail;

    private String pSpec;

    private String pDesc;

    private Integer collectNum;

    private Integer starNum;

    private Integer status;

    private Date saleTime;

    private Integer saleNum;

    private Integer countComment;

    private Integer auctionPv;

    private Date createTim;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPfOne() {
        return pfOne;
    }

    public void setPfOne(Integer pfOne) {
        this.pfOne = pfOne;
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

    public Integer getpAuctionNum() {
        return pAuctionNum;
    }

    public void setpAuctionNum(Integer pAuctionNum) {
        this.pAuctionNum = pAuctionNum;
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

    public String getpSpec() {
        return pSpec;
    }

    public void setpSpec(String pSpec) {
        this.pSpec = pSpec == null ? null : pSpec.trim();
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc == null ? null : pDesc.trim();
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getStarNum() {
        return starNum;
    }

    public void setStarNum(Integer starNum) {
        this.starNum = starNum;
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

    public Integer getCountComment() {
        return countComment;
    }

    public void setCountComment(Integer countComment) {
        this.countComment = countComment;
    }

    public Integer getAuctionPv() {
        return auctionPv;
    }

    public void setAuctionPv(Integer auctionPv) {
        this.auctionPv = auctionPv;
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
}