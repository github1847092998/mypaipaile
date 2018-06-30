package com.paishop.entity;

public class ProductType {
    private Integer pfOne;

    private String pfOneName;

    public Integer getPfOne() {
        return pfOne;
    }

    public void setPfOne(Integer pfOne) {
        this.pfOne = pfOne;
    }

    public String getPfOneName() {
        return pfOneName;
    }

    public void setPfOneName(String pfOneName) {
        this.pfOneName = pfOneName == null ? null : pfOneName.trim();
    }
}