package com.coding.sales.utils;

import com.coding.sales.trans.PreciousMetal;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GetPreciousMetalInfo {
    public static PreciousMetal getPreciousMetal(String productId){
        Map map=new HashMap();
        PreciousMetal preciousMetal1=getPreciousMetal("世园会五十国钱币册","册",new BigDecimal(998.00),0.0,true);
        map.put("001001",preciousMetal1);
        PreciousMetal preciousMetal2=getPreciousMetal("2019北京世园会纪念银章大全40g","盒",new BigDecimal(1380.00),0.9,true);
        map.put("001002",preciousMetal2);
        PreciousMetal preciousMetal3=getPreciousMetal("招财进宝","条",new BigDecimal(1580.00),0.95,true);
        map.put("003001",preciousMetal3);
        PreciousMetal preciousMetal4=getPreciousMetal("水晶之恋","条",new BigDecimal(980.00),0.0,true);
        map.put("003002",preciousMetal4);
        PreciousMetal preciousMetal5=getPreciousMetal("中国经典钱币套装","套",new BigDecimal(998.00),0.0,true);
        map.put("002002",preciousMetal5);
        PreciousMetal preciousMetal6=getPreciousMetal("守扩之羽比翼双飞4.8g","条",new BigDecimal(1080.00 ),0.95,true );
        map.put("002001",preciousMetal6);
        PreciousMetal preciousMetal7=getPreciousMetal("中国银象棋12g ","套",new BigDecimal(698.00),0.9,true);
        map.put("002003",preciousMetal7);
        PreciousMetal preciousMetal=(PreciousMetal)map.get(productId);
        return preciousMetal;
    }
    public static PreciousMetal getPreciousMetal(String productName,String unit,BigDecimal price,Double discount,boolean isManjian){
        PreciousMetal preciousMetal=new PreciousMetal();
        preciousMetal.setProductName(productName);
        preciousMetal.setUnit(unit);
        preciousMetal.setPrice(price);
        preciousMetal.setDiscount(discount);
        preciousMetal.setManJian(isManjian);
        return preciousMetal;
    }
}
