package com.coding.sales.trans;

import java.util.List;

public class custInfo {
    private  String custName;//客户名称
    private  String custLevel;//客户等级
    private  String CardNo;//客户卡号
    private  int custSroce;//客户积分
    private List<String> custInfo;//客户信息

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String cardNo) {
        CardNo = cardNo;
    }

    public int getCustSroce() {
        return custSroce;
    }

    public void setCustSroce(int custSroce) {
        this.custSroce = custSroce;
    }

    public List<String> getCustInfo() {
        return custInfo;
    }

    public void setCustInfo(List<String> custInfo) {
        this.custInfo = custInfo;
    }
}
