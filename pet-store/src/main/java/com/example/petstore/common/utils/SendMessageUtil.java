package com.example.petstore.common.utils;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class SendMessageUtil {

    @Autowired
    private RabbitTemplate rabbitTemplate;   //使用RabbitTemplate,这提供了接收/发送等等方法

    public Result sendDirectMessage(Map messageMap) {
        Result result = new Result();
        //将要发送的消息转为map
        Map<String, Object> map = new HashMap<>(messageMap);
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = DateTool.getCurrTime();
        map.put("messageId", messageId);
        map.put("createTime", createTime);
        //将消息携带绑定键值：directRouting 发送到交换机directExchange
        rabbitTemplate.convertAndSend("directExchange", "directRouting", map);
        System.out.println(createTime + "  发送消息  : " + map);
        result.success("RabbitMq发送成功");
        result.setData(map);
        return result;
    }

}