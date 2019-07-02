package com.coding.sales.utils;

import com.coding.sales.trans.CustInfo;

import java.util.HashMap;
import java.util.Map;

public class GetCustInfo {
    public  static CustInfo getCustInfo(String custName, String custLevel, String custSroce){
        CustInfo info=new CustInfo();
        info.setCustName(custName);
        info.setCustLevel(custLevel);
        info.setCustSroce(custSroce);
        // info.setCardNo(CardNo);
        return info;
    }
    //存储客户信息
    public static CustInfo byIdGetCustInfo(String cardNo){
        Map maps=new HashMap();
        CustInfo info1=getCustInfo("马丁","普卡","9860");
        maps.put("6236609999",info1);
        CustInfo info2=getCustInfo("王立","金卡","48860");
        maps.put("6630009999",info2);
        CustInfo info3=getCustInfo("李想","白金卡","98860");
        maps.put("8230009999",info3);
        CustInfo info4=getCustInfo("张三","钻石卡","198860");
        maps.put("9230009999",info4);
        CustInfo info=(CustInfo)maps.get(cardNo);
        return info;

    }
}
