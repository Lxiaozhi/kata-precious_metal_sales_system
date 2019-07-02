package com.coding.sales;

import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.PaymentRepresentation;
import com.coding.sales.trans.PreciousMetal;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.trans.Transfer;
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

        String orderId=command.getOrderId();//订单号
        Date createTime=new Date();
        try {
            createTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(command.getCreateTime());//创建日期
        }catch (Exception e){
            e.getLocalizedMessage();
        }
        String memberNo=command.getMemberId();//客户卡号
        custInfo cust=GetCustInfo.byIdGetCustInfo(memberNo);
        String memberName= cust.getCustName();//客户姓名
        String oldMemberType=cust.getCustLevel();//客户原等级
        int custSroce=cust.getCustSroce();//客户原有积分
        String newMemberType="";//客户新等级

        BigDecimal totalPrice=BigDecimal.ZERO;//总金额
        BigDecimal totalDiscountPrice=BigDecimal.ZERO;//优惠金额
        List<OrderItemRepresentation> orderItems=new ArrayList<OrderItemRepresentation>();//产品清单列表
        List<DiscountItemRepresentation> discountItems=new ArrayList<DiscountItemRepresentation>();//优惠产品列表

        List<OrderItemCommand> payments=command.getItems();//
        BigDecimal total=BigDecimal.valueOf(0.0);//实际支付金额
        for(int i=0;i<payments.size();i++){
            OrderItemCommand paymentCommand=payments.get(i);
            String productId=paymentCommand.getProduct();
            BigDecimal amount=paymentCommand.getAmount();
            PreciousMetal preciousMetal= GetPreciousMetalInfo.getPreciousMetal(productId);
            BigDecimal price=preciousMetal.getPrice();
            //获取产品清单
            OrderItemRepresentation orderItemRepresentation=new OrderItemRepresentation(productId,preciousMetal.getProductName(),price,amount,price.multiply(amount));
            orderItems.add(orderItemRepresentation);
            //获取产品优惠信息
            BigDecimal discount=BigDecimal.valueOf(preciousMetal.getDiscount());
            if(discount.compareTo(BigDecimal.ZERO)!=0) {
                //优惠金额
                discount = BigDecimal.valueOf(1).subtract(discount);
                DiscountItemRepresentation discountItemRepresentation = new DiscountItemRepresentation(productId, preciousMetal.getProductName(), price.multiply(discount).multiply(amount));
                discountItems.add(discountItemRepresentation);
                totalDiscountPrice = totalDiscountPrice.add(price.multiply(discount).multiply(amount));//优惠总金额
            }
            totalPrice=totalPrice.add(price.multiply(amount));
        }
        int memberPointsIncreased=(int)Math.floor(totalPrice.doubleValue());//新增积分
        int memberPoints=custSroce+memberPointsIncreased;//现有积分
        newMemberType=new Transfer().bySorceCalcCustLevel(memberPoints);//客户新等级
        total=totalPrice.subtract(totalDiscountPrice);//实际支付

        result =new  OrderRepresentation(
                orderId, createTime,
                memberNo, memberName, oldMemberType, newMemberType,
                memberPointsIncreased, memberPoints,
                orderItems, totalPrice,
                discountItems, totalDiscountPrice, total, getPayments(), getDiscountCards());
        return result;
    }

    private List<PaymentRepresentation> getPayments() {
        return Arrays.asList(new PaymentRepresentation("账户余额", new BigDecimal(184.4)));
    }
    private List<String> getDiscountCards() {
        return Arrays.asList("9折券");
    }

}
