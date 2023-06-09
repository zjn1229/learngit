package com.example.petstore.common.utils;

//import com.example.petstore.example.entiey.OrdersItem;
//import com.example.petstore.example.service.OrdersItemService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "deadQueue")//监听的队列名称 deadQueue
public class DeadReceiver {

//    @Autowired
//    private OrdersItemService ordersItemService;
//
//    @RabbitHandler
//    public void process(Map message) {
//        System.out.println(DateTool.getCurrTime() + "  死信队列收到消息  : " + message.toString());
//        OrdersItem ordersItem = ordersItemService.getById(message.get("ordersItemId").toString());
//        if (ordersItem != null && ordersItem.getStatus() == 0) {
//            ordersItemService.setStatus(ordersItem.getId(), -1, "超时未支付自动取消");
//        }
//    }

}
