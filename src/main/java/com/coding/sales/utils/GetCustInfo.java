package com.coding.sales.utils;

import com.coding.sales.trans.custInfo;

import java.util.HashMap;
import java.util.Map;

public class GetCustInfo {
    public  static custInfo getCustInfo(String custName, String custLevel, int custSroce){
        custInfo info=new custInfo();
        info.setCustName(custName);
        info.setCustLevel(custLevel);
        info.setCustSroce(custSroce);
        // info.setCardNo(CardNo);
        return info;
    }
    //存储客户信息
    public static  custInfo  byIdGetCustInfo(String cardNo){
        Map maps=new HashMap();
        custInfo info1=getCustInfo("马丁","普卡",9860);
        maps.put("6236609999",info1);
        custInfo info2=getCustInfo("王立","金卡",48860);
        maps.put("6630009999",info2);
        custInfo info3=getCustInfo("李想","白金卡",98860);
        maps.put("8230009999",info3);
        custInfo info4=getCustInfo("张三","钻石卡",198860);
        maps.put("9230009999",info4);
        custInfo info=(custInfo)maps.get(cardNo);
        return info;

    }
}
