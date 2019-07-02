package com.coding.sales.trans;

import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transfer {
    /*
   * 根据积分计算客户等级
   * money 消费金额
   * Sorce 客户积分
   * */
    public String bySorceCalcCustLevel(int Sorce){
        String lvl="非会员";
        if(Sorce<10000){
            lvl="普卡";
        }
        if(Sorce>=10000&&Sorce<50000){
            lvl="金卡";
        }
        if(Sorce>=50000&&Sorce<100000){
            lvl="白金卡";
        }
        if(Sorce>=100000){
            lvl="钻石卡";
        }
        return lvl;
    }
   
}
