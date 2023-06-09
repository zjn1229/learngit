package com.example.petstore.common.config;


import com.example.petstore.common.utils.RabbitMQProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DirectRabbitConfig {

    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    //获取连接工厂
    @Bean
    public ConnectionFactory factory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(rabbitMQProperties.getHost());
        factory.setPort(rabbitMQProperties.getPort());
        factory.setUsername(rabbitMQProperties.getUserName());
        factory.setPassword(rabbitMQProperties.getPassword());
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        return template;
    }

    /* 直连队列配置 **********************************************************************************************************/
    //队列 起名：directQueue
    @Bean
    public Queue directQueue() {
        Map<String, Object> args = deadQueueArgs();
        // 队列设置消息过期时间30分钟
        args.put("x-message-ttl", 30 * 60 * 1000);
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，默认也是false，当没有生产者或者消费者使用此队列，该队列会自动删除。
        return new Queue("directQueue", true, true, false, args);
    }

    //Direct交换机 起名：DirectExchange
    @Bean
    DirectExchange DirectExchange() {
        //  return new DirectExchange("DirectExchange",true,true);
        return new DirectExchange("directExchange", true, false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：DirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(directQueue()).to(DirectExchange()).with("directRouting");
    }


    /* 死信配置 **********************************************************************************************************/

    /**
     * 死信交换机
     */
    @Bean
    DirectExchange deadExchange() {
        return new DirectExchange("deadExchange", true, false);
    }

    /**
     * 死信队列
     */
    @Bean
    public Queue deadQueue() {
        return new Queue("deadQueue", true, false, false);
    }

    @Bean
    Binding deadRouteBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("deadRouting");
    }

    /**
     * 转发到 死信队列，配置参数
     */
    private Map<String, Object> deadQueueArgs() {
        Map<String, Object> map = new HashMap<>();
        // 绑定该队列到私信交换机
        map.put("x-dead-letter-exchange", "deadExchange");
        map.put("x-dead-letter-routing-key", "deadRouting");
        return map;
    }


}