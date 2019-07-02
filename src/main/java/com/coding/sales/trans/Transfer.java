package com.coding.sales.trans;

import com.coding.sales.input.OrderCommand;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transfer {
    public void byGoldCalcPrice(OrderCommand command) {
        JSONObject jsonObject = new JSONObject(command);
       // String list= jsonObject.getString("items");
       // ArrayList<String,String> item=jsonObject.getJSONArray("items");
        Map<String,String> item=new HashMap<String, String>();
        item.putAll((Map<? extends String, ? extends String>) jsonObject.getJSONArray("items"));
        for (int i=0;i<item.size();i++){
            System.out.print(item);
        }

    }
}
