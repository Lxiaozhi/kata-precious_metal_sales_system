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
   
}
