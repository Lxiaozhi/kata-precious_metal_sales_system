package com.coding.sales.trans;

import java.math.BigDecimal;

public class PreciousMetal {
    private  String productName;//商品名称
    private  String unit;//商品单位
    private BigDecimal price;//商品价格
    private  Double discount;//商品折扣
    private  boolean isManJian;//是否满减

    public boolean isManJian() {
        return isManJian;
    }

    public void setManJian(boolean manJian) {
        isManJian = manJian;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

}
