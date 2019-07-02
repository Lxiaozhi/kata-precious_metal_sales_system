package com.coding.sales;

import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.PaymentRepresentation;
import com.coding.sales.trans.PreciousMetal;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.trans.custInfo;
import com.coding.sales.utils.GetCustInfo;
import com.coding.sales.utils.GetPreciousMetalInfo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 销售系统的主入口
 * 用于打印销售凭证
 */
public class OrderApp {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("参数不正确。参数1为销售订单的JSON文件名，参数2为待打印销售凭证的文本文件名.");
        }

        String jsonFileName = args[0];
        String txtFileName = args[1];

        String orderCommand = FileUtils.readFromFile(jsonFileName);
        OrderApp app = new OrderApp();
        String result = app.checkout(orderCommand);
        FileUtils.writeToFile(result, txtFileName);
    }

    public String checkout(String orderCommand) {
        OrderCommand command = OrderCommand.from(orderCommand);
        OrderRepresentation result = checkout(command);
        
        return result.toString();
    }

    OrderRepresentation checkout(OrderCommand command) {
        OrderRepresentation result = null;

        //TODO: 请完成需求指定的功能
        String orderId=command.getOrderId();
        Date createTime=new Date();
        try {
            createTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(command.getCreateTime());
        }catch (Exception e){
            e.getLocalizedMessage();
        }
        String memberNo=command.getMemberId();
        custInfo cust=GetCustInfo.byIdGetCustInfo(memberNo);
        String memberName= cust.getCustName();
        String oldMemberType=cust.getCustLevel();
        int custSroce=cust.getCustSroce();
        String newMemberType="";

        BigDecimal totalPrice=BigDecimal.ZERO;
        BigDecimal totalDiscountPrice=BigDecimal.ZERO;
        List<OrderItemRepresentation> orderItems=new ArrayList<OrderItemRepresentation>();

        List<OrderItemCommand> payments=command.getItems();
        BigDecimal total=new BigDecimal(0.0);
        for(int i=0;i<payments.size();i++){
            OrderItemCommand paymentCommand=payments.get(i);
            String productId=paymentCommand.getProduct();
            BigDecimal amount=paymentCommand.getAmount();
            PreciousMetal preciousMetal= GetPreciousMetalInfo.getPreciousMetal(productId);
            BigDecimal price=preciousMetal.getPrice();
            OrderItemRepresentation orderItemRepresentation=new OrderItemRepresentation(productId,preciousMetal.getProductName(),price,amount,price.multiply(amount));
            orderItems.add(orderItemRepresentation);
            totalPrice=totalPrice.add(price.multiply(amount));
        }
        int memberPointsIncreased=(int)Math.floor(total.doubleValue());
        System.out.println(memberPointsIncreased);
        int memberPoints=custSroce+memberPointsIncreased;
        System.out.println(memberPointsIncreased+""+memberPoints);


        result =new  OrderRepresentation(
                orderId, createTime,
                memberNo, memberName, oldMemberType, newMemberType,
                memberPointsIncreased, memberPoints,
                orderItems, totalPrice,
                getDiscountItems(), totalDiscountPrice, total, getPayments(), getDiscountCards());
        return result;
    }
    private List<OrderItemRepresentation> getOrderItems() {

        return Arrays.asList(
                new OrderItemRepresentation("0001", "AAA", new BigDecimal("101.1"), new BigDecimal("2"), new BigDecimal(202.2)),
                new OrderItemRepresentation("0002", "BBB", new BigDecimal("101.1"), new BigDecimal("2"), new BigDecimal(202.2))
        );
    }

    private List<DiscountItemRepresentation> getDiscountItems() {
        return Arrays.asList(
                new DiscountItemRepresentation("0001", "AAA", new BigDecimal(10.0)),
                new DiscountItemRepresentation("0002", "BBB", new BigDecimal(10.0))
        );
    }

    private List<PaymentRepresentation> getPayments() {
        return Arrays.asList(new PaymentRepresentation("账户余额", new BigDecimal(184.4)));
    }
    private List<String> getDiscountCards() {
        return Arrays.asList("9折券");
    }

}
