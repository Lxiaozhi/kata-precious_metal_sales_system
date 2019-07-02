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
   * ���ݻ��ּ���ͻ��ȼ�
   * money ���ѽ��
   * Sorce �ͻ�����
   * */
    public String bySorceCalcCustLevel(int Sorce){
        String lvl="�ǻ�Ա";
        if(Sorce<10000){
            lvl="�տ�";
        }
        if(Sorce>=10000&&Sorce<50000){
            lvl="��";
        }
        if(Sorce>=50000&&Sorce<100000){
            lvl="�׽�";
        }
        if(Sorce>=100000){
            lvl="��ʯ��";
        }
        return lvl;
    }
    /*
   * �����
   *proNum ��Ʒ����
   * proPrice ��Ʒ�۸�
   * manjianType ��������
   * 0-ÿ��3000Ԫ��350
   * 1-ÿ��2000Ԫ��30
   * 2-ÿ��1000Ԫ��10
   * 3-��3����ۣ���3�������ϣ�����1����ۣ�
   * 4-��3��1����4�������ϣ�����1����ѣ�
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
                    proMoney = proPrice.multiply(BigDecimal.valueOf(2));//������Ʒ�ü۸�
                    proPrice=proPrice.divide(proPrice,2,BigDecimal.ROUND_HALF_UP);//һ����Ʒ���
                    proMoney= proMoney.add(proPrice);//�����۸����
                }
                break;
            case 4:
                if(proNum>=4){
                    proNum=proNum-1;
                    proMoney = proPrice.multiply(BigDecimal.valueOf(proNum));//������Ʒ�ü۸�
                }
                break;
        }
        System.out.println(proMoney);
        return proMoney;
    }
}
