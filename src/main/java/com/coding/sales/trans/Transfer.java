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
    /*
   * 满减活动
   *proNum 产品数量
   * proPrice 产品价格
   * manjianType 满减类型
   * 0-每满3000元减350
   * 1-每满2000元减30
   * 2-每满1000元减10
   * 3-第3件半价（买3件及以上，其中1件半价）
   * 4-满3送1（买4件及以上，其中1件免费）
   * */
    public BigDecimal manjian(int manjianType,int proNum,BigDecimal proPrice){
        BigDecimal proMoney=proPrice;
        switch (manjianType){
            case 0:
                if(proPrice.intValue()>=3000){
                    proMoney=proPrice.subtract(BigDecimal.valueOf(350));
                }
                break;
            case 1:
                if(proPrice.intValue()>=2000){
                    proMoney=proPrice.subtract(BigDecimal.valueOf(30));
                }
                break;
            case 2:
                if(proPrice.intValue()>=1000){
                    proMoney=proPrice.subtract(BigDecimal.valueOf(10));
                }
                break;
            case 3:
                if(proNum>=3){
                    proMoney = proPrice.multiply(BigDecimal.valueOf(2));//两件商品得价格
                    proPrice=proPrice.divide(proPrice,2,BigDecimal.ROUND_HALF_UP);//一件商品半价
                    proMoney= proMoney.add(proPrice);//两个价格相加
                }
                break;
            case 4:
                if(proNum>=4){
                    proNum=proNum-1;
                    proMoney = proPrice.multiply(BigDecimal.valueOf(proNum));//两件商品得价格
                }
                break;
        }
        System.out.println(proMoney);
        return proMoney;
    }
}
