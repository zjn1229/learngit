package com.example.petstore.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petstore.common.utils.DateTool;
import com.example.petstore.common.utils.JwtUtil;
import com.example.petstore.common.utils.SendMessageUtil;
import com.example.petstore.example.entiey.Orders;
import com.example.petstore.example.mapper.OrdersMapper;
import com.example.petstore.example.service.OrdersService;
//import com.example.petstore.example.vo.OrdersBookShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SendMessageUtil sendMessageUtil;

    @Override
    public Boolean add(Orders orders) {
        String token = request.getHeader("Authorization");
        String userId = JwtUtil.validateToken(token);
        orders.setUserId(userId);
        orders.setCreateTime(DateTool.getCurrTime());
        orders.setUpdateTime(DateTool.getCurrTime());
        this.save(orders);
        //加入消息队列
        Map<String, Object> map = new HashMap<>();
        map.put("ordersId", orders.getId());
        sendMessageUtil.sendDirectMessage(map);
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for (String id : aryIds) {
            listIds.add(id);
        }
        this.removeByIds(listIds);
    }

    @Override
    public Boolean update(Orders orders) {
        orders.setUpdateTime(DateTool.getCurrTime());
        this.updateById(orders);
        return true;
    }

//    @Override
//    public List<OrdersBookShop> list(String bookName) {
//        String token = request.getHeader("Authorization");
//        String userId = JwtUtil.validateToken(token);
//        return baseMapper.listByBookName(userId, bookName);
//    }
//
//    @Override
//    public List<OrdersBookShop> listByAdminId() {
//        String token = request.getHeader("Authorization");
//        String adminId = JwtUtil.validateToken(token);
//        return baseMapper.listByAdminId(adminId);
//    }

    @Override
    public Boolean setStatus(String ids, int status, String reason) {
        String[] aryIds = ids.split(",");
        for (String id : aryIds) {
            Orders orders = this.getById(id);
            if (status == -2) {
                //记录申请退款前的订单状态
                orders.setPreStatus(orders.getStatus());
            }
            orders.setStatus(status);
            orders.setReason(reason);
            orders.setUpdateTime(DateTool.getCurrTime());
            this.updateById(orders);
        }
        return true;
    }

}

